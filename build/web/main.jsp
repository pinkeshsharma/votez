<%@page import="com.java.model.Election"%>

<jsp:include page="header.jsp" />
<div class="side_left">
    <jsp:include page="sidemenu.jsp" />
</div>
<div class="side_right">
    <div>${User.userId}</div>
    <%
        Election election = (Election) session.getAttribute("Election");
        if (election != null) {
    %>
    <div class="msg_div">${message}</div>
    <div><p class="main_para"><b>Click <a href="<%=request.getContextPath() + "/controller?action=vote_page"%>">HERE</a> </b> to go to the voting page for
            <%=election.getDistrict()%> election. </p></div>
        <%
            } else {
        %>
        <p class="main_para"><b>Votez</b><br><br>You have voted for today's election.<br><br></p>
         <%
            }
        %>
</div>
<jsp:include page="footer.jsp" />