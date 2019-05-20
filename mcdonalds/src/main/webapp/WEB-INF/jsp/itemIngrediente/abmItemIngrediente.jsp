<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/itemIngredientes/item" var = "urlNuevo"></spring:url>
<spring:url value="/itemIngredientes/itemIngrediente" var = "urlEditar"></spring:url>
<spring:url value="/itemIngredientes/item/${item.id}/listar" var = "urlListar"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
</script>
<script src = "${urlResources}/js/abmItemIngrediente.js"></script>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>
	        Ingredientes del Item <i><c:out value = "${item.nombre}"/></i>
	        <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button>
        </h3>
        <a href = "${urlNuevo}/<c:out value = "${item.id}"/>/nuevo" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nuevo</a>
        <c:choose>
            <c:when test = "${not empty itemIngredientes}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Ingrediente</th>
                                <th>Cantidad</th>
                                <th>Acciones</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${itemIngredientes}" var = "itemIngrediente">
                                <tr>
                                    <td><c:out value = "${itemIngrediente.id}"/></td>
                                    <td><c:out value = "${itemIngrediente.ingrediente.nombre}"/></td>
                                    <td><c:out value = "${itemIngrediente.cantidad}"/></td>
                                    <td>
                                        <a href = "${urlEditar}/<c:out value = "${itemIngrediente.id}"/>/editar"class="waves-effect waves-light btn-small red lighten-1">
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
