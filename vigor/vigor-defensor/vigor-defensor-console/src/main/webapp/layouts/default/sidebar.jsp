<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- left side start-->
<div class="left-side sticky-left-side">
    <!--logo and iconic logo start-->
    <div class="logo">
        <a href="${contextPath}/"><img src="${contextPath}/static/img/logo.png" alt=""></a>
    </div>
    <div class="logo-icon text-center">
        <a href="${contextPath}/"><img src="${contextPath}/static/img/logo_icon.png" alt=""></a>
    </div>
    <!--logo and iconic logo end-->
    
    <div class="left-side-inner">
        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav">
            <c:set var="menus" value="${MENUS}" scope="session" />
            <c:import url="menu-items.jsp" />    
        </ul>
        <!--sidebar nav end-->
    </div>
</div>
<!-- left side end-->