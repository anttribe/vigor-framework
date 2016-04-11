<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.resource.title" /></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/assets/zTree/css/zTreeStyle/zTreeStyle.css" >
        <link rel="stylesheet" type="text/css" href="${contextPath}/assets/zTree/css/metroStyle/metroStyle.css" >
    </head>
    <body>
        <div class="clearfix"></div>
        <!--body wrapper start-->
        <div class="row">
            <div class="col-sm-12">
                <section>
                    <div class="alert alert-info"><spring:message code="app.resource.select.dblClick.select" /></div>
                    <div id="resource-list-tree" class="ztree"></div>
                </section>
            </div>
        </div>
        <!--body wrapper end-->
        
        <script type="text/javascript" src="${contextPath}/assets/zTree/js/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript" src="${contextPath}/static/js/moudles/resource.js"></script>
        <script type="text/javascript">
	        $(function(){
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
									'children': processResourceTreeData(resource['children']) || null
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
	        		            zTreeObj = $.fn.zTree.init($("#resource-list-tree"), {
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
	        		                callback: {
	        		                	onClick: function(e, treeId, treeNode){
	        		                		var zTree = $.fn.zTree.getZTreeObj(treeId);
	        		                		if(zTree){
	        		                			if(treeNode.isParent){
	        		                				zTree.expandNode(treeNode, null, false, true, true);
	        		                			}
	        		                		}
	        		                	},
	        		                	onDblClick: function(e, treeId, treeNode){
	        		                		var resurce = {
	        		                			id: treeNode.id,
	        		                			name: treeNode.name
	        		                		};
	        		                		defensor.resource.selectResource(resurce);
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
    </body>
</html>