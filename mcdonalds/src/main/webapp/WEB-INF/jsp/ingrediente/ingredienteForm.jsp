<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/ingredientes/guardar" var = "urlGuardar"></spring:url>
    <div style = "margin-left: 20px; margin-right: 20px">
        <c:choose>
            <c:when test = "${empty ingrediente.nombre}">
                <h3>Nuevo ingrediente</h3>
            </c:when>
            <c:otherwise>
                <h3>Editar ingrediente</h3>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <form:form class="col s12 l6" action = "${urlGuardar}" modelAttribute="ingrediente" method = "POST">
            	<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                <form:hidden id = "id" path = "id" />
                <div class="row">
                    <div class="input-field col s12 l6">
                        <form:input placeholder="Nombre" id="nombre" path = "nombre" type="text" class="validate" required = "required" />
                        <label for="nombre">Nombre</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light btn-yellow" type="submit" name="action">Guardar
                    <i class="material-icons right">add</i>
                </button>
            </form:form>
        </div>
	</div>
</body>

</html>
