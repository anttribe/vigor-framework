<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- left side start-->
<div class="left-side sticky-left-side">
    <!--logo and iconic logo start-->
    <div class="logo">
        <a href="${contextPath}/"><img src="${contextPath}/static/img/logo.png" alt=""></a>
    </div>
    <div class="logo-icon text-center">
        <a href="${contextPath}/"><img src="${contextPath}/static/img/logo_icon.png" alt=""></a>
    </div>
    <!--logo and iconic logo end-->
    
    <div id="menus" class="left-side-inner">
        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav">
            <c:set var="menus" value="${MENUS}" scope="request" />
            <c:set var="level" value="0" scope="request" />
            <c:import url="menu-items.jsp" />
        </ul>
        <!--sidebar nav end-->
    </div>
</div>
<!-- left side end-->
<script type="text/javascript">
    $(function(){
    	$('.menu-item', '#menus').click(function(){
    		$('li', '#menus').removeClass('active');
    		$(this).parent('li').addClass('active').parents('li').addClass('nav-active');
    		
    		var data_href = $(this).attr('data-href');
    		if(data_href){
    			var url_regex = /((http|ftp|https):\/\/)(([a-zA-Z0-9._-]+.[a-zA-Z]{2,6})|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(:[0-9]{1,4})*(\/[a-zA-Z0-9&%_.\/-~-]*)?/;
    			if(data_href.match(url_regex)){
    			} else{
    				var href = contextPath + (data_href.startsWith('/') ? '' : '/') + data_href;
    				$('#mainFrame').attr('src', href);
    			}
    		}
    	});
    	$('.menu-item', '#menus').first().click();
    });
</script>