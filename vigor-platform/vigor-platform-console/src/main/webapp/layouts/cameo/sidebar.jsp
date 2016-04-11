<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<aside class="sidebar collapsible canvas-left">
    <div class="scroll-menu">
        <nav class="main-navigation slimscroll" data-height="auto" data-size="4px" data-color="#ddd" data-distance="0">
            <ul>
                <c:set var="menus" value="${MENUS}" scope="request" />
                <c:set var="level" value="0" scope="request" />
                <c:import url="menu-items.jsp" />
            </ul>
        </nav>
    </div>
    <footer>
        <div class="about-app pd-md animated pulse">
            <a href="javascript:;"><img src="${contextPath}/static/img/about.png" alt=""></a>
            <span></span>
        </div>
        <div class="footer-toolbar pull-left">
            <a href="javascript:;" class="pull-left help"><i class="fa fa-question-circle"></i></a>
            <a href="javascript:;" class="toggle-sidebar pull-right hidden-xs"><i class="fa fa-angle-left"></i></a>
        </div>
    </footer>
</aside>

<script type="text/javascript">
    $(function(){
    	$('.menu-item', '.main-navigation').click(function(){
    		$('li', '.main-navigation').removeClass('active');
    		$(this).parent('li').addClass('active').parents('li').addClass('nav-active collapse-open');
    		
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
    	$('.menu-item', '.main-navigation').first().click();
    });
</script>