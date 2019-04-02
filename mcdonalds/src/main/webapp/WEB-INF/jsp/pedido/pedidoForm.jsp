<%-- 
    Document   : abmUsuario
    Created on : 24/09/2018, 10:18:54 PM
    Author     : Federico
--%>

<%@ include file="../cabecera.jsp" %>

<script>
    var _idUsuario = "<c:out value = '${id}'/>";
</script>

<script src = "${urlResources}/js/pedidoForm.js"></script>
    <div style = "margin-left: 20px; margin-right: 20px">
        <h3>Nuevo pedido</h3>
        <div class="row margin-top-30-px">
            <form class="col s12 l6" action = "PedidoController" method = "POST">
                <h4>Items del pedido</h4><hr>
                <input type = "hidden" id = "${_csrf.parameterName}" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                <input type = "hidden" id = "idVendedor" name = "id" value = "<c:out value = "${idVendedor}"/>"/>
                <input type = "hidden" id = "type" name = "type" value = "guardar"/>
                <br>
                <c:forEach items = "${items}" var = "item" varStatus="loop">
                     <div class="row">
                        <div class="input-field col s12 l3">
                            <input placeholder="Nombre" id="nombre" name = "nombre" type="text" class="validate" data-item = "${loop.index}" disabled value = "<c:out value = "${item.nombre}"/>">
                            <label for="nombre">Nombre</label>
                        </div>
                        <div class="input-field col s4 l2">
                            <input placeholder="Cantidad" type="number" class="validate cantidad-item${loop.index}" data-precio-unitario = "<c:out value = "${item.monto}"/>" data-id-item = "<c:out value = "${item.id}"/>" disabled value = "0">
                            <label for="nombre">Cantidad</label>
                        </div>
                        <div class="input-field col s4 l2">
                            <input placeholder="Precio Uni." type="number" class="validate precio-unitario-item${loop.index}" disabled value = "<c:out value = "${item.monto}"/>" step='0.01'>
                            <label for="nombre">Precio Uni.</label>
                        </div>
                        <div class="input-field col s4 l2">
                            <input placeholder="Total" type="number" class="validate total-item${loop.index}" disabled value = "0.00" step='0.01'>
                            <label for="nombre">Total</label>
                        </div>
                        <button class="btn waves-effect waves-light red col s5 offset-s1 l1 restar-item" type="button" name="action"  data-item = "${loop.index}"><i class="material-icons center">remove_circle</i></button>
                        <button class="btn waves-effect waves-light green col s5 l1 sumar-item" type="button" name="action" style = "margin-left: 10px"  data-item = "${loop.index}"><i class="material-icons center">add_circle</i></button>
                    </div>
                </c:forEach>
                <hr>
                <div class = "row">
                    <h4 id = "total-pedido">Total pedido: $0.00</h4>
                </div>
                <button class="btn waves-effect waves-light btn-yellow" type="button" name="action" id = "guardar-pedido">Guardar
                    <i class="material-icons right">add</i>
                </button>
            </form>
        </div>
	</div>
</body>

</html>
