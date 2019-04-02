<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>
<spring:url value="/items/nuevo" var = "urlNuevo"></spring:url>
<spring:url value="/items/editar" var = "urlEditar"></spring:url>
<spring:url value="/items/listar" var = "urlListar"></spring:url>
<spring:url value="/itemIngredientes/item" var = "urlItemIngrediente"></spring:url>
<script>
    var _alert = "<c:out value = '${response}'/>";
</script>
<script src = "${urlResources}/js/abmItem.js"></script>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Items</h3>
        <a href = "${urlNuevo}" class="waves-effect waves-light btn btn-yellow"><i class="material-icons left">add</i>Nuevo</a>
        <c:choose>
            <c:when test = "${not empty items}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Monto</th>
                                <th>Ingredientes</th>
                                <th>Acciones</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${items}" var = "item">
                                <tr>
                                    <td><c:out value = "${item.id}"/></td>
                                    <td><c:out value = "${item.nombre}"/></td>
                                    <td>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${item.monto}" /></td>
                                    <td>
                                        <a href = "${urlItemIngrediente}/<c:out value = "${item.id}"/>/listar/0"class="waves-effect waves-light btn-small green lighten-1"><i class="material-icons left">add</i>Administrar</a>
                                    </td>
                                    <td>
                                        <a href = "${urlEditar}/<c:out value = "${item.id}"/>"class="waves-effect waves-light btn-small red lighten-1"><i class="material-icons left">edit</i>Editar</a>
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
