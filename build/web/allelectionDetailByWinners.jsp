<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Party Election Details By Winners </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<%
    List<Candidate> candidates  = (List) session.getAttribute("allelectionDetailByWinners");
%>

<table class="table width_80">
        <thead>
        <th>Name of Candidate</th>
        <th>Party</th>
        <th>Election Type</th>
        <th>District</th>
        <th>Time Period</th>
        </thead>
        <tfoot>
            <%if (candidates != null && candidates.size() > 0) {
                    for (Candidate candidate : candidates) {
            %>
            <tr>
                <td class="signup_input"><%=candidate.getLastName() + ", " + candidate.getFirstName()%></td>
                <td class="signup_input"><%=candidate.getParty().getPartyName()%></td>
                <td class="signup_input"><%=candidate.getElection().getElectionType()%></td>
                <td class="signup_input"><%=candidate.getElection().getDistrict()%></td>
                <td class="signup_input"><%=candidate.getElection().getTimePeriod()%></td>
            </tr>
            <%}
            } else {%>
            <tr>
                <td colspan="5">No data found.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>
<jsp:include page="footer.jsp" />
