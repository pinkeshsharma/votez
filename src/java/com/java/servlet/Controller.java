/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.servlet;

import com.java.dao.ElectionDB;
import com.java.dao.UserDB;
import com.java.model.Address;
import com.java.model.Admin;
import com.java.model.Candidate;
import com.java.model.Election;
import com.java.model.Party;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.java.model.User;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Pinkesh
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        User user = (User) session.getAttribute("User");
        Election currentElection = (Election) session.getAttribute("Election");
        Admin admin = (Admin) session.getAttribute("admin");
        UserDB userDB = new UserDB();
        ElectionDB electionDb = new ElectionDB();
        String url;
        Candidate loggedInCandidate = (Candidate) session.getAttribute("loggedInCandidate");

        /**
         * User Actions
         */
        if (action == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("login_pg".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else if ("signup".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "signup.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("create".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user != null) {
                url = "main.jsp";
            } else {
                String firstName = String.valueOf(request.getParameter("firstname"));
                String lastName = String.valueOf(request.getParameter("lastname"));
                String email = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));
                String apartment = String.valueOf(request.getParameter("apartment"));
                String city = String.valueOf(request.getParameter("city"));
                String state = String.valueOf(request.getParameter("state"));
                String postalCode = String.valueOf(request.getParameter("postalCode"));
                String phonenumber = String.valueOf(request.getParameter("phonenumber"));
                String voterid = String.valueOf(request.getParameter("voterid"));
                String age = String.valueOf(request.getParameter("age"));

                User newUser = new User(email, firstName, lastName, email, phonenumber, voterid, age, new Address(state, apartment, city, state, postalCode));

                if (userDB.emailExists(email)) {
                    url = "signup.jsp";
                    request.setAttribute("msg", "User already exists!");
                } else {
                    String status = userDB.createUser(newUser, password);
                    if ("User is registered successfully to vote".equalsIgnoreCase(status)) {
                        url = "main.jsp";
                        User loggedInUser = userDB.getUser(email);
                        session.setAttribute("User", loggedInUser);
                        Election election = electionDb.getTodaysElectionDetailsForCurrentUser(loggedInUser.getAddress().getCity(), loggedInUser.getUserId());
                        session.setAttribute("Election", election);
                    } else {
                        url = "signup.jsp";
                        request.setAttribute("msg", status);
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("login".equalsIgnoreCase(action)) {
            if (user != null) {
                url = "main.jsp";
            } else {
                String email = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));
                User loggedInUser = userDB.getUser(email, password);
                if (loggedInUser != null) {
                    url = "main.jsp";
                    session.setAttribute("User", loggedInUser);
                    Election election = electionDb.getTodaysElectionDetailsForCurrentUser(loggedInUser.getAddress().getCity(), loggedInUser.getUserId());
                    session.setAttribute("Election", election);
                } else {
                    request.setAttribute("msg", "Invalid username and/or password.");
                    url = "login.jsp";
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("vote_page".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "vote.jsp";
            }
            Map<String, Candidate> candidates = electionDb.getListOfCandidates(currentElection.getElectionID());
            session.setAttribute("CandidateList", candidates);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("castvote".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            String message = "";
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "main.jsp";
                String electionID = (String) request.getParameter("electionID");
                String candidateID = (String) request.getParameter("selectedCandidate");
                String userID = user.getUserId();
                int status = electionDb.insertVote(electionID, candidateID, userID);
                if (status == 1) {
                    message = "Your vote is recorded successfully.";
                } else {
                    message = "Unable to record your vote. Please try again later.";
                }
                Election election = electionDb.getTodaysElectionDetailsForCurrentUser(user.getAddress().getCity(), user.getUserId());
                session.setAttribute("Election", election);
            }
            request.setAttribute("message"
                    + "", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("upcomingelections".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "upcomingelections.jsp";
            }
            List<Election> elections = electionDb.getUpcomingElections();
            session.setAttribute("upcomingelections", elections);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("pastelections".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "pastelections.jsp";
            }
            List<Election> elections = electionDb.getPastElections();
            session.setAttribute("pastelections", elections);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("electionvotingdetails_u".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "electionvotingdetails.jsp";
                String electionID = String.valueOf(request.getParameter("electionID"));
                List<Candidate> electionVotingDetails = electionDb.getElectionVotingDetails(electionID);
                session.setAttribute("electionVotingDetails", electionVotingDetails);
                session.setAttribute("currentElectionID", electionID);
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("candidateslist".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "candidateslist.jsp";
            }
            String electionID = String.valueOf(request.getParameter("electionID"));
            Map<String, Candidate> candidates = electionDb.getListOfCandidates(electionID);
            session.setAttribute("CandidateList", candidates);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("partylist".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "partylist.jsp";
            }
            List<Party> partyList = electionDb.getListOfParties();
            session.setAttribute("partyList", partyList);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("updateprofile".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "updateVoterProfile.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("main".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "login.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("agerangeparticipation".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "agerangeparticipation.jsp";
            }
            String electionID = String.valueOf(request.getParameter("electionID"));
            List<String> ageRangeParticipationValues = electionDb.getElectionAgeRangeParticipation(electionID);
            session.setAttribute("ageRangeParticipationValues", ageRangeParticipationValues);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("agerangeparticipation".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "agerangeparticipation.jsp";
            }
            String electionID = String.valueOf(request.getParameter("electionID"));
            List<String> AgeRangeParticipation = electionDb.getElectionAgeRangeParticipation(electionID);
            session.setAttribute("ageRangeParticipation", AgeRangeParticipation);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } /**
         * Candidate Actions
         */
        else if ("signup_c".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "signup_c.jsp";
            } else {
                url = "main_c.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("create_c".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user != null) {
                url = "main.jsp";
            } else {
                String firstName = String.valueOf(request.getParameter("firstname"));
                String lastName = String.valueOf(request.getParameter("lastname"));
                String email = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));
                String apartment = String.valueOf(request.getParameter("apartment"));
                String city = String.valueOf(request.getParameter("city"));
                String state = String.valueOf(request.getParameter("state"));
                String postalCode = String.valueOf(request.getParameter("postalCode"));
                String phonenumber = String.valueOf(request.getParameter("phonenumber"));
                String totalAssets = String.valueOf(request.getParameter("totalassets"));
                String accountHoldingBank = String.valueOf(request.getParameter("accountholdingbank"));
                String accountNumber = String.valueOf(request.getParameter("accountnumber"));
                String partyID = String.valueOf(request.getParameter("partyID"));

                Address address = new Address(state, apartment, city, state, postalCode);
                Candidate newCandidate = new Candidate("", firstName, lastName, "", phonenumber, email, partyID, totalAssets, accountNumber, accountHoldingBank);
                newCandidate.setAddress(address);

                if (userDB.emailExistsCandidate(email)) {
                    url = "signup_c.jsp";
                    request.setAttribute("msg", "Candidate already exists!");
                } else {
                    int status = userDB.createCandidate(newCandidate, password);
                    if (status == 1) {
                        url = "main_c.jsp";
                        loggedInCandidate = userDB.getCandidate(email, password);
                        session.setAttribute("loggedInCandidate", loggedInCandidate);
                    } else {
                        url = "signup.jsp";
                        request.setAttribute("msg", "Unable to create account, Please try again after sometime.");
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("login_candidate".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("candidate_login.jsp");
            rd.forward(request, response);
        } else if ("login_c".equalsIgnoreCase(action)) {
            if (loggedInCandidate != null) {
                url = "main_c.jsp";
            } else {
                String userId = String.valueOf(request.getParameter("email"));
                String password = String.valueOf(request.getParameter("password"));
                loggedInCandidate = userDB.getCandidate(userId, password);
                if (loggedInCandidate != null) {
                    url = "main_c.jsp";
                    session.setAttribute("loggedInCandidate", loggedInCandidate);
                } else {
                    request.setAttribute("msg", "Invalid username and/or password.");
                    url = "candidate_login.jsp";
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("upcomingelections_c".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (loggedInCandidate == null) {
                url = "home.jsp";
            } else {
                url = "upcomingelections_c.jsp";
            }
            List<Election> elections = electionDb.getUpcomingElections();
            session.setAttribute("upcomingelections", elections);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("home_c".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (loggedInCandidate == null) {
                url = "home.jsp";
            } else {
                url = "main_c.jsp";
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("updatecandidateprofile".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (loggedInCandidate == null) {
                url = "home.jsp";
            } else {
                url = "updateCandidateProfile.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("registerforelection".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (loggedInCandidate == null) {
                url = "home.jsp";
            } else {

                url = "main_c.jsp";
                String electionID = String.valueOf(request.getParameter("electionID"));
                int status = electionDb.registerForElection(electionID, loggedInCandidate.getCandidateID());
                if (status == 1) {
                    request.setAttribute("msg", "You have successfully registered for election.");
                } else {
                    request.setAttribute("msg", "Unable to register for election, Please try again after sometime.");
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("doupdatecandidateprofile".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user != null) {
                url = "main.jsp";
            } else {
                String firstName = String.valueOf(request.getParameter("firstname"));
                String lastName = String.valueOf(request.getParameter("lastname"));
                String email = String.valueOf(request.getParameter("email"));
                String apartment = String.valueOf(request.getParameter("apartment"));
                String city = String.valueOf(request.getParameter("city"));
                String state = String.valueOf(request.getParameter("state"));
                String postalCode = String.valueOf(request.getParameter("postalCode"));
                String phonenumber = String.valueOf(request.getParameter("phonenumber"));
                String totalAssets = String.valueOf(request.getParameter("totalassets"));
                String accountHoldingBank = String.valueOf(request.getParameter("accountholdingbank"));
                String accountNumber = String.valueOf(request.getParameter("accountnumber"));
                String partyID = String.valueOf(request.getParameter("partyID"));
                String AddressId = String.valueOf(request.getParameter("AddressId"));
                String CandidateId = String.valueOf(request.getParameter("CandidateId"));

                Address address = new Address(state, apartment, city, state, postalCode);
                address.setAddressID(AddressId);
                Candidate newCandidate = new Candidate(CandidateId, firstName, lastName, "", phonenumber, email, partyID, totalAssets, accountNumber, accountHoldingBank);
                newCandidate.setAddress(address);

                int status = userDB.updateCandidate(newCandidate);
                if (status == 1) {
                    url = "main_c.jsp";

                    request.setAttribute("msg", "Profile updated successfully.");
                    session.setAttribute("loggedInCandidate", userDB.getCandidate(email));
                } else {
                    url = "signup.jsp";
                    request.setAttribute("msg", "Unable to update profile, Please try again after sometime.");
                }

            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } /**
         * Admin Actions
         */
        else if ("login_admin".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
            rd.forward(request, response);
        } else if ("login_a".equalsIgnoreCase(action)) {
            if (admin != null) {
                url = "main_a.jsp";
            } else {
                String userId = String.valueOf(request.getParameter("userid"));
                String password = String.valueOf(request.getParameter("password"));
                Admin loggedInAdmin = userDB.getAdmin(userId, password);
                if (loggedInAdmin != null) {
                    url = "main_a.jsp";
                    session.setAttribute("admin", loggedInAdmin);
                } else {
                    request.setAttribute("msg", "Invalid username and/or password.");
                    url = "admin_login.jsp";
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addelection_pg".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "addelection.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addelection".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "addelection.jsp";
                Election election = new Election(null, String.valueOf(request.getParameter("electiontype")),
                        String.valueOf(request.getParameter("district")), String.valueOf(request.getParameter("timePeriod")),
                        String.valueOf(request.getParameter("state")), null);
                int status = electionDb.addElection(election);
                if (status == 1) {
                    request.setAttribute("msg", "You have successfully added election.");
                } else {
                    request.setAttribute("msg", "Unable to add election, Please try again after sometime.");
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addparty_pg".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "addparty.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addparty".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "main_a.jsp";

                String partyName = String.valueOf(request.getParameter("partyname"));
                String partySymbol = String.valueOf(request.getParameter("partysymbol"));
                String partyIdeology = String.valueOf(request.getParameter("partyideology"));
                String apartment = String.valueOf(request.getParameter("apartment"));
                String city = String.valueOf(request.getParameter("city"));
                String state = String.valueOf(request.getParameter("state"));
                String postalCode = String.valueOf(request.getParameter("postalCode"));
                String phonenumber = String.valueOf(request.getParameter("phonenumber"));
                String email = String.valueOf(request.getParameter("email"));
                String partyChairman = String.valueOf(request.getParameter("partychairman"));
                String founded = String.valueOf(request.getParameter("founded"));
                Address address = new Address(state, apartment, city, state, postalCode);
                Party party = new Party(address, partyName, partySymbol, partyIdeology, phonenumber, email, partyChairman, founded);
                String status = electionDb.addParty(party);
                request.setAttribute("msg", status);
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addadmin_pg".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "addadmin.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("addadmin".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "main_a.jsp";
                if ("admin".equalsIgnoreCase(admin.getRole())) {
                    Admin newAdmin = new Admin(null, String.valueOf(request.getParameter("firstname")),
                            String.valueOf(request.getParameter("lastname")), String.valueOf(request.getParameter("phonenumber")),
                            String.valueOf(request.getParameter("email")), String.valueOf(request.getParameter("role")));
                    int status = electionDb.addAdmin(newAdmin, String.valueOf(request.getParameter("password")));
                    if (status == 1) {
                        request.setAttribute("msg", "You have successfully added new Admin.");
                    } else {
                        request.setAttribute("msg", "Unable to add a new Admin, Please try again after sometime.");
                    }
                } else {
                    request.setAttribute("msg", "You are not authorized to add a new Admin.");
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("upcomingelections_a".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "upcomingelections_a.jsp";
            }
            List<Election> elections = electionDb.getUpcomingElections();
            session.setAttribute("upcomingelections", elections);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("pastelections_a".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "pastelections_a.jsp";
            }
            List<Election> elections = electionDb.getPastElections();
            session.setAttribute("pastelections", elections);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("determinewinner".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "pastelections_a.jsp";
                String electionId = String.valueOf(request.getParameter("electionIDd"));
                int status = electionDb.setElectionWinner(electionId);
                if (status == 1) {
                    request.setAttribute("msg", "Operation completed Successfully.");
                } else {
                    request.setAttribute("msg", "Unable to perform specified operation, Please try again after sometime.");
                }
                List<Election> elections = electionDb.getPastElections();
                session.setAttribute("pastelections", elections);
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("upcomingelectionstats".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "upcomingElectionStats.jsp";
                String state = String.valueOf(request.getParameter("electionID"));
                Map<String, String> result = electionDb.getPartyPerformance(state);
                session.setAttribute("result", result);
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("electionvotingdetails".equalsIgnoreCase(action) && user == null) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home_a.jsp";
            } else {
                url = "electionvotingdetails_a.jsp";
                String electionID = String.valueOf(request.getParameter("electionIDd"));
                List<Candidate> electionVotingDetails = electionDb.getElectionVotingDetails(electionID);
                session.setAttribute("electionVotingDetails", electionVotingDetails);
                session.setAttribute("currentElectionID", electionID);
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("votersparticipation".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "votersparticipation.jsp";
            }
            String electionID = String.valueOf(request.getParameter("electionID"));
            List<User> votersParticipation = electionDb.getVoterParticipationInElection(electionID);
            session.setAttribute("votersParticipation", votersParticipation);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("candidateslist_a".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "candidateslist_a.jsp";
            }
            String electionID = String.valueOf(request.getParameter("electionID"));
            Map<String, Candidate> candidates = electionDb.getListOfCandidates(electionID);
            session.setAttribute("CandidateList", candidates);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("allelectionDetail".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "allElectionData.jsp";
            }
            List<Candidate> candidates = electionDb.getAllElectionDetails();
            session.setAttribute("allElectionData", candidates);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("allelectionDetailByWinners".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "allelectionDetailByWinners.jsp";
            }
            List<Candidate> candidates = electionDb.getElectionDetailsByWinners();
            session.setAttribute("allelectionDetailByWinners", candidates);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("partyPerformanceAllOver".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "partyPerformanceAllOver.jsp";
            }
            List<Party> partys = electionDb.getPartyPerformanceAllOver();
            session.setAttribute("partyPerformanceAllOver", partys);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("partyPerformanceStateWise".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (admin == null) {
                url = "home.jsp";
            } else {
                url = "partyPerformanceStateWise.jsp";
            }
            List<Party> partys = electionDb.getPartyPerformanceStateWise();
            session.setAttribute("partyPerformanceStateWise", partys);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } /**
         * Common
         *
         */
        else if ("logout".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            url = "home.jsp";
            session.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if ("home".equalsIgnoreCase(action)) {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null && loggedInCandidate == null && admin == null) {
                url = "home.jsp";
            } else if (user != null) {
                url = "main.jsp";
            } else if (loggedInCandidate != null) {
                url = "main_c.jsp";
            } else if (admin != null) {
                url = "main_a.jsp";
            } else {
                url = "home.jsp";
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            if (user == null) {
                url = "home.jsp";
            } else {
                url = "main.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
