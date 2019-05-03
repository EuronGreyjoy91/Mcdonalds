<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/usuarios/nuevo" var = "urlNuevo"></spring:url>
<spring:url value="/usuarios/editar" var = "urlEditar"></spring:url>
<spring:url value="/usuarios/desactivar" var = "urlDesactivar"></spring:url>
<spring:url value="/usuarios/activar" var = "urlActivar"></spring:url>
<spring:url value="/usuarios/listar" var = "urlListar"></spring:url>
<spring:url value="/usuarios/reasignar" var = "urlReasignar"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
    var _urlReasignar = "<c:out value = '${urlReasignar}'/>";
</script>
<script src = "${urlResources}/js/abmUsuario.js"></script>
	<c:set var = "queryString" scope = "session" value = "&nombre=${usuario.nombre}&apellido=${usuario.apellido}&documento=${usuario.documento}&usuarioTipo.id=${usuario.usuarioTipo.id}&activo=${usuario.activo}"/>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Usuarios <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nuevo</a>
    	<form:form class="col l6 center-align" action = "${urlListar}/0" method = "GET" autocomplete = "off" style = "margin-top: 20px" modelAttribute="usuario">
           <div class="row">
               <div class="input-field col s12 l3 offset-l3">
			   		<form:input placeholder="Nombre" id="nombre" path = "nombre" type="text" class="validate"/>
                    <label for="nombre">Nombre</label>
         		</div>
               	<div class="input-field col s12 l3">
					<form:input placeholder="Apellido" id="apellido" path = "apellido" type="text" class="validate"/>
                    <label for="apellido">Apellido</label>
         		</div>
           </div>
           <div class="row">
           	   <div class="input-field col s12 l3 offset-l3">
					<form:input placeholder="Documento" id="documento" path = "documento" type="text" class="validate"/>
                    <label for="documento">Documento</label>
         	   </div>
               <div class="input-field col s12 l3">
					<form:select path="usuarioTipo.id">
						<form:option value="">Todos</form:option>
						<form:options items="${usuarioTipos}" itemValue = "id" itemLabel = "nombre"></form:options>
					</form:select>
                   <label>Tipo de usuario</label>
               	</div>
			</div>
			<div class="row">
               <div class="input-field col s12 l3 offset-l3">
					<form:select path="activo">
						<form:option value="">Todos</form:option>
						<form:option value="1">Activos</form:option>
						<form:option value="0">Inactivos</form:option>
					</form:select>
                   <label>Estado</label>
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
                                <th>Usuario</th>
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
                                    <td><c:out value = "${usuario.usuario}"/></td>
                                    <td><c:out value = "${usuario.nombre}"/></td>
                                    <td><c:out value = "${usuario.apellido}"/></td>
                                    <td><c:out value = "${usuario.documento}"/></td>
                                    <td>
                                    	<c:if test = "${principal.username ne usuario.usuario}">
	                                    	<c:choose>
	                                    		<c:when test="${usuario.activo eq 1}">
	                                    			<a href = "${urlDesactivar}/<c:out value = "${usuario.id}"/>"class="waves-effect waves-light btn-small deep-purple darken-3">
			                                        	<i class="material-icons">close</i><span class = "hide-on-small-only vertical-align">&nbsp;Desactivar</span>
			                                        </a>
	                                    		</c:when>
	                                    		<c:otherwise>
	                                    			<a href = "${urlActivar}/<c:out value = "${usuario.id}"/>"class="waves-effect waves-light btn-small teal accent-4">
			                                        	<i class="material-icons">done</i><span class = "hide-on-small-only vertical-align">&nbsp;Activar</span>
			                                        </a>
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                        <a href = "${urlEditar}/<c:out value = "${usuario.id}"/>"class="waves-effect waves-light btn-small red lighten-1">
	                                        	<i class="material-icons">edit</i><span class = "hide-on-small-only vertical-align">&nbsp;Editar</span>
	                                        </a>
	                                        <c:if test = "${usuario.usuarioTipo.id ne 1}">
	                                            <a href = "#" data-id = "<c:out value = "${usuario.id}"/>" data-nombre = "<c:out value = "${usuario.nombre}"/>" class="waves-effect waves-light btn-small blue lighten-1 reasignar">
	                                            	<i class="material-icons">autorenew</i><span class = "hide-on-small-only vertical-align">&nbsp;Reasignar</span>
	                                            </a>
	                                        </c:if>
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
