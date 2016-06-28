<%@page import="com.java.model.Party"%>
<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Party Perform (All Over) </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<%
    List<Party> partys = (List) session.getAttribute("partyPerformanceAllOver");
%>

<table class="table width_80">
        <thead>
        <th>Party Name</th>
        <th>Total Election Won</th>
        
        </thead>
        <tfoot>
            <%if (partys != null && partys.size() > 0) {
                    for (Party party : partys) {
            %>
            <tr>
                <td class="signup_input"><%=party.getPartyName()%></td>
                <td class="signup_input"><%=party.getCount()%></td>
            </tr>
            <%}
            } else {%>
            <tr>
                <td colspan="2">No data found.</td>
            </tr>
            <%}%>
        </tfoot>    

    </table>
<jsp:include page="footer.jsp" />
