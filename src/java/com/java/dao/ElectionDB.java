/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.dao;

import com.java.data.ConnectionPool;
import com.java.model.Address;
import com.java.model.Admin;
import com.java.model.Candidate;
import com.java.model.Election;
import com.java.model.Party;
import com.java.model.User;
import com.java.util.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Pinkesh
 */
public class ElectionDB {

    ConnectionPool pool;
    Connection connection;

    public ElectionDB() {
        pool = ConnectionPool.getInstance();

    }

    public Election getTodaysElectionDetailsForCurrentUser(String county, String userId) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String query = "select * from Election where District= ? and date(TimePeriod) = ? "
                + "and electionId not in (select ElectionID from ElectionVoters where userID = ?)";

        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, county);
            ps.setString(2, sdf.format(new Date()));
            ps.setString(3, userId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Election(resultSet.getString("ElectionID"), resultSet.getString("ElectionType"), resultSet.getString("District"),
                        resultSet.getString("TimePeriod"), resultSet.getString("State"), resultSet.getString("ElectionWinner"));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public Election getTodaysElectionDetails(String county) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String query = "select * from Election where District= ? and date(TimePeriod) = ?";

        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, county);
            ps.setString(2, sdf.format(new Date()));
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Election(resultSet.getString("ElectionID"), resultSet.getString("ElectionType"), resultSet.getString("District"),
                        resultSet.getString("TimePeriod"), resultSet.getString("State"), resultSet.getString("ElectionWinner"));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int insertVote(String electionID, String candidateID, String userID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        String result;
        try {
            ps = connection.prepareStatement("{call newvote(?,?,?)}");
            ps.setString(1, electionID);
            ps.setString(2, candidateID);
            ps.setString(3, userID);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(1);
                if (result.contains("committed")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Election> getUpcomingElections() {
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "select * from Election where STR_TO_DATE(TimePeriod, '%d-%m-%Y') > now()";
        List<Election> elections = new ArrayList<>();
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                elections.add(new Election(resultSet.getString("ElectionID"), resultSet.getString("ElectionType"), resultSet.getString("District"),
                        resultSet.getString("TimePeriod"), resultSet.getString("State"), resultSet.getString("ElectionWinner")));
            }
            return elections;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Election> getPastElections() {
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "select * from Election e LEFT JOIN candidate c ON e.ElectionWinner=c.CandidateID where STR_TO_DATE(e.TimePeriod, '%d-%m-%Y') < now()";
        List<Election> elections = new ArrayList<>();
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                elections.add(new Election(resultSet.getString("ElectionID"), resultSet.getString("ElectionType"), resultSet.getString("District"),
                        resultSet.getString("TimePeriod"), resultSet.getString("State"), resultSet.getString("FirstName") + " " + resultSet.getString("LastName")));
            }
            return elections;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public Map getListOfCandidates(String electionID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        Map<String, Candidate> candidates = new HashMap<>();
        String query = "select c.CandidateID as CandidateID,c.FirstName as FirstName,c.LastName as LastName,c.PhoneNumber as PhoneNumber,c.Email as Email,c.TotalAssets as TotalAssets,p.PartyName as PartyName from Candidate c join party p on c.PartyID=p.PartyID where CandidateID in (select CandidateID from candidateelection where ElectionID = ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, electionID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Candidate candidateInfo = new Candidate(resultSet.getString("CandidateID"), resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), null, resultSet.getString("PhoneNumber"), resultSet.getString("Email"),
                        null, resultSet.getString("TotalAssets"), null, null);
                Party party = new Party();
                party.setPartyName(resultSet.getString("PartyName"));
                candidateInfo.setParty(party);
                candidates.put(resultSet.getString("CandidateID"), candidateInfo);
            }
            return candidates;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Party> getListOfParties() {
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "select * from party p, address a where p.PartyAddress=a.addressID;";
        List<Party> parties = new ArrayList<>();
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                parties.add(new Party(new Address(resultSet.getString("Street"), resultSet.getString("Apartment"),
                        resultSet.getString("City"), resultSet.getString("State"), resultSet.getString("PostalCode")),
                        resultSet.getString("PartyName"), resultSet.getString("PartySymbol"),
                        resultSet.getString("PartyIdeology"), resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email"), resultSet.getString("PartyChairperson"),
                        resultSet.getString("Founded")));
            }
            return parties;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int registerForElection(String electionID, String candidateID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement("INSERT INTO candidateelection (CandidateElectionId, CandidateId, ElectionID, Position, Eligibility) "
                    + "VALUES (?, ?, ?, (select electiontype from election where electionid=?), 'true')");
            ps.setString(1, candidateID + electionID);
            ps.setString(2, candidateID);
            ps.setString(3, electionID);
            ps.setString(4, electionID);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int addElection(Election election) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement("INSERT INTO election(ElectionID, ElectionType,District,TimePeriod,State,ElectionWinner) VALUES (?,?,?,?,?,?)");
            ps.setString(1, election.getElectionType() + election.getState() + election.getTimePeriod());
            ps.setString(2, election.getElectionType());
            ps.setString(3, election.getDistrict());
            ps.setString(4, election.getTimePeriod());
            ps.setString(5, election.getState());
            ps.setString(6, null);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public String addParty(Party party) {
        PreparedStatement insertUserStmt = null;
        connection = pool.getConnection();

        Address address = party.getAddress();

        try {
            insertUserStmt = connection.prepareStatement("{call RegisterParty(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            insertUserStmt.setString(1, party.getEmail());
            insertUserStmt.setString(2, party.getPartyName());
            insertUserStmt.setString(3, party.getPartySymbol());
            insertUserStmt.setString(4, party.getPartyIdeology());
            insertUserStmt.setString(5, party.getEmail());
            insertUserStmt.setString(6, party.getPartyChairperson());
            insertUserStmt.setString(7, party.getFounded());
            insertUserStmt.setString(8, party.getPhoneNumber());

            insertUserStmt.setString(9, address.getStreet());
            insertUserStmt.setString(10, address.getApartment());
            insertUserStmt.setString(11, address.getCity());
            insertUserStmt.setString(12, address.getState());
            insertUserStmt.setString(13, address.getZip());
            insertUserStmt.setString(14, "USA");
            insertUserStmt.setString(15, "Permanent");
            insertUserStmt.executeUpdate();

            insertUserStmt.executeUpdate();
            ResultSet resultSet = insertUserStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return e.getMessage();
        } finally {
            DBUtil.closePreparedStatement(insertUserStmt);
            pool.freeConnection(connection);
        }
    }

    public int addAdmin(Admin admin, String password) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement("INSERT INTO systemuser (UserID,UserPassword,FirstName,LastName,PhoneNumber,Email,Role) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, admin.getEmail());
            ps.setString(2, password);
            ps.setString(3, admin.getFirstName());
            ps.setString(4, admin.getLastName());
            ps.setString(5, admin.getPhoneNumber());
            ps.setString(6, admin.getEmail());
            ps.setString(7, admin.getRole());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int setElectionWinner(String electionId) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {
            ps = connection.prepareStatement("UPDATE election SET ElectionWinner = (select CandidateID from Ballot  where ElectionID= ? group by ElectionID, CandidateID limit 1) WHERE ElectionID= ?;");
            ps.setString(1, electionId);
            ps.setString(2, electionId);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public Map<String, String> getPartyPerformance(String state) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {

            ps = connection.prepareStatement("{call PartyPerformance(?)}");
            ps.setString(1, state);
            ResultSet resultSet = ps.executeQuery();
            Map<String, String> result = new TreeMap<>();
            while (resultSet.next()) {
                result.put(resultSet.getString("partyid"), resultSet.getString("PartyName") + "(" + resultSet.getString("count") + ")");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List getElectionVotingDetails(String electionID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        List<Candidate> candidates = new ArrayList<>();
        String query = "select count(*) as count,  Candidate.FirstName as firstName, Candidate.LastName as lastName, party.PartyName as partyName from Ballot join Candidate join party "
                + "on Ballot.CandidateID = Candidate.CandidateID and Candidate.PartyID=party.PartyID  where Ballot.ElectionID=? group by Ballot.ElectionID, Ballot.CandidateID";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, electionID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Candidate candidateInfo = new Candidate();
                candidateInfo.setCount(resultSet.getString("count"));
                candidateInfo.setFirstName(resultSet.getString("firstName"));
                candidateInfo.setLastName(resultSet.getString("lastName"));
                Party partyInfo = new Party();
                partyInfo.setPartyName(resultSet.getString("partyName"));
                candidateInfo.setParty(partyInfo);
                candidates.add(candidateInfo);
            }
            return candidates;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List getElectionAgeRangeParticipation(String electionID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        String query = "{call AgeRangeParticipation(?)}";
        try {
            List<String> values = new ArrayList<>();
            ps = connection.prepareCall(query);
            ps.setString(1, electionID);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                values.add(resultSet.getString("Age18_30"));
                values.add(resultSet.getString("Age31_45"));
                values.add(resultSet.getString("Age46_60"));
                values.add(resultSet.getString("AgeOver60"));
            }
            return values;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<User> getVoterParticipationInElection(String electionID) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        try {

            ps = connection.prepareStatement("{call VoterParticipatedInElection(?)}");
            ps.setString(1, electionID);
            ResultSet resultSet = ps.executeQuery();
            List<User> listOfVoters = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUserId(resultSet.getString("UserID"));
                user.setCreatedDate(resultSet.getString("CreatedDate"));
                listOfVoters.add(user);
            }
            return listOfVoters;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Candidate> getAllElectionDetails() {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        List<Candidate> candidates = new ArrayList<>();
        String query = "select count(*) as vote_count, E.ElectionType as ElectionType,E.District as District,E.TimePeriod as TimePeriod,C.FirstName as FirstName,"
                + " C.LastName as LastName, P.PartyName as PartyName from Party P, Candidate C, Ballot B, Election E "
                + " where C.PartyID=P.PartyID and B.CandidateID=C.CandidateID and E.ElectionID=B.ElectionID group by B.ElectionID, B.CandidateID";

        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Candidate candidateInfo = new Candidate();
                candidateInfo.setCount(resultSet.getString("vote_count"));
                candidateInfo.setFirstName(resultSet.getString("FirstName"));
                candidateInfo.setLastName(resultSet.getString("LastName"));
                Election electionInfo = new Election();
                electionInfo.setElectionType(resultSet.getString("ElectionType"));
                electionInfo.setTimePeriod(resultSet.getString("TimePeriod"));
                electionInfo.setDistrict(resultSet.getString("District"));
                candidateInfo.setElection(electionInfo);
                Party partyInfo = new Party();
                partyInfo.setPartyName(resultSet.getString("PartyName"));
                candidateInfo.setParty(partyInfo);
                candidates.add(candidateInfo);
            }
            return candidates;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Candidate> getElectionDetailsByWinners() {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        List<Candidate> candidates = new ArrayList<>();
        String query = "select E.District, E.ElectionType, E.TimePeriod, P.PartyName, C.FirstName, C.LastName from Election E, Party P, Candidate C "
                + " where E.ElectionWinner=C.CandidateID and C.PartyID=P.PartyID ";

        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Candidate candidateInfo = new Candidate();
                candidateInfo.setFirstName(resultSet.getString("FirstName"));
                candidateInfo.setLastName(resultSet.getString("LastName"));
                Election electionInfo = new Election();
                electionInfo.setElectionType(resultSet.getString("ElectionType"));
                electionInfo.setTimePeriod(resultSet.getString("TimePeriod"));
                electionInfo.setDistrict(resultSet.getString("District"));
                candidateInfo.setElection(electionInfo);
                Party partyInfo = new Party();
                partyInfo.setPartyName(resultSet.getString("PartyName"));
                candidateInfo.setParty(partyInfo);
                candidates.add(candidateInfo);
            }
            return candidates;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Party> getPartyPerformanceAllOver() {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        List<Party> partys = new ArrayList<>();
        String query = "select count(*) as count, P.PartyName from Election E, Party P, Candidate C where E.ElectionWinner=C.CandidateID and C.PartyID=P.PartyID group by P.PartyName ";
               
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Party partyInfo = new Party();
                partyInfo.setPartyName(resultSet.getString("PartyName"));
                partyInfo.setCount(resultSet.getString("count"));
                partys.add(partyInfo);
            }
            return partys;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public List<Party> getPartyPerformanceStateWise() {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        List<Party> partys = new ArrayList<>();
        String query = "select count(*) as count, P.PartyName as PartyName, E.State as State from Election E, Party P, Candidate C where E.ElectionWinner=C.CandidateID and C.PartyID=P.PartyID group by E.State ";
               
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Party partyInfo = new Party();
                partyInfo.setPartyName(resultSet.getString("PartyName"));
                partyInfo.setCount(resultSet.getString("count"));
                //Storing State in Email to avoid changing Model
                partyInfo.setEmail(resultSet.getString("State"));
                partys.add(partyInfo);
            }
            return partys;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
