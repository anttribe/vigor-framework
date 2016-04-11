<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <title><spring:message code="app.user.title.signin" /></title>
    </head>
    <body class="bg-color center-wrapper">
        <div class="center-content">
            <div class="">
                <div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <section class="panel panel-default">
                        <header class="panel-heading"><spring:message code="app.user.title.signin" /></header>
                        <div class="bg-white user pd-md">
                            <h6><spring:message code="app.user.tip.signin" /></h6>
                            <form role="form" method="POST" action="">
                                <input type="text" name="username" class="form-control mg-b-sm" placeholder="<spring:message code="app.user.title.username" />" autofocus>
                                <input type="password" name="password" class="form-control" placeholder="<spring:message code="app.user.title.password" />">
                                <label class="checkbox pull-left">
                                    <input type="checkbox" value=""><spring:message code="app.user.title.signin.remeberMe" />
                                </label>
                                <div class="text-right mg-b-sm mg-t-sm">
                                    <a href="javascript:;"> <spring:message code="app.user.title.forgotpassword" /></a>
                                </div>
                                <button class="btn btn-info btn-block" type="submit"><spring:message code="app.user.title.signin" /></button>
                            </form>
                            <p class="center-block mg-t mg-b text-right"><a class="" href=""><spring:message code="app.user.link.signup" /></a></p>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>