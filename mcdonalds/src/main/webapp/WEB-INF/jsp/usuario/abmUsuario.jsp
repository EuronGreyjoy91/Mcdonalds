<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/usuarios/nuevo" var = "urlNuevo"></spring:url>
<spring:url value="/usuarios/editar" var = "urlEditar"></spring:url>
<spring:url value="/usuarios/listar" var = "urlListar"></spring:url>
<spring:url value="/usuarios/reasignar" var = "urlReasignar"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
    var _urlReasignar = "<c:out value = '${urlReasignar}'/>";
</script>
<script src = "${urlResources}/js/abmUsuario.js"></script>
<body>
	<c:set var = "queryString" scope = "session" value = "&nombre=${nombre}&apellido=${apellido}&documento=${documento}&usuarioTipo=${usuarioTipoSeleccionado}"/>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Usuarios</h3>
        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nuevo</a>
    	<form:form class="col l6 center-align" action = "${urlListar}/0" method = "GET" autocomplete = "off" style = "margin-top: 20px">
           <div class="row">
               <div class="input-field col s12 l3 offset-l3">
                   <input placeholder="Buscar por nombre" id="nombre" name = "nombre" type="text" class="validate" value = "<c:out value = "${nombre}"/>"/>
                   <label for="nombre">Nombre</label>
               </div>
               <div class="input-field col s12 l3">
                   <input placeholder="Buscar por apellido" id="apellido" name = "apellido" type="text" class="validate" value = "<c:out value = "${apellido}"/>"/>
                   <label for="apellido">Apellido</label>
               </div>
           </div>
           <div class="row">
               <div class="input-field col s12 l3 offset-l3">
                   <input placeholder="Buscar por documento" id="documento" name = "documento" type="number" class="validate" value = "<c:out value = "${documento}"/>"/>
                   <label for="documento">Documento</label>
               </div>
			   <div class="input-field col s12 l3">
					<select id = "usuarioTipo" name = "usuarioTipo">
						<option value="">Seleccione una opci&oacute;n</option>
						<c:forEach items = "${usuarioTipos}" var = "usuarioTipo">
							<option value = "${usuarioTipo.id}" <c:if test = "${usuarioTipo.id eq usuarioTipoSeleccionado}"> selected </c:if>><c:out value = "${usuarioTipo.nombre}"></c:out></option>
						</c:forEach>
					</select>
					<label>Tipo de usuario</label>
				</div>
			</div>
           <button class="btn waves-effect waves-light blue lighten-1" type="submit" name="action">Buscar
               <i class="material-icons right">search</i>
           </button>
       	</form:form>
        <c:choose>
            <c:when test = "${not empty usuarios}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Tipo</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Documento</th> 
                                <th>Acciones</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${usuarios}" var = "usuario">
                                <tr>
                                    <td><c:out value = "${usuario.id}"/></td>
                                    <td><c:out value = "${usuario.usuarioTipo.nombre}"/></td>
                                    <td><c:out value = "${usuario.nombre}"/></td>
                                    <td><c:out value = "${usuario.apellido}"/></td>
                                    <td><c:out value = "${usuario.documento}"/></td>
                                    <td>
                                        <a href = "${urlEditar}/<c:out value = "${usuario.id}"/>"class="waves-effect waves-light btn-small red lighten-1"><i class="material-icons left">edit</i>Editar</a>
                                        <c:if test = "${usuario.usuarioTipo.id ne 1}">
                                            <a href = "#" data-id = "<c:out value = "${usuario.id}"/>" data-nombre = "<c:out value = "${usuario.nombre}"/>" class="waves-effect waves-light btn-small blue lighten-1 reasignar"><i class="material-icons left">autorenew</i>Reasignar</a>
                                        </c:if>
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
                <a href="#!" class="waves-effect waves-green btn-flat modal-ok"><i class="material-icons left">autorenew</i>Reasignar</a>
            </div>
        </div>
    </div>
</body>

</html>
