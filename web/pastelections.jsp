<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Past Elections </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>      
<%
    List<Election> upcomingElections = (List) session.getAttribute("pastelections");
%>
<form action="" method="POST" id="submitform" class="" >
    <input id="electionID" name="electionID" type="hidden" value=""/>
    <div>${msg}</div>
    <table class="table width_80">
        <thead>
        <th>ID</th>
        <th>Type</th>
        <th>Date</th>
        <th>City</th>
        <th>State</th>
        <th>Winner</th>
        <th>Voting Detail</th>
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
                <%if (election.getElectionWinner() == null || "null".equalsIgnoreCase(election.getElectionWinner()) || "null null".equalsIgnoreCase(election.getElectionWinner())) {%>
                <td >--</td>    

                <%} else {%>
                <td ><%=election.getElectionWinner()%></td>

                <%}%>
                <td ><button  onclick="submitFormWithAction('electionID', '<%=election.getElectionID()%>', 'submitform', 'controller?action=electionvotingdetails_u');
                        return 0;">Click here for detailed voting results.</button></td>
            </tr>
            <%}
            } else {%>
            <tr>
                <td colspan="5">No upcoming elections found.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>
</form>
<jsp:include page="footer.jsp" />
