<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/ingredientes/nuevo" var = "urlNuevo"></spring:url>
<spring:url value="/ingredientes/listar" var = "urlListar"></spring:url>
<spring:url value="/ingredientes/editar" var = "urlEditar"></spring:url>
<spring:url value="/ingredientes/desactivar" var = "urlDesactivar"></spring:url>
<spring:url value="/ingredientes/activar" var = "urlActivar"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
</script>
<script src = "${urlResources}/js/abmIngrediente.js"></script>
	<c:set var = "queryString" scope = "session" value = "&nombre=${ingrediente.nombre}&activo=${ingrediente.activo}"/>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Ingredientes <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nuevo</a>
        <form:form class="col l6 center-align" action = "${urlListar}/0" method = "GET" autocomplete = "off" style = "margin-top: 20px" modelAttribute="ingrediente">
           <div class="row">
              	<div class="input-field col s12 l3 offset-l3">
					<form:input placeholder="Nombre" id="nombre" path = "nombre" type="text" class="validate"/>
                    <label for="nombre">Nombre</label>
               	</div>
               	<div class="input-field col s12 l3">
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
            <c:when test = "${not empty ingredientes}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Acciones</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${ingredientes}" var = "ingrediente">
                                <tr>
                                    <td><c:out value = "${ingrediente.id}"/></td>
                                    <td><c:out value = "${ingrediente.nombre}"/></td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${ingrediente.activo eq 1}">
                                    			<a href = "${urlDesactivar}/<c:out value = "${ingrediente.id}"/>"class="waves-effect waves-light btn-small deep-purple darken-3">
		                                        	<i class="material-icons">close</i><span class = "hide-on-small-only vertical-align">&nbsp;Desactivar</span>
		                                        </a>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<a href = "${urlActivar}/<c:out value = "${ingrediente.id}"/>"class="waves-effect waves-light btn-small teal accent-4">
		                                        	<i class="material-icons">done</i><span class = "hide-on-small-only vertical-align">&nbsp;Activar</span>
		                                        </a>
                                    		</c:otherwise>
                                    	</c:choose>
                                        <a href = "${urlEditar}/<c:out value = "${ingrediente.id}"/>"class="waves-effect waves-light btn-small red lighten-1">
                                        	<i class="material-icons">edit</i><span class = "hide-on-small-only vertical-align">&nbsp;Editar</span>
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
	</div> 
</body>

</html>
