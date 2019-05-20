<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/usuarios/guardar" var = "urlGuardar"></spring:url>
<script src = "${urlResources}/js/usuarioForm.js"></script>
    <div style = "margin-left: 20px; margin-right: 20px">
        <c:choose>
            <c:when test = "${empty usuario.id}">
                <h3>Nuevo usuario</h3>
            </c:when>
            <c:otherwise>
                <h3>Editar usuario</h3>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <form:form class="col l6 form-validate" action = "${urlGuardar}" method = "POST" modelAttribute="usuario" autocomplete = "off" id = "usuario-form">
            	<input type = "hidden" id = "_csrf" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                <form:hidden id = "id" path = "id" />
                <div class="row">
                    <div class="input-field col s12 l3">
                        <form:input placeholder="Nombre" id="nombre" path = "nombre" type="text" class="validate" required = "required"/>
                        <label for="nombre">Nombre</label>
                    </div>
                    <div class="input-field col s12 l3">
                        <form:input placeholder="Apellido" id="apellido" path = "apellido" type="text" class="validate" required = "required"/>
                        <label for="last_name">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 l6">
                        <form:input placeholder="Documento" id="documento" path = "documento" type="number" class="validate" required = "required"/>
                        <label for="documento">Documento</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 l6">
                        <form:input placeholder="Usuario" id="usuario" path = "usuario" type="text" class="validate" required = "required" data-usuario-original = "${usuario.usuario}"/>
                        <label for="usuario">Usuario</label>
                    </div>
                </div>
                <c:if test = "${empty usuario.id}">
	                <div class="row">
	                    <div class="input-field col s12 l6">
	                        <form:input id="contrasenia" path = "contrasenia" type="password" class="validate" required = "required"/>
	                        <label for="password">Contrase&ntilde;a</label>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s12 l6">
	                        <input id="repetir-contrasenia" name = "repetir-contrasenia" type="password" class="validate" required = "required" equalTo = "#contrasenia"/>
	                        <label for="repetir-contrasenia">Repetir contrase&ntilde;a</label>
	                    </div>
	                </div>
                </c:if>
                <div class = "row">
                    <div class="input-field col s12 l6">
                        <form:select path = "usuarioTipo.id" items = "${usuarioTipos}" itemValue = "id" itemLabel = "nombre"/>
                        <label>Tipo</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light red" type="button" name="action" onclick="window.history.go(-1)">Cancelar
                    <i class="material-icons right">cancel</i>
                </button>
                <button class="btn waves-effect waves-light green" type="button" name="action" id = "btn-guardar">Guardar
                    <i class="material-icons right">check</i>
                </button>
            </form:form>
        </div>
	</div>
</body>

</html>
