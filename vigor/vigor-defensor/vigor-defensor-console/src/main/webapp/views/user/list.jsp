<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.user.title" /></title>
    </head>
    <body>
        <div class="clearfix"></div>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-sm-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span><spring:message code="app.user.title" /></span>
                            <span class="tools pull-right"></span>
                        </header>
                        <div class="panel-body">
                            <div class="btn-group">
                                <a href="#none" class="btn btn-primary btn-add"><i class="fa fa-plus"></i> <spring:message code="app.user.action.add" /></a>
                            </div>
                            <div class="table-responsive">
                                <table id="role-table" class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="app.common.title.serial" /></th>
                                            <th><spring:message code="app.user.title.username" /></th>
                                            <th><spring:message code="app.user.title.role" /></th>
                                            <th><spring:message code="app.user.title.createTime" /></th>
                                            <th><spring:message code="app.common.action.operate" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${PAGE_DATA}" var="user" varStatus="s">
                                            <tr data-id="${user.id}">
                                                <td><c:out value="${s.index + 1}" /></td>
                                                <td><c:out value="${user.username}" /></td>
                                                <td>
                                                    <c:forEach items="${user.roles}" var="userRole" varStatus="s">
                                                        <c:if test="${x.index > 0}">,</c:if>
                                                        <c:out value="${userRole.role.name}"></c:out>
                                                    </c:forEach>
                                                </td>
                                                <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                <td>
                                                    <a href="${contextPath}/user/edit?id=${user.id}" class="btn btn-success btn-sm"><i class="fa fa-edit"></i> <spring:message code="app.common.action.edit" /></a>
                                                    <a href="#none" class="btn btn-danger btn-sm btn-delete"><i class="fa fa-trash-o"></i> <spring:message code="app.common.action.delete" /></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <%@include file="../components/pagination.jsp" %>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!--body wrapper end-->
        
        <script type="text/javascript" src="${contextPath}/assets/jquery-treetable/js/jquery.treetable.js"></script>
        <script type="text/javascript">
	        $(function(){
	        	$('.btn-add').click(function(){
	        		location.href = '${contextPath}/user/add';
	        	});
	        	
	        	$('.btn-delete').click(function(){
	        		var roleId = $(this).parents('tr').attr('data-id');
	        		if(roleId){
	        			BootstrapDialog.confirm({
	        				size: BootstrapDialog.SIZE_NORMAL,
            				type: BootstrapDialog.TYPE_WARNING,
            				draggable: true,
            				closable: true,
	        				title: '<spring:message code="app.user.title.delete" />',
	        	            message: '<spring:message code="app.user.title.delete.message" />',
	        	            callback: function(f){
	        	            	if(f){
	        	            		$.ajax({
	        	        				type: 'POST',
	        	        				url: '${contextPath}/user/delete/exec',
	        	        				data: {'id': roleId},
	        	        				success: function(result){
	        	        					if(result){
	        	        						var r = $.parseJSON(result);
	        	        						if(r && r.resultCode == '000000'){
	        	        							location.href = contextPath + '/user';
	        	        						}
	        	        					}
	        	        				}
	        	        			});
	        	            	}
	        	            }
	        			});
	        		}
	        	});
	        });
	    </script>
    </body>
</html>