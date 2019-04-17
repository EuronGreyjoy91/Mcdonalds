<%-- 
    Document   : cabecera
    Created on : 22/09/2018, 06:24:37 PM
    Author     : Federico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<spring:url value = "/resources" var = "urlResources" />
<spring:url value="/" var = "urlRoot"></spring:url>
<spring:url value="/usuarios/listar" var = "urlListarUsuarios"></spring:url>
<spring:url value="/ingredientes/listar" var = "urlListarIngredientes"></spring:url>
<spring:url value="/items/listar" var = "urlListarItems"></spring:url>
<spring:url value="/pedidos/listar" var = "urlListarPedidos"></spring:url>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>McDonalds - Sistema de Gesti&oacute;n</title>
        <link href="${urlResources}/img/favicon.ico" rel="icon" type="image/x-icon" />
        <link rel="stylesheet" href="${urlResources}/css/materialize.min.css">
        <link rel="stylesheet" href="${urlResources}/css/jquery-ui.theme.min.css">
        <link rel="stylesheet" href="${urlResources}/css/estilos.css">
        <link rel="stylesheet" href="${urlResources}/css/materialize.icons.css">
        <link rel="stylesheet" href="${urlResources}/css/pickadate.css">
        <link rel="stylesheet" href="${urlResources}/css/pickadate.date.css">
        <script src="${urlResources}/js/jquery.js"></script>
        <script src="${urlResources}/js/materialize.min.js"></script>
        <script src="${urlResources}/js/picker.js"></script>
		<script src="${urlResources}/js/picker.date.js"></script>
        <script src="${urlResources}/js/jquery-ui.min.js"></script>
        <script src="${urlResources}/js/jquery.validate.min.js"></script>
        <script src="${urlResources}/js/scripts.js"></script>
        <script src="${urlResources}/js/validate-form.js"></script>
    </head>
    <body>
	    <ul id="dropdown1" class="dropdown-content">
	        <li><a href="${urlRoot}logout">Salir</a></li>
	    </ul>
	    <ul id="dropdown2" class="dropdown-content">
	        <li><a href="${urlRoot}logout">Salir</a></li>
	    </ul>
	    <nav>
	        <div class="nav-wrapper">
	            <a href="#!" class="brand-logo"><img src = "${urlResources}/img/logo.png" id = "logo"/></a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">
                	<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	                    <li><a href="${urlListarUsuarios}/0"><i class="material-icons left">face</i>Usuarios</a></li>
    	                <li><a href="${urlListarIngredientes}/0"><i class="material-icons left">group_work</i>Ingredientes</a></li>
        	            <li><a href="${urlListarItems}/0"><i class="material-icons left">room_service</i>Items</a></li>
                	</sec:authorize>
                	<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR', 'VENDEDOR', 'COCINERO')">
                        <li>
                        	<a href="${urlListarPedidos}/0"><i class="material-icons left">attach_money</i>Ventas</a>
                        </li>
                    	<li>
                    		<a class="dropdown-trigger" href="#!" data-target="dropdown1">
                    			<i class="material-icons left">mood</i>
                    			<sec:authentication property = "principal.username"/>
                    			<i class="material-icons right">arrow_drop_down</i>
                    		</a>
						</li>
                	</sec:authorize>
                </ul>
	        </div>
	    </nav>

		<ul class="sidenav" id="mobile-demo">
			<sec:authorize access="hasAnyAuthority('ADMINISTRADOR')">
				<li><a href="${urlListarUsuarios}/0"><i class="material-icons left">face</i>Usuarios</a></li>
				<li><a href="${urlListarIngredientes}/0"><i class="material-icons left">group_work</i>Ingredientes</a></li>
				<li><a href="${urlListarItems}/0"><i class="material-icons left">room_service</i>Items</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyAuthority('ADMINISTRADOR', 'VENDEDOR', 'COCINERO')">
				<li><a href="${urlListarPedidos}/0"><i class="material-icons left">attach_money</i>Ventas</a></li>
				<li><a class="dropdown-trigger" href="#!" data-target="dropdown2"><i class="material-icons left">mood</i> <sec:authentication property="principal.username" /> <i class="material-icons right">arrow_drop_down</i></a></li>
			</sec:authorize>
		</ul>