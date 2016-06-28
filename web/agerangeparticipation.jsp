<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Votez</title>
        <script src="script/common_script.js"></script>
        <link rel="stylesheet" href="styles/common.css">
        <style>
            .pad_10 {
                padding: 5% 5% 0% 15%;
            }
            .graph {
                width: 77%;
                height: 200px;
                border: 1px solid #aeaeae;
                bottom: -100px;
                background-color: #eaeaea;
            }
            .bar {
                width: 200px;
                margin: 1px;
                display: inline-block;
                position: relative;
                background-color: #9D9;
                vertical-align: baseline;
            }
        </style>
    </head>
    <body>
        <%
            List<String> ageRangeParticipationValues = (List) session.getAttribute("ageRangeParticipationValues");
            List<String> sortedList = new ArrayList<>(ageRangeParticipationValues);
            Collections.sort(sortedList);
            String max = sortedList.get(sortedList.size() - 1);
        %>
        <div class="top">
            <div>
                <span><a href="<%=request.getContextPath() + "/controller?action=home"%>">Votez</a></span>
                <c:if test="${admin != null}">
                    <div class="top_box">
                        <div>Hello, ${User.firstName} ${User.lastName}</div>
                    </div>
                </c:if> 
            </div>
        </div>
        <div class="inline">
            <div class="top_breadcrum">
                <a href="<%=request.getContextPath() + "/controller?action=home"%>" class="text_150">&#9754; Back to main page</a>
            </div>
        </div>  
        <div class="pad_10">
            <div class="graph pad_3">
                <%if (max == ageRangeParticipationValues.get(0)) {%>
                <div style="height: 100%;" class="bar"></div>
                <%} else {
                     int percent = ((Integer.parseInt(ageRangeParticipationValues.get(2)))  * 100) / Integer.parseInt(max);
                %>
                <div style="height: <%=percent%>%;" class="bar"></div>
                <%}
                    if (max == ageRangeParticipationValues.get(1)) {%>
                <div style="height: 100%;" class="bar"></div>
                <%} else {
                     int percent = ((Integer.parseInt(ageRangeParticipationValues.get(2)))  * 100) / Integer.parseInt(max);
                %>
                <div style="height: <%=percent%>%;" class="bar"></div>
                <%}
                    if (max == ageRangeParticipationValues.get(2)) {%>
                <div style="height: 100%;" class="bar"></div>
                <%} else {
                    int percent = ((Integer.parseInt(ageRangeParticipationValues.get(2)))  * 100) / Integer.parseInt(max);
                %>
                <div style="height: <%=percent%>%;" class="bar"></div>
                <%}
                    if (max == ageRangeParticipationValues.get(3)) {%>
                <div style="height: 100%;" class="bar"></div>
                <%} else {
                     int percent = ((Integer.parseInt(ageRangeParticipationValues.get(2)))  * 100) / Integer.parseInt(max);
                %>
                <div style="height: <%=percent%>%;" class="bar"></div>
                <%}%>

            </div>
            <div class="pad_3">
                <div style="height: 0px;" class="bar pad_left_3">18-30 (<%=ageRangeParticipationValues.get(0)%>)</div>
                <div style="height: 0px;" class="bar pad_left_3">31-45 (<%=ageRangeParticipationValues.get(1)%>)</div>
                <div style="height: 0px;" class="bar pad_left_3">46-60 (<%=ageRangeParticipationValues.get(2)%>)</div>
                <div style="height: 0px;" class="bar pad_left_3">Over 60 (<%=ageRangeParticipationValues.get(3)%>)</div>
            </div>
        </div>

