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
    <div style = "margin-left: 20px; margin-right: 20px">
    	<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	        <h3>Ventas</h3>
        </sec:authorize>
        <sec:authorize access = "hasAnyAuthority('VENDEDOR')">
	        <h3>Mis ventas</h3>
	        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nueva</a>
        </sec:authorize>
        <sec:authorize access = "hasAnyAuthority('COCINERO')">
	        <h3>Ventas a despachar</h3>
        </sec:authorize>
        <form:form class="col l6 center-align" action = "${urlListar}/0" method = "GET" autocomplete = "off" style = "margin-top: 20px" modelAttribute="pedido">
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
           <div class="row">
              	<div class="input-field col s12 l3 offset-l3">
					<form:input placeholder="Fecha de ingreso" id="fechaIngreso" path = "fechaIngreso" type="date" class="validate customDatePicker" data-value="${pedido.fechaIngreso}"/>
                   	<label for="fechaIngreso">Fecha de ingreso</label>
               	</div>
               	<div class="input-field col s12 l3">
					<form:input placeholder="Fecha de despacho" id="fechaDespacho" path = "fechaDespacho" type="text" class="validate customDatePicker" data-value="${pedido.fechaDespacho}"/>
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
                            <c:set var = "totalDia" value = "0"/>
                            <c:set var = "totalGeneral" value = "0"/>
                            
                            <c:forEach items = "${pedidos}" var = "pedido" varStatus="loop">
                            	<sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	                                <c:set var="totalGeneral" value="${totalGeneral + pedido.monto}"/>                           
	
	                                
	                                <c:if test = "${loop.first}">
	                                    <fmt:formatDate var="diaComparador" value="${pedido.fechaIngreso}" pattern="yyyy-MM-dd" />
	                                    <fmt:formatDate var="diaMostrar" value="${pedido.fechaIngreso}" pattern="dd/MM/yyyy" />
	                                </c:if>
	                                
	                                <fmt:formatDate var="diaPedido" value="${pedido.fechaIngreso}" pattern="yyyy-MM-dd" />
	                                <c:choose>
	                                    <c:when test = "${diaComparador eq diaPedido}">
	                                        <c:set var="totalDia" value="${totalDia + pedido.monto}"/>                           
	                                    </c:when>
	                                    <c:otherwise>
	                                        <tr>
	                                            <td colspan = "5" style = "text-align: right"><b>Total del d&iacute;a <c:out value = "${diaMostrar}"/>: </b></td>
	                                            <td><b>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${totalDia}" /></b></td>
	                                            <td></td>
	                                        </tr>
	                                        <c:set var = "totalDia"  value = "${pedido.monto}"/>
	                                        <fmt:formatDate var="diaComparador" value="${pedido.fechaIngreso}" pattern="yyyy-MM-dd" />
	                                        <fmt:formatDate var="diaMostrar" value="${pedido.fechaIngreso}" pattern="dd/MM/yyyy" />
	                                    </c:otherwise>
	                                </c:choose>  
                                </sec:authorize>
                                <tr>
                                    <td><c:out value = "${pedido.id}"/></td>
                                    <td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${pedido.fechaIngreso}"/></td>
                                    <td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${pedido.fechaDespacho}"/></td>
                                    <td><c:out value = "${pedido.vendedor.nombre}"/></td>
                                    <td><c:out value = "${pedido.cocinero.nombre}"/></td>
                                    <td>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${pedido.monto}" /></td>
                                    <td>
                                        <sec:authorize access = "hasAnyAuthority('COCINERO')">
                                            <a href = "#" class="waves-effect waves-light btn-small blue lighten-1 despachar" data-id-pedido = "<c:out value = "${pedido.id}"/>"><i class="material-icons left">done</i>Despachar</a>
                                        </sec:authorize>
                                        <a href = "${urlListarItemsPedido}/<c:out value = "${pedido.id}"/>"class="waves-effect waves-light btn-small green lighten-1"><i class="material-icons left">description</i>Detalle</a>
                                    </td>
                                </tr>
                                <sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	                                <c:if test = "${loop.last}">
	                                    <tr>
	                                        <td colspan = "5" style = "text-align: right"><b>Total del d&iacute;a <c:out value = "${diaMostrar}"/>: </b></td>
	                                        <td><b>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${totalDia}" /></b></td>
	                                        <td></td>
	                                    </tr>
	                                </c:if>
                                </sec:authorize>
                            </c:forEach>
                            <sec:authorize access = "hasAnyAuthority('ADMINISTRADOR')">
	                            <tr>
	                                <td colspan = "5" style = "text-align: right; color: #66BB6A"><h5><b>Total general: </b></h5></td>
	                                <td style = "color: #66BB6A"><h5><b>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${totalGeneral}" /></b></h5></td>
	                                <td></td>
	                            </tr>
                            </sec:authorize>
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
