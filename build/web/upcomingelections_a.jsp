<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Upcoming Elections </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>      
<%
    List<Election> upcomingElections = (List) session.getAttribute("upcomingelections");
%>
<form action="" method="POST" id="submitform" class="" > 
    <input id="electionID" name="electionID" type="hidden" value=""/>

    <table class="table width_80">
        <thead>
        <th>ID</th>
        <th>Type</th>
        <th>Date</th>
        <th>City</th>
        <th>State</th>
        <th>Candidate List</th>
        <th>Party Performance in State</th>
        </thead>
        <tfoot>
            <%if (upcomingElections != null && upcomingElections.size() > 0) {
                    for (Election election : upcomingElections) {

            %>
            <tr>
                <td ><%=election.getElectionID()%></td>
                <td ><%=election.getElectionType()%></td>
                <td ><%=election.getTimePeriod()%></td>
                <td ><%=election.getDistrict()%></td>
                <td ><%=election.getState()%></td>
                <td><button  onclick="submitFormWithAction('electionID', '<%=election.getElectionID()%>', 'submitform', 'controller?action=candidateslist_a');
                        return 0;">Click here for the list of Candidates</button></td>
                <td><button  onclick="submitFormWithAction('electionID', '<%=election.getState()%>', 'submitform', 'controller?action=upcomingelectionstats');
                        return 0;">Click here for Stats</button></td>
            </tr>
            <%}
    } else {%>
            <tr>
                <td colspan="5">No upcoming elections found.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>
</div>
<jsp:include page="footer.jsp" />
