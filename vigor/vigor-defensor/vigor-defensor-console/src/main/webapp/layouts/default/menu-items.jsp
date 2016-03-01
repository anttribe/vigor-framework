<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:forEach items="${menus}" var="menu">
    <c:if test="${menu.resourceType == 'MENU' or menu.resourceType == 'PAGE'}">
        <li class="<c:if test="${fn:length(menu.children) > 0}">menu-list</c:if>">
            <a href="${menu.resourceType == 'MENU' ? contextPath : ''}${menu.resourceUrl}" targat="${menu.target}"><i class="${menu.icon}"></i> <span class="title"><c:out value="${menu.name}" /></span></a>
	        <c:if test="${fn:length(menu.children) > 0}">
                <c:set var="menus" value="${menu.children}" scope="request" />
                <ul class="sub-menu-list">
                    <c:import url="menu-items.jsp" />
                </ul>
            </c:if>
	    </li>
    </c:if>
</c:forEach>