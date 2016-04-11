<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.user.title" /></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/jquery.validation.css" />
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
                        </header>
                        <div class="panel-body">
                            <form class="data-form form-horizontal" role="form" method="POST" action="">
                                <input type="hidden" name="id" value="${PARAM.id}" />
                                <div class="form-group">
                                    <label for="username" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.user.title.username" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="username" name="username" value="<c:out value="${PARAM.username}" />" class="form-control required" maxLength="30" placeholder="<spring:message code="app.user.placeholder.username" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.user.title.password" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="password" id="password" name="password" value="" class="form-control <c:if test="${PARAM.id == null or PARAM.id == ''}">required</c:if>" maxLength="30" placeholder="<spring:message code="app.user.placeholder.password" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.user.title.confirmPassword" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="password" id="confirmPassword" value="" class="form-control <c:if test="${PARAM.id == null or PARAM.id == ''}">required</c:if>" maxLength="30" placeholder="<spring:message code="app.user.placeholder.confirmPassword" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.user.title.role" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <div id="userRoles"></div>
                                        <div id="roles" class="checkbox"></div>
                                        <c:set var="selectedRoles" value=""></c:set>
                                        <c:forEach items="${PARAM.roles}" var="userRole" varStatus="s">
                                            <c:if test="${null != userRole and null != userRole.role and null != userRole.role.id}">
                                                <c:if test="${s.index > 0}">
                                                    <c:set var="selectedRoles" value="${selectedRoles},"></c:set>
                                                </c:if>
                                                <c:set var="selectedRoles" value="${selectedRoles}-${userRole.role.id}-"></c:set>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10 col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary submit"><spring:message code="app.common.action.submit" /></button>
                                        <a href="#none" class="btn btn-default btn-cancel"><spring:message code="app.common.action.cancel" /></a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!--body wrapper end-->
        
        <script type="text/javascript" src="${contextPath}/assets/jquery-validation/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${contextPath}/assets/jquery-validation/localization/messages_zh.min.js"></script>
        <script type="text/javascript" src="${contextPath}/assets/jquery.form/jquery.form.js"></script>
        <script type="text/javascript">
	        $(function(){
	        	$('.btn-cancel').click(function(){
	        		location.href = contextPath + '/user';
	        	});
	        });
	    </script>
	    <script type="text/javascript">
	        $(function(){
	        	// 用户原来的角色
	            var selectedRoles = '${selectedRoles}';
	        	$.ajax({
	        		type: 'POST',
	        		url: '${contextPath}/role/list/exec',
	        		success: function(r){
	        			if(r){
	        				var result = $.parseJSON(r);
	        				if(result && result.resultCode == '000000'){
	        					var roles = result.data;
	        					if(roles && roles.length>0){
	        						var $html = '';
	        						for(var i=0; i<roles.length; i++){
	        							var role = roles[i];
	        							if(role && role['id'] && role['name']){
	        								$html += '<div><label><input type="checkbox" name="role" value="' + role['id'] + '" ' + (selectedRoles && selectedRoles.indexOf('-' + role['id'] + '-') != -1 ? 'checked' : '') + '>' + (role['name'] || '') + '</label></div>';
	        							}
	        						}
	        						$('#roles').append($html);
	        					}
	        				}
	        			}
	        		}
	        	});
	        });
	    </script>
	    <script type="text/javascript">
	        $(function(){
	        	$('.data-form').validate({
	        		submitHandler: function(form){
	        			// 获取所有选中的角色
	        			var checkedRoles = $('input:checked', '#roles');
	        			console.log(checkedRoles);
	        			if(checkedRoles && checkedRoles.length>0){
	        				var index = 0;
        					$('#userRoles').empty();
        					for(var i=0; i<checkedRoles.length; i++){
        						var checkedRole = checkedRoles[i];
        						if(checkedRole && $(checkedRole).val()){
        							$('<div>', {
        								'html': '<input type="hidden" name="roles[' + index + '].role.id" value="' + $(checkedRole).val() + '">'
        							}).appendTo('#userRoles');
        							++index;
        						}
        					}
	        			}
	        			
	        			$(form).ajaxSubmit({
	        				type: 'POST',
	        				url: '${contextPath}/user/edit/exec',
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
	        	});
	        });
	    </script>
    </body>
</html>