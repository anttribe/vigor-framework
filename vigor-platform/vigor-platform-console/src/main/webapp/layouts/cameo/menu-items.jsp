<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:forEach items="${menus}" var="menu">
    <c:if test="${menu.resourceType == 'MENU' or menu.resourceType == 'PAGE'}">
        <li class="<c:if test="${fn:length(menu.children) > 0}">dropdown</c:if>">
            <c:choose>
                <c:when test="${fn:length(menu.children) == 0 and menu.resourceUrl != null and menu.resourceUrl != ''}">
                    <a href="javascript:;" data-href="${menu.resourceUrl}" target="_${fn:toLowerCase(menu.target)}" class="menu-item">
                </c:when>
                <c:otherwise>
                    <a href="javascript:;" data-toggle="dropdown">
                </c:otherwise>
            </c:choose>
                <c:if test="${menu.icon != null and menu.icon != ''}"><i class="${menu.icon}"></i> </c:if>
                <span><c:out value="${menu.name}" /></span>
                <c:if test="${fn:length(menu.children) > 0}"><i class="toggle-accordion"></i></c:if>
            </a>
	        <c:if test="${fn:length(menu.children) > 0}">
                <c:set var="menus" value="${menu.children}" scope="request" />
                <c:set var="level" value="${level + 1}" scope="request" />
                <ul class="dropdown-menu">
                    <c:import url="menu-items.jsp" />
                </ul>
            </c:if>
	    </li>
    </c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" />