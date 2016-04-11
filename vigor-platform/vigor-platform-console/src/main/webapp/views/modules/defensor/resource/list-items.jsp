<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:forEach items="${resourceList}" var="resource">
    <tr data-tt-id="${resource.id}" data-tt-parent-id="${resource.parent.id}" data-tt-branch="${fn:length(resource.children) > 0}">
        <td><c:if test="${resource.icon != null and resource.icon != ''}"><i class="${resource.icon}"></i></c:if> <c:out value="${resource.name}" /></td>
        <td><spring:message code="app.resource.title.resourceType.${resource.resourceType}" text="" /></td>
        <td><span><c:out value="${resource.resourceUrl}" /></span></td>
        <td><spring:message code="app.resource.title.resourceTarget.${resource.target}" text="" /></td>
        <td><c:out value="${resource.privilege}" /></td>
        <td><spring:message code="app.common.type.YesOrNo.${resource.isShow}" text="" /></td>
        <td>
            <a href="${contextPath}/resource/edit?id=${resource.id}" class="btn btn-success btn-sm"><i class="fa fa-edit"></i> <spring:message code="app.common.action.edit" /></a>
            <a href="${contextPath}/resource/add?parent.id=${resource.id}" class="btn btn-success btn-sm"><i class="fa fa-list-alt"></i> <spring:message code="app.resource.action.addChild" /></a>
            <a href="javascript:void(0);" class="btn btn-danger btn-sm btn-delete"><i class="fa fa-trash-o"></i> <spring:message code="app.common.action.delete" /></a>
        </td>
    </tr>
    <c:if test="${fn:length(resource.children) > 0}">
        <c:set var="resourceList" value="${resource.children}" scope="request" />
        <c:import url="list-items.jsp" />
    </c:if>
</c:forEach>