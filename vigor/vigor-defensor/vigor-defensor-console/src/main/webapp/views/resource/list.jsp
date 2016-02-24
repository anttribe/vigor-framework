<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.resource.title" /></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/jquery-treetable/css/jquery.treetable.css" >
        <link rel="stylesheet" type="text/css" href="${contextPath}/static/static/css/jquery.treetable.theme.custom.css" >
    </head>
    <body>
        <div class="clearfix"></div>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-sm-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <spring:message code="app.resource.title.list" />
                            <span class="tools pull-right"></span>
                        </header>
                        <div class="panel-body">
                            <div class="btn-group mb10">
                                <a href="#none" class="btn btn-primary btn-add"><i class="fa fa-plus"></i> <spring:message code="app.resource.action.add" /></a>
                            </div>
                            <div class="table-responsive">
                                <table id="resource-table" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="app.resource.title.name" /></th>
                                            <th><spring:message code="app.resource.title.resourceType" /></th>
                                            <th><spring:message code="app.resource.title.resourceUrl" /></th>
                                            <th><spring:message code="app.resource.title.privilege" /></th>
                                            <th><spring:message code="app.resource.title.enabled" /></th>
                                            <th width="10%"><spring:message code="app.common.action.operate" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!--body wrapper end-->
        
        <script type="text/javascript" src="${contextPath}/static/assets/jquery-treetable/js/jquery.treetable.js"></script>
        <script type="text/javascript" src="${contextPath}/static/static/js/moudles/resource.js"></script>
        <script type="text/javascript">
            $(function(){
            	$('.btn-add').click(function(){
            		location.href = contextPath + '/resource/add';
            	});
            });
        </script>
    </body>
</html>