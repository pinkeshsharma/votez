<%@page import="com.java.model.Admin"%>


<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<div>
    <ul class="side_menu_items">
        <%-- TO DO --%>
        <li><a href="<%=request.getContextPath() + "/controller?action=home"%>">Home</a></li>   
        <%if ("admin".equalsIgnoreCase(admin.getRole())) {%>
        <li><a href="<%=request.getContextPath() + "/controller?action=addelection_pg"%>">Add Election</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=addparty_pg"%>">Add Party</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=addadmin_pg"%>">Add System User</a></li>
        <%}%>
        <li><a href="<%=request.getContextPath() + "/controller?action=upcomingelections_a"%>">Upcoming Elections</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=pastelections_a"%>">Past Elections</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=allelectionDetail"%>">All Elections Details</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=allelectionDetailByWinners"%>">Party Election Details By Winners</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=partyPerformanceAllOver"%>">Party Performance (All Over)</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=partyPerformanceStateWise"%>">Party Performance (State Wise)</a></li>
        <li><a href="<%=request.getContextPath() + "/controller?action=logout"%>">Logout</a></li>
    </ul>
</div>
