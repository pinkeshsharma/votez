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
%>

<table class="table width_80">
        <thead>
        <th>Name of Candidate</th>
        <th>Party</th>
        <th>Email</th>
        <th>Total Asset</th>
        </thead>
        <tfoot>
            <%if (candidates != null && candidates.size() > 0) {
                    for (String candidateID : candidates.keySet()) {
                        Candidate candidate = candidates.get(candidateID);
            %>
            <tr>
                <td class="signup_input"><%=candidate.getLastName() + ", " + candidate.getFirstName()%></td>
                <td class="signup_input"><%=candidate.getParty().getPartyName()%></td>
                <td class="signup_input"><%=candidate.getEmail()%></td>
                <td class="signup_input"><%=candidate.getTotalAssets()%></td>
            </tr>
            <%}
            } else {%>
            <tr>
                <td colspan="4">No candidate registered for this election.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>
<jsp:include page="footer.jsp" />
