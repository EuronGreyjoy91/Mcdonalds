<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/itemIngredientes/guardar" var = "urlGuardar"></spring:url>
    <div style = "margin-left: 20px; margin-right: 20px">
        <c:choose>
            <c:when test = "${empty itemIngrediente.id}">
                <h3>Nuevo item ingrediente</h3>
            </c:when>
            <c:otherwise>
                <h3>Editar item ingrediente</h3>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <form:form class="col s6 form-validate" action = "${urlGuardar}" method = "POST" modelAttribute = "itemIngrediente">
            	<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                <form:hidden id = "id" path = "id"/>
                <form:hidden path = "item.id" value = "${idItem}"/>
                <div class="row">
                    <div class="input-field col s6">
                        <form:input id="cantidad" path = "cantidad" type="text" class="validate" required = "required"/>
                        <label for="cantidad">Cantidad</label>
                    </div>
                </div>
                <div class = "row">
                    <div class="input-field col s6">
                        <form:select path = "ingrediente.id" items = "${ingredientes}" itemValue = "id" itemLabel = "nombre" required = "required"/>
                        <label>Ingrediente</label>
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
