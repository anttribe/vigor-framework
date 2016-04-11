<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta name="keywords" content="<spring:message code="app.keywords" />">
        <meta name="description" content="<spring:message code="app.description" />">
        <title><spring:message code="app.appname" /> - <sitemesh:write property='title'/></title>
        <script type="text/javascript">
            //<![CDATA[
            try{if (!window.CloudFlare) {var CloudFlare=[{verbose:0,p:0,byc:0,owlid:"cf",bag2:1,mirage2:0,oracle:0,paths:{cloudflare:"/cdn-cgi/nexp/dok3v=1613a3a185/"},atok:"8cf090f78113d0737680c9c0460e3c94",petok:"c4bfc2ae81f1465c1fe1f9f45b0c71f8873334f9-1432712705-1800",zone:"nyasha.me",rocket:"0",apps:{"ga_key":{"ua":"UA-50530436-1","ga_bs":"2"}}}];CloudFlare.push({"apps":{"ape":"feccb3d3fc74fd07f6658eb4838284ef"}});!function(a,b){a=document.createElement("script"),b=document.getElementsByTagName("script")[0],a.async=!0,a.src="//ajax.cloudflare.com/cdn-cgi/nexp/dok3v=7e13c32551/cloudflare.min.js",b.parentNode.insertBefore(a,b)}()}}catch(e){};
            //]]>
        </script>
        <%@include file="stylesheet.jsp" %>
        <%@include file="javascript-required.jsp" %>
        <sitemesh:write property='head'/>
    </head>
    <body class="pace-done">
        <section>
            <div class="">
                <sitemesh:write property='body'/>
            </div>
        </section>
        <%@include file="javascript.jsp" %>
        <script type="text/javascript" src="${contextPath}/assets/adminEx/js/adminEx.js"></script>
    </body>
</html>