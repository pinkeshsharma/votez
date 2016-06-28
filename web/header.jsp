<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Votez</title>
        <link rel="stylesheet" href="styles/common.css">
        <link rel="stylesheet" href="styles/datatables.min.css">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script src="script/jquery-1.11.3.min.js"></script>
        <script src="script/jquery.dataTables.min.js"></script>
        <script src="script/common_script.js"></script>
    </head>
    <body>
        <div class="top">
            <div>
                <span><a href="<%=request.getContextPath() + "/controller?action=home"%>">Votez</a></span>
                <c:if test="${User != null}">
                    <div class="top_box">
                        <div>Hello, ${User.firstName} ${User.lastName}</div>
                    </div>
                </c:if> 
                <c:if test="${admin != null}">
                    <div class="top_box">
                        <div>Hello, ${admin.firstName} ${admin.lastName}</div>
                    </div>
                </c:if> 
                <c:if test="${loggedInCandidate != null}">
                    <div class="top_box">
                        <div>Hello, ${loggedInCandidate.firstName} ${loggedInCandidate.lastName}</div>
                    </div>
                </c:if> 
                <c:if test="${User == null && admin == null && loggedInCandidate == null}">
                    <div class="top_box">
                        <a href="<%=request.getContextPath() + "/controller?action=login_candidate"%>">Candidate Log in</a>
                    </div>
                    <div class="top_box">
                        <a href="<%=request.getContextPath() + "/controller?action=login_admin"%>">Admin Log in</a>
                    </div>
                </c:if>
            </div>
        </div>