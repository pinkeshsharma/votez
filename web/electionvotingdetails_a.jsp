<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Voting Details </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>      
<%
    List<Candidate> votingDetails = (List) session.getAttribute("electionVotingDetails");
%>
<table class="table width_80">
    <thead>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Party</th>
    <th>Vote Count</th>
</thead>
<tfoot>
    <%if (votingDetails != null && votingDetails.size() > 0) {
            for (Candidate candidate : votingDetails) {
    %>
    <tr>
        <td ><%=candidate.getFirstName()%></td>
        <td ><%=candidate.getLastName()%></td>
        <td ><%=candidate.getParty().getPartyName()%></td>
        <td ><%=candidate.getCount()%></td>
    </tr>
    <%}%>
        <%} else {%>
    <tr>
        <td colspan="4">No data found.</td>
    </tr>
    <%}%>
</tfoot>    

</table>

<form action="controller?action=votersparticipation" method="POST" id="submitForm" class="signup_center pad_3" id=''>
    <input id="electionID" name="electionID" type="hidden" value="<%=session.getAttribute("currentElectionID")%>"/>
    <input type="submit" onclick="submit('submitForm');return false;" class="button" value="Get Voter Details" name="Get Voter Details" />
</form>
<jsp:include page="footer.jsp" />
