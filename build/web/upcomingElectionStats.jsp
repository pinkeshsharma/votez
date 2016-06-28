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
                width: 57%;
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
            Map<String, String> result = (Map) session.getAttribute("result");
        %>
        <div class="top">
            <div>
                <span><a href="<%=request.getContextPath() + "/controller?action=home"%>">Votez</a></span>
                <c:if test="${admin != null}">
                    <div class="top_box">
                        <div>Hello, ${admin.firstName} ${admin.lastName}</div>
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
                <div style="height: 100%;" class="bar"></div>
                <div style="height: 13%;" class="bar"></div>
                <div style="height: 40%;" class="bar"></div>
            </div>
            <div class="pad_3">
                <%for (String key : result.keySet()) {%>
                    <div style="height: 0px;" class="bar"><%=result.get(key)%></div>
                <%}%>
            </div>
        </div>

