<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/items/guardar" var = "urlGuardar"></spring:url>
    <div style = "margin-left: 20px; margin-right: 20px">
        <c:choose>
            <c:when test = "${empty item.nombre}">
                <h3>Nuevo item</h3>
            </c:when>
            <c:otherwise>
                <h3>Editar item</h3>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <form:form class="col s12 l6 form-validate" action = "${urlGuardar}" method = "POST" modelAttribute = "item">
            	<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                <form:hidden id = "id" path = "id"/>
                <div class="row">
                    <div class="input-field col s12 l6">
                        <form:input placeholder="Nombre" id="nombre" path = "nombre" type="text" class="validate" required = "required"/>
                        <label for="nombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 l6">
                        <form:input id="monto" path = "monto" type="number" class="validate" required = "required" step=".01"/>
                        <label for="monto">Monto</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light red" type="button" name="action" onclick="window.history.go(-1)">Cancelar
                    <i class="material-icons right">cancel</i>
                </button>
                <button class="btn waves-effect waves-light green" type="submit" name="action">Guardar
                    <i class="material-icons right">check</i>
                </button>
            </form:form>
        </div>
	</div>
</body>

</html>
