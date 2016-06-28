<%@page import="com.java.model.Party"%>
<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>

<jsp:include page="header.jsp" />
<div class="">
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<div>        
    <%
        List<Party> partyList = (List) session.getAttribute("partyList");
    %>
    <table class="table">
        <thead>
        <th>Name</th>
        <th>Symbol</th>
        <th>Ideology</th>
        <th>Chairperson</th>
        <th>Founded</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Address</th>
        </thead>
        <tfoot>
            <%if (partyList != null && partyList.size() > 0) {
                    for (Party party : partyList) {
            %>
            <tr>
                <td class="signup_input"><%=party.getPartyName().equalsIgnoreCase("Null")  ? "" : party.getPartyName()%></td>
                <td class="signup_input"><%=party.getPartySymbol().equalsIgnoreCase("Null")  ? "" : party.getPartySymbol()%></td>
                <td class="signup_input"><%=party.getPartyIdeology().equalsIgnoreCase("Null")  ? "" : party.getPartyIdeology()%></td>
                <td class="signup_input"><%=party.getPartyChairperson().equalsIgnoreCase("Null") ? "" : party.getPartyChairperson()%></td>
                <td class="signup_input"><%=party.getFounded().equalsIgnoreCase("Null")  ? "" : party.getFounded()%></td>
                <td class="signup_input"><%=party.getPhoneNumber().equalsIgnoreCase("Null") ? "" : party.getPhoneNumber()%></td>
                <td class="signup_input"><%=party.getEmail().equalsIgnoreCase("Null")  ? "" : party.getEmail()%></td>
                <td class="signup_input"><%=party.getAddress().getApartment() + ", " + party.getAddress().getStreet() + ", " +
                party.getAddress().getCity() + ", " + party.getAddress().getState() + " - " + party.getAddress().getZip()%></td>
            </tr>
            <%}
            } else {%>
            <tr>
                <td colspan="4">No party data found.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>

</div>
<jsp:include page="footer.jsp" />
