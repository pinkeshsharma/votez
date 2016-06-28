<%@page import="com.java.model.Party"%>
<%@page import="com.java.model.Election"%>
<%@page import="java.util.Map"%>
<%@page import="com.java.model.Candidate"%>
<%@page import="java.util.List"%>

<jsp:include page="header.jsp" />
<div class="inline">
    <div class="top_page">
        <span class="bck_grey"> Party Perform (State Wise) </span>
    </div>
    <div class="top_breadcrum">
        <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
    </div>
</div>
<%
    List<Party> partys = (List) session.getAttribute("partyPerformanceStateWise");
%>

<table class="table width_80">
    <thead>
    <th>Party Name</th>
    <th>Total Election Won</th>
    <th>State</th>
</thead>
<tfoot>
    <%if (partys != null && partys.size() > 0) {
            for (Party party : partys) {
    %>
    <tr>
        <td class="signup_input"><%=party.getPartyName()%></td>
        <td class="signup_input"><%=party.getCount()%></td>
        <%=party.getEmail().equalsIgnoreCase("AL") ? "<td class='signup_input'>Al-Alabama</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("AK") ? "<td class='signup_input'>Ak-Alaska</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("AZ") ? "<td class='signup_input'>AZ-Arizona</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("AR") ? "<td class='signup_input'>AR-Arkansas</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("CA") ? "<td class='signup_input'>CA-California</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("CO") ? "<td class='signup_input'>CO-Colorado</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("CT") ? "<td class='signup_input'>CT-Connecticut</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("DE") ? "<td class='signup_input'>DE-Delaware</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("DC") ? "<td class='signup_input'>DC-District Of Columbia</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("FL") ? "<td class='signup_input'>FL-Florida</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("GA") ? "<td class='signup_input'>GA-Georgia</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("HI") ? "<td class='signup_input'>HI-Hawaii</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("ID") ? "<td class='signup_input'>ID-Idaho</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("IL") ? "<td class='signup_input'>IL-Illinois</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("IN") ? "<td class='signup_input'>IN-Indiana</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("IA") ? "<td class='signup_input'>IA-Iowa</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("KS") ? "<td class='signup_input'>KS-Kansas</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("KY") ? "<td class='signup_input'>KY-Kentucky</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("LA") ? "<td class='signup_input'>LA-Louisiana</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("ME") ? "<td class='signup_input'>ME-Maine</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MD") ? "<td class='signup_input'>MD-Maryland</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MA") ? "<td class='signup_input'>MA-Massachusetts</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MI") ? "<td class='signup_input'>MI-Michigan</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MN") ? "<td class='signup_input'>MN-Minnesota</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MS") ? "<td class='signup_input'>MS-Mississippi</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MO") ? "<td class='signup_input'>MO-Missouri</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MT") ? "<td class='signup_input'>MT-Montana</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NE") ? "<td class='signup_input'>NE-Nebraska</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NV") ? "<td class='signup_input'>NV-Nevada</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NH") ? "<td class='signup_input'>NH-New Hampshire</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NJ") ? "<td class='signup_input'>NJ-New Jersey</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NM") ? "<td class='signup_input'>NM-New Mexico</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NY") ? "<td class='signup_input'>NY-New York</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("NC") ? "<td class='signup_input'>NC-North Carolina</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("ND") ? "<td class='signup_input'>ND-North Dakota</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("OH") ? "<td class='signup_input'>OH-Ohio</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("OK") ? "<td class='signup_input'>OK-Oklahoma</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("OR") ? "<td class='signup_input'>OR-Oregon</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("PA") ? "<td class='signup_input'>PA-Pennsylvania</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("RI") ? "<td class='signup_input'>RI-Rhode Island</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("SC") ? "<td class='signup_input'>SC-South Carolina</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("SD") ? "<td class='signup_input'>SD-South Dakota</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("TN") ? "<td class='signup_input'>TN-Tennessee</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("TX") ? "<td class='signup_input'>TX-Texas</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("UT") ? "<td class='signup_input'>UT-Utah</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("VT") ? "<td class='signup_input'>VT-Vermont</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("VA") ? "<td class='signup_input'>VA-Virginia</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("WA") ? "<td class='signup_input'>WA-Washington</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("WV") ? "<td class='signup_input'>WV-West Virginia</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("WI") ? "<td class='signup_input'>WI-Wisconsin</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("WY") ? "<td class='signup_input'>WY-Wyoming</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("AS") ? "<td class='signup_input'>AS-American Samoa</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("GU") ? "<td class='signup_input'>GU-Guam</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("MP") ? "<td class='signup_input'>MP-Northern Mariana Islands</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("PR") ? "<td class='signup_input'>PR-Puerto Rico</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("UM") ? "<td class='signup_input'>UM-United States Minor Outlying Islands</td>" : ""%>
        <%=party.getEmail().equalsIgnoreCase("VI") ? "<td class='signup_input'>VI-Virgin Islands</td>" : ""%>
        
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
