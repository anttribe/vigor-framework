<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.role.title" /></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/jquery.validation.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/assets/zTree/css/zTreeStyle/zTreeStyle.css" >
        <link rel="stylesheet" type="text/css" href="${contextPath}/assets/zTree/css/metroStyle/metroStyle.css" >
    </head>
    <body>
        <div class="clearfix"></div>
        <!--body wrapper start-->
        <div class="wrapper">
            <div class="">
                <div class="col-sm-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span><spring:message code="app.role.title" /></span>
                        </header>
                        <div class="panel-body">
                            <form class="data-form form-horizontal" role="form" method="POST" action="">
                                <input type="hidden" name="id" value="${PARAM.id}" />
                                <div class="form-group">
                                    <label for="name" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.role.title.name" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="name" name="name" value="<c:out value="${PARAM.name}" />" class="form-control required" maxLength="30" placeholder="<spring:message code="app.role.placeholder.name" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="code" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.role.title.code" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <input type="text" id="code" name="code" value="<c:out value="${PARAM.code}" />" class="form-control required" maxLength="30" placeholder="<spring:message code="app.role.placeholder.code" />">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="identity" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.role.title.identity" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <select id="identity" name="identity.id" class="form-control required">
                                            <option value=""><spring:message code="app.common.title.select" /></option>
                                            <c:forEach items="${identityDict.items}" var="dictItem">
                                                <option value="${dictItem.id}" <c:if test="${dictItem.id eq PARAM.identity.id}">selected</c:if>><c:out value="${dictItem.name}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.role.title.resource" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <div id="roleResources"></div>
                                        <div id="roleResource-tree" class="ztree"></div>
                                        <c:set var="selectedResources" value=""></c:set>
                                        <c:forEach items="${PARAM.resources}" var="roleResource" varStatus="s">
                                            <c:if test="${null != roleResource and null != roleResource.resource and null != roleResource.resource.id}">
                                                <c:if test="${s.index > 0}">
                                                    <c:set var="selectedResources" value="${selectedResources},"></c:set>
                                                </c:if>
                                                <c:set var="selectedResources" value="${selectedResources}-${roleResource.resource.id}-"></c:set>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="remarks" class="col-lg-2 col-sm-2 control-label"><spring:message code="app.role.title.remarks" /></label>
                                    <div class="col-lg-8 col-sm-8">
                                        <textarea id="remarks" name="remarks" style="height: 80px;" class="form-control">${PARAM.remarks}</textarea>
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
        <script type="text/javascript" src="${contextPath}/assets/zTree/js/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript">
	        $(function(){
	        	$('.btn-cancel').click(function(){
	        		location.href = contextPath + '/role';
	        	});
	        });
	    </script>
	    <script type="text/javascript">
	        var zTreeObj = null;
	        $(function(){
	        	// 角色原选中的权限
	            var selectedResources = '${selectedResources}';
	        	function processResourceTreeData(resources){
	        		if(resources && resources.length > 0){
    					var resourceTreeDatas = [];
						for(var i=0; i<resources.length; i++){
							var resource = resources[i];
							if(resource && resource['id']){
								resourceTreeDatas.push({
									'id': resource['id'],
									'name': resource['name'] || '',
									'parentId': (resource['parent'] && resource['parent']['id']) || null,
									'children': processResourceTreeData(resource['children']) || null,
									'checked': selectedResources && selectedResources.indexOf('-' + resource['id'] + '-') != -1
								});
							}
						}
						return resourceTreeDatas;
	        		}
	        	}
	        	$.ajax({
	        		type: 'POST',
	        		url: '${contextPath}/resource/list/exec',
	        		success: function(r){
	        			if(r){
	        				var result = $.parseJSON(r);
	        				if(result && result.resultCode == '000000'){
	        					var resources = result.data;
	        					// 构造成tree数据
	        					var resourceTreeDatas = processResourceTreeData(resources);
	        					// 初始化tree
	        		            zTreeObj = $.fn.zTree.init($("#roleResource-tree"), {
	        		        		view: {
	        		                    dblClickExpand: false
	        		                },
	        		                data: {
	        		                    simpleData: {
	        		                        enable: true,
	        		                        idKey: 'id',
	        		                        pIdKey: 'parentId'
	        		                    }
	        		                },
	        		                check: {
	        		                	enable: true
	        		                },
	        		                callback: {
	        		                	onNodeCreated: function(e, treeId, treeNode){
	        		                	},
	        		                	onClick: function(e, treeId, treeNode){
	        		                		var zTree = $.fn.zTree.getZTreeObj(treeId);
	        		                		if(zTree){
	        		                			if(treeNode.isParent){
	        		                				zTree.expandNode(treeNode, null, false, true, true);
	        		                			}
	        		                		}
	        		                	},
	        		                	onExpand: function(e, treeId, treeNode){
	        		                	}
	        		                }
	        		            }, resourceTreeDatas);
	        		            zTreeObj.expandAll(true);
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
	        			// 获取ztee所有选中的节点
	        			if(zTreeObj){
	        				var checkedNodes = zTreeObj.getCheckedNodes(true);
	        				if(checkedNodes && checkedNodes.length>0){
	        					var index = 0;
	        					$('#roleResources').empty();
	        					for(var i=0; i<checkedNodes.length; i++){
	        						var checkedNode = checkedNodes[i];
	        						if(checkedNode && checkedNode.id){
	        							$('<div>', {
	        								html: '<input type="hidden" name="resources[' + index + '].resource.id" value="' + checkedNode.id + '">'
	        							}).appendTo('#roleResources');
	        							++index;
	        						}
	        					}
	        				}
	        			}
	        			$(form).ajaxSubmit({
	        				type: 'POST',
	        				url: '${contextPath}/role/edit/exec',
	        				success: function(result){
	        					if(result){
	        						var r = $.parseJSON(result);
	        						if(r && r.resultCode == '000000'){
	        							location.href = contextPath + '/role';
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