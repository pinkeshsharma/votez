<%@page import="com.java.model.User"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>


<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Voters Details </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>      
<%
    List<User> votersParticipation = (List) session.getAttribute("votersParticipation");
%>
<table class="table width_80">
    <thead>
    <th>Party</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Created Date</th>
</thead>
<tfoot>
    <%if (votersParticipation != null && votersParticipation.size() > 0) {
            for (User user : votersParticipation) {
    %>
    <tr>
        <td ><%=user.getUserId()%></td>
        <td ><%=user.getFirstName()%></td>
        <td ><%=user.getLastName()%></td>
        <td ><%=user.getCreatedDate()%></td>
    </tr>
    <%}%>
        <%} else {%>
    <tr>
        <td colspan="4">No data found.</td>
    </tr>
    <%}%>
</tfoot>    

</table>

<jsp:include page="footer.jsp" />
