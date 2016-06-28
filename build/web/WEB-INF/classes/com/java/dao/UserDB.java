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
import com.java.model.User;
import com.java.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pinkesh
 */
public class UserDB {

    ConnectionPool pool;
    Connection connection;

    public UserDB() {
        pool = ConnectionPool.getInstance();

    }

    public boolean emailExists(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = pool.getConnection();
        String query = "SELECT Email FROM Voters "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public boolean emailExistsCandidate(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = pool.getConnection();
        String query = "SELECT Email FROM Candidate "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public String createUser(User user, String password) {
        PreparedStatement insertUserStmt = null;
        connection = pool.getConnection();

        Address address = user.getAddress();

        try {
            insertUserStmt = connection.prepareStatement("{call RegisterUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            insertUserStmt.setString(1, user.getEmail());
            insertUserStmt.setString(2, password);
            insertUserStmt.setString(3, user.getFirstName());
            insertUserStmt.setString(4, user.getLastName());
            insertUserStmt.setString(5, user.getPhoneNumber());
            insertUserStmt.setString(6, user.getAge());
            insertUserStmt.setString(7, user.getEmail());
            insertUserStmt.setString(8, user.getVoterId());

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

    public int createCandidate(Candidate candidate, String password) {
        PreparedStatement insertAddressStmt = null;
        PreparedStatement insertCandidateStmt = null;
        connection = pool.getConnection();

        Address address = candidate.getAddress();

        String insertAddress
                = "insert into address(Street, Apartment, City,State,PostalCode,Country,AddressType) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertCandidate
                = "INSERT INTO candidate(CandidateID,CandidatePassword,FirstName,LastName,AddressID,PhoneNumber,Email,PartyID,TotalAssets,AccountNumber,AccountHoldingBank) "
                + "VALUES (?, ?, ?, ?, (select max(AddressID) from address), ?, ?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            insertAddressStmt = connection.prepareStatement(insertAddress);
            insertCandidateStmt = connection.prepareStatement(insertCandidate);

            insertAddressStmt.setString(1, address.getStreet());
            insertAddressStmt.setString(2, address.getApartment());
            insertAddressStmt.setString(3, address.getCity());
            insertAddressStmt.setString(4, address.getState());
            insertAddressStmt.setString(5, address.getZip());
            insertAddressStmt.setString(6, "USA");
            insertAddressStmt.setString(7, "Permanent");
            insertAddressStmt.executeUpdate();

            insertCandidateStmt.setString(1, candidate.getEmail());
            insertCandidateStmt.setString(2, password);
            insertCandidateStmt.setString(3, candidate.getFirstName());
            insertCandidateStmt.setString(4, candidate.getLastName());
            insertCandidateStmt.setString(5, candidate.getPhoneNumber());
            insertCandidateStmt.setString(6, candidate.getEmail());
            insertCandidateStmt.setString(7, candidate.getPartyID());
            insertCandidateStmt.setString(8, candidate.getTotalAssets());
            insertCandidateStmt.setString(9, candidate.getAccountNumber());
            insertCandidateStmt.setString(10, candidate.getAccountHoldingBank());
            insertCandidateStmt.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(insertAddressStmt);
            DBUtil.closePreparedStatement(insertCandidateStmt);
            pool.freeConnection(connection);
        }
    }

    public User getUser(String email, String password) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        User user = null;
        String pass;
        String query = "select * from voters v, address a where a.AddressID=v.AddressID and UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                pass = resultSet.getString("UserPassword");
                if (pass.equals(password)) {
                    user = new User(resultSet.getString("UserID"), resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("Email"),
                            resultSet.getString("PhoneNumber"), resultSet.getString("VoterId"), resultSet.getString("Age"),
                            new Address(resultSet.getString("Street"), resultSet.getString("Apartment"), resultSet.getString("City"),
                                    resultSet.getString("State"), resultSet.getString("PostalCode")));
                } else {
                    return user;
                }
            } else {
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }

    public Admin getAdmin(String userId, String password) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        Admin admin;
        String query = "SELECT * FROM SystemUser WHERE UserID = ? and UserPassword = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(resultSet.getString("UserID"), resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("PhoneNumber"), resultSet.getString("Email"), resultSet.getString("Role"));
                return admin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public Candidate getCandidate(String userId, String password) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        Candidate candidate;
        String query = "SELECT * FROM Candidate c, Address a WHERE c.addressID=a.addressID and Email = ? and CandidatePassword=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                candidate = new Candidate(resultSet.getString("CandidateID"), resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("AddressID"), resultSet.getString("PhoneNumber"), resultSet.getString("Email"),
                        resultSet.getString("PartyID"), resultSet.getString("TotalAssets"), resultSet.getString("AccountNumber"),
                        resultSet.getString("AccountHoldingBank"));
                Address address = new Address(resultSet.getString("Street"), resultSet.getString("Apartment"), resultSet.getString("City"),
                        resultSet.getString("State"), resultSet.getString("PostalCode"));
                address.setAddressID(resultSet.getString("AddressID"));
                candidate.setAddress(address);
                return candidate;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public Candidate getCandidate(String userId) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        Candidate candidate;
        String query = "SELECT * FROM Candidate c, Address a WHERE c.addressID=a.addressID and Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                candidate = new Candidate(resultSet.getString("CandidateID"), resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("AddressID"), resultSet.getString("PhoneNumber"), resultSet.getString("Email"),
                        resultSet.getString("PartyID"), resultSet.getString("TotalAssets"), resultSet.getString("AccountNumber"),
                        resultSet.getString("AccountHoldingBank"));
                Address address = new Address(resultSet.getString("Street"), resultSet.getString("Apartment"), resultSet.getString("City"),
                        resultSet.getString("State"), resultSet.getString("PostalCode"));
                address.setAddressID(resultSet.getString("AddressID"));
                candidate.setAddress(address);
                return candidate;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public User getUser(String email) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        User user = null;

        String query = "select * from voters v, address a where a.AddressID=v.AddressID and UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("UserID"), resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"), resultSet.getString("VoterId"), resultSet.getString("Age"),
                        new Address(resultSet.getString("Street"), resultSet.getString("Apartment"), resultSet.getString("City"),
                                resultSet.getString("State"), resultSet.getString("PostalCode")));
            } else {
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }

    public int updateCandidate(Candidate candidate) {
        PreparedStatement updateAddressStmt = null;
        PreparedStatement updateCandidateStmt = null;
        connection = pool.getConnection();

        Address address = candidate.getAddress();
        
        

        String updateAddress = "UPDATE address SET Street = ?, Apartment = ?, City = ?, State = ?, PostalCode = ? WHERE AddressID = ? ";

        String updateCandidate = "UPDATE votez.candidate SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, TotalAssets = ?, AccountNumber = ?, AccountHoldingBank = ? WHERE CandidateID = ?";

        try {
            connection.setAutoCommit(false);
            updateAddressStmt = connection.prepareStatement(updateAddress);
            updateCandidateStmt = connection.prepareStatement(updateCandidate);

            updateAddressStmt.setString(1, address.getStreet());
            updateAddressStmt.setString(2, address.getApartment());
            updateAddressStmt.setString(3, address.getCity());
            updateAddressStmt.setString(4, address.getState());
            updateAddressStmt.setString(5, address.getZip());
            updateAddressStmt.setString(6, address.getAddressID());
            updateAddressStmt.executeUpdate();

            updateCandidateStmt.setString(1, candidate.getFirstName());
            updateCandidateStmt.setString(2, candidate.getLastName());
            updateCandidateStmt.setString(3, candidate.getPhoneNumber());
            updateCandidateStmt.setString(4, candidate.getEmail());
            updateCandidateStmt.setString(5, candidate.getTotalAssets());
            updateCandidateStmt.setString(6, candidate.getAccountNumber());
            updateCandidateStmt.setString(7, candidate.getAccountHoldingBank());
             updateCandidateStmt.setString(8, candidate.getCandidateID());
            updateCandidateStmt.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(updateAddressStmt);
            DBUtil.closePreparedStatement(updateCandidateStmt);
            pool.freeConnection(connection);
        }
    }
}
