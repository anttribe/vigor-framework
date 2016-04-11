<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- header section start -->
<div class="header header-fixed navbar">
    <div class="brand">
        <a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>
        <a href="${contextPath}/" class="navbar-brand text-white">
            <i class="fa fa-stop mg-r-sm"></i>
            <span class="heading-font"><spring:message code="app.appname" /></span>
        </a>
    </div>
    <form class="navbar-form navbar-left hidden-xs" role="search">
        <div class="form-group">
            <button class="btn no-border no-margin bg-none no-pd-l" type="submit"><i class="fa fa-search"></i></button>
            <input type="text" class="form-control no-border no-padding search" placeholder="Search Workspace">
        </div>
    </form>
    
    <ul class="nav navbar-nav navbar-right off-right">
    <c:if test="${null != USERINFO}">
        <li class="quickmenu">
            <a href="javascript:;" data-toggle="dropdown">
                <img src="${contextPath}/static/img/photos/user-avatar.png" alt="avatar" class="avatar pull-left img-circle" />
                <i class="caret mg-l-xs hidden-xs no-margin"></i>
            </a>
            <ul class="dropdown-menu dropdown-menu-right mg-r-xs">
                <li>
                    <a href="javascript:;"><div class="pd-t-sm"><c:out value="${USERINFO.username}" /></div></a>
                </li>
                <li><a href="profile.html"><i class="fa fa-user"></i> Profile</a></li>
                <li><a href="javascript:;"><i class="fa fa-cog"></i> Settings</a></li>
                <li class="divider"></li>
                <li><a href="${contextPath}/signout"><i class="fa fa-sign-out"></i> <spring:message code="app.user.title.signout" /></a></li>
            </ul>
        </li>
    </c:if>
</div>
<!-- header section end-->