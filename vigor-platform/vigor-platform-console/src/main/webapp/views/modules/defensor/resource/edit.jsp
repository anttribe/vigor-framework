<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.resource.title" /></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/assets/jquery.icheck/skins/all.css" />
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
                            <span><spring:message code="app.resource.title" /></span>
                        </header>
                        <div class="panel-body">
                            <form class="data-form form-horizontal" role="form" method="POST" action="">
                                <input type="hidden" name="id" value="${PARAM.id}" />
                                <input type="hidden" name="parent.id" value="${PARAM.parent.id}" />
                                <div class="form-group">
                                    <label for="parentSelector" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.parent" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                            <input type="text" id="parentSelector" value="<c:out value="${PARAM.parent.name}" />" class="form-control" readonly="readonly" placeholder="<spring:message code="app.resource.placeholder.parent" />">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.name" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="name" name="name" value="<c:out value="${PARAM.name}" />" class="form-control required" maxLength="30" placeholder="<spring:message code="app.resource.placeholder.name" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="resourceType" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.resourceType" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <select id="resourceType" name="resourceType" value="${PARAM.resourceType}" class="form-control required">
                                            <option value=""><spring:message code="app.common.title.select" /></option>
                                            <c:forEach items="${resourceTypes}" var="resourceType">
                                                <option value="${resourceType}" <c:if test="${resourceType == PARAM.resourceType}">selected</c:if>><spring:message code="app.resource.title.resourceType.${resourceType}" text="" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div id="resource-type-selector">
                                    <div class="MENU-selector PAGE-selector selector hidden">
                                        <div class="form-group">
                                            <label for="resourceUrl" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.resourceUrl" /></label>
                                            <div class="col-lg-8 col-sm-8">
                                                <input type="text" id="resourceUrl" name="resourceUrl" value="${PARAM.resourceUrl}" class="form-control" maxLength="500" placeholder="<spring:message code="app.resource.placeholder.resourceUrl" />">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="target" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.target" /></label>
                                            <div class="col-lg-8 col-sm-8">
                                                <select id="target" name="target" value="${PARAM.target}" class="form-control">
                                                    <option value=""><spring:message code="app.common.title.select" /></option>
                                                    <c:forEach items="${resourceTargets}" var="target">
                                                        <option value="${target}" <c:if test="${target == PARAM.target}">selected</c:if>><spring:message code="app.resource.title.resourceTarget.${target}" text="" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="OPERATOR-selector selector hidden">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="privilege" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.privilege" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="privilege" name="privilege" value="${PARAM.privilege}" class="form-control" maxLength="30" placeholder="<spring:message code="app.resource.placeholder.privilege" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="icon" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.icon" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="icon" name="icon" value="${PARAM.icon}" class="form-control" maxLength="30" placeholder="<spring:message code="app.resource.placeholder.icon" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="isShow" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.resource.title.isShow" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <div class="icheck">
                                            <c:forEach items="${yesOrNos}" var="yesOrNo">
                                                <input type="radio" id="isShow_${yesOrNo}" name="isShow" value="${yesOrNo}" <c:if test="${PARAM.isShow == yesOrNo}">checked</c:if>>
                                                <label for="isShow_${yesOrNo}"><spring:message code="app.common.type.YesOrNo.${yesOrNo}" text="" /></label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-sm-offset-2 col-lg-10 col-sm-10">
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
        <script type="text/javascript" src="${contextPath}/assets/jquery.icheck/jquery.icheck.min.js"></script>
        <script type="text/javascript" src="${contextPath}/static/js/moudles/resource.js"></script>
        <script type="text/javascript">
            var resourceSelector = null;
            var selectResource = function(resource){
    			if(resource){
    				$('input[name="parent.id"]').val(resource.id);
    				$('input[id="parentSelector"]').val(resource.name);
    			}
            	if(resourceSelector){
            		resourceSelector.close();
    			}
            };
            var changeResourceType = function(resourceType){
            	if(resourceType){
    				$('.' + resourceType + '-selector', '#resource-type-selector').removeClass('hidden').siblings().addClass('hidden');
    			} else{
    				$('div.selector', '#resource-type-selector').addClass('hidden');
    			}
            };
        </script>
        <script type="text/javascript">
	        $(function(){
	        	$('.icheck input').iCheck({
	        		cursor: true,
	                checkboxClass: 'icheckbox_flat-green',
	                radioClass: 'iradio_flat-green'
	            });
	        	
	        	$('input[id="parentSelector"]').bind({
	        		'click': function(){
	        			resourceSelector = defensor.resource.resourceSelector({title: '<spring:message code="app.resource.select.title" />'});
	        			if(resourceSelector){
	        				resourceSelector.open();
	        			}
	        		}
	        	});
	        	$('.btn-cancel').click(function(){
	        		location.href = contextPath + '/resource';
	        	});
	        	$('#resourceType').bind({
	        		'change': function(){
	        			var resourceType = $(this).val();
	        			changeResourceType(resourceType);
	        		}
	        	});
	        	
	        	$('.data-form').validate({
	        		submitHandler: function(f){
	        			$(f).ajaxSubmit({
	        				type: 'POST',
	        				url: '${contextPath}/resource/edit/exec',
	        				success: function(r){
	        					if(r){
	        						var result = $.parseJSON(r);
	        						if(result && result.resultCode == '000000'){
	        							location.href = '${contextPath}/resource';
	        						}
	        					}
	        				}
	        			});
	        		}
	        	});
	        	
	        	//初始化
	        	changeResourceType('${PARAM.resourceType}');
	        });
	    </script>
    </body>
</html>