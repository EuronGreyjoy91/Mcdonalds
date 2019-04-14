<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>

<script>
    var _alert = "<c:out value = '${response}'/>";
</script>
<script src = "js/abmUsuario.js"></script>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Items del pedido <i><c:out value = "${idPedido}"/></i> <button class="waves-effect waves-light red btn-floating right" onclick="window.history.go(-1)"><i class="material-icons">arrow_back</i></button></h3>
        <c:choose>
            <c:when test = "${not empty itemsPedido}">
                <div class = "row margin-top-30-px">
                    <table class = "responsive-table centered highlight striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Item</th>
                                <th>Cantidad</th>
                                <th>Monto</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var = "total" scope = "session" value = "0"/>
                            <c:forEach items = "${itemsPedido}" var = "itemPedido">
                                <tr>
                                    <td><c:out value = "${itemPedido.id}"/></td>
                                    <td><c:out value = "${itemPedido.item.nombre}"/></td>
                                    <td><c:out value = "${itemPedido.cantidad}"/></td>
                                    <td>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${itemPedido.monto}" /></td>
                                    
                                    <c:set var="total" value="${total + itemPedido.monto}"/>
                                </tr>
                            </c:forEach>
                                <tr>
                                    <td colspan = "3" style = "text-align: right"><b>Total: </b></td>
                                    <td><b>$ <fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${total}" /></b></td>
                                </tr>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <h5 class = "center-align"><i>No se encontraron resultados</i></h5>
            </c:otherwise>
        </c:choose>
	</div>
</body>

</html>
