<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Cast Vote </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<%
    Map<String, Candidate> candidates = (Map) session.getAttribute("CandidateList");
    Election election = (Election) session.getAttribute("Election");
%>
<form action="controller?action=castvote" method="POST" id="voteForm" class="" id=''>
    <input id="electionID" name="electionID" type="hidden" value="<%=election.getElectionID()%>"/>
    <input id="selectedCandidate" name="selectedCandidate" type="hidden" value=""/>
    <table class="table">
        <thead>
        <th>Name of Candidate</th>
        <th>Party</th>
        <th>Action</th>
        </thead>
        <tfoot>
            <%if (candidates != null && candidates.size() > 0) {
                    for (String candidateID : candidates.keySet()) {
                        Candidate candidate = candidates.get(candidateID);
            %>
            <tr>
                <td class="signup_input"><%=candidate.getLastName() + ", " + candidate.getFirstName()%></td>
                <%if (candidate.getPartyID() == null) {%>
                <td class="signup_input">Independent</td>
                <%} else {%>
                <td class="signup_input"><a href=''><%=candidate.getPartyID()%></a></td>
                    <%}%>
                <td class="signup_input"><input type="radio" name="candidate" value="<%=candidateID%>"></td>
            </tr>
            <%}%>
            <tr>
                <td colspan="4">
                    <input type="submit" onclick="submitVote();return false;" class="button" value="Submit Vote" name="Submit Vote" />
                </td>
            <tr>
                <%} else {%>
            <tr>
                <td colspan="4">No candidate registered for this election.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>

</form>
<jsp:include page="footer.jsp" />
