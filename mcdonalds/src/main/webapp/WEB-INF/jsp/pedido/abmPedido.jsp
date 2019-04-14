<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/pedidos/nuevo" var = "urlNuevo"></spring:url>
<spring:url value="/pedidos/listar" var = "urlListar"></spring:url>
<spring:url value="/itemsPedido/listar" var = "urlListarItemsPedido"></spring:url>
<spring:url value="/pedidos/despachar" var = "urlDespacharPedido"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
    var _urlDespacharPedido = "<c:out value = '${urlDespacharPedido}'/>";
</script>
<script src = "${urlResources}/js/abmPedido.js"></script>
<body>
	<fmt:formatDate value="${pedido.fechaIngreso}" pattern="dd/MM/yyyy" var="fechaIngreso"/>
    <fmt:formatDate value="${pedido.fechaDespacho}" pattern="dd/MM/yyyy" var="fechaDespacho"/>
	<c:set var = "queryString" scope = "session" value = "&vendedor.id=${pedido.vendedor.id}&cocinero.id=${pedido.cocinero.id}&fechaIngreso=${fechaIngreso}&fechaDespacho=${fechaDespacho}"/>
	
    <div style = "margin-left: 20px; margin-right: 20px">
    	<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	        <h3>Ventas <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
        </sec:authorize>
        <sec:authorize access = "hasAnyAuthority('VENDEDOR')">
	        <h3>Mis ventas <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
	        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nueva</a>
        </sec:authorize>
        <sec:authorize access = "hasAnyAuthority('COCINERO')">
	        <h3>Ventas a despachar <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
        </sec:authorize>
        <form:form class="col l6 center-align" action = "${urlListar}/0" method = "GET" autocomplete = "off" style = "margin-top: 20px" modelAttribute="pedido">
       		<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	           <div class="row">
	              	<div class="input-field col s12 l3 offset-l3">
						<form:select path="vendedor.id">
							<form:option value="">Seleccione una opci&oacute;n</form:option>
							<form:options items="${vendedores}" itemValue = "id" itemLabel = "nombre"></form:options>
						</form:select>
	                   <label>Vendedor</label>
	               	</div>
	
	               	<div class="input-field col s12 l3">
						<form:select path="cocinero.id">
							<form:option value="">Seleccione una opci&oacute;n</form:option>
							<form:options items="${cocineros}" itemValue = "id" itemLabel = "nombre"></form:options>
						</form:select>
	                   <label>Cocinero</label>
	               	</div>
	           </div>
           </sec:authorize>
           <div class="row">
              	<div class="input-field col s12 l3 offset-l3">
					<form:input placeholder="Fecha de ingreso" id="fechaIngreso" path = "fechaIngreso" type="date" class="validate customDatePicker" data-value="${fechaIngreso}"/>
                   	<label for="fechaIngreso">Fecha de ingreso</label>
               	</div>
               	<div class="input-field col s12 l3">
					<form:input placeholder="Fecha de despacho" id="fechaDespacho" path = "fechaDespacho" type="text" class="validate customDatePicker" data-value="${fechaDespacho}"/>
                   	<label for="fechaDespacho">Fecha de despacho</label>
               	</div>
           </div>
           <button class="btn waves-effect waves-light blue lighten-1" type="submit" name="action">Buscar
               <i class="material-icons right">search</i>
           </button>
       	</form:form>
        <c:choose>
            <c:when test = "${not empty pedidos}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha de ingreso</th>
                                <th>Fecha de despacho</th>
                                <th>Vendedor</th>
                                <th>Cocinero</th>
                                <th>Monto</th>
                                <th>Acciones</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${pedidos}" var = "pedido" varStatus="loop">
                                <tr>
                                    <td><c:out value = "${pedido.id}"/></td>
                                    <td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${pedido.fechaIngreso}"/></td>
                                    <td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${pedido.fechaDespacho}"/></td>
                                    <td><c:out value = "${pedido.vendedor.nombre}"/></td>
                                    <td><c:out value = "${pedido.cocinero.nombre}"/></td>
                                    <td>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${pedido.monto}" /></td>
                                    <td>
                                        <sec:authorize access = "hasAnyAuthority('COCINERO')">
                                            <a href = "#" class="waves-effect waves-light btn-small blue lighten-1 despachar" data-id-pedido = "<c:out value = "${pedido.id}"/>">
                                            	<i class="material-icons">done</i><span class = "hide-on-small-only vertical-align">&nbsp;Despachar</span>
                                           	</a>
                                        </sec:authorize>
                                        <a href = "${urlListarItemsPedido}/<c:out value = "${pedido.id}"/>"class="waves-effect waves-light btn-small green lighten-1">
                                        	<i class="material-icons">description</i><span class = "hide-on-small-only vertical-align">&nbsp;Detalle</span>
                                       	</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%@ include file="../pie.jsp" %>
            </c:when>
            <c:otherwise>
                <h5 class = "center-align"><i>No se encontraron resultados</i></h5>
            </c:otherwise>
        </c:choose>
		<div id="modal" class="modal bottom-sheet">
            <div class="modal-content">
            </div>
            <div class="modal-footer">
                <a href="#!" class="waves-effect waves-green btn-flat modal-ok"><i class="material-icons left">done</i>Despachar</a>
            </div>
        </div>
	</div>
</body>

</html>
