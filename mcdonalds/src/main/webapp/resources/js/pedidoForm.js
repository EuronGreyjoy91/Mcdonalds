var itemPedido = {
    item : null,
    cantidad : null,
    monto : null
}

var itemsPedido = [];
var totalPedido = 0;

$(document).ready(function(){
    
    $(".sumar-item").click(function() {
        var dataItem = $(this).attr("data-item");
        var cantidad = parseInt($(".cantidad-item"+dataItem).val());
        
        $(".cantidad-item"+dataItem).val(cantidad + 1);
        calcularTotalItem(dataItem);
        calcularTotalPedido();
    });
    
    $(".restar-item").click(function() {
        var dataItem = $(this).attr("data-item");
        var cantidad = parseInt($(".cantidad-item"+dataItem).val());
        
        if(cantidad > 0)
            cantidad--;
        
        $(".cantidad-item"+dataItem).val(cantidad);
        calcularTotalItem(dataItem);
        calcularTotalPedido();
    });
    
    $("#guardar-pedido").click(function(){
        var idItem;
        var cantidad;
        var precioUnitario;
        var monto;
        itemsPedido = [];

        $.each($("input[class^='validate cantidad-item']"), function(i, input){
            cantidad = parseInt($(input).val());

            if(cantidad > 0){
                idItem = $(input).attr("data-id-item");
                precioUnitario = parseFloat($(input).attr("data-precio-unitario"));
                monto = cantidad * precioUnitario;

                itemPedido = {
                    item : idItem,
                    cantidad : cantidad,
                    monto : monto
                }

                itemsPedido.push(itemPedido);
            }

        });
        
        if(itemsPedido.length == 0)
            M.toast({html: "Por favor, agregue al menos un &iacute;tem al pedido"});
        else{
            $.ajax({
                type: 'POST',
                url: "guardar",
                data: {
	                	"itemsPedido" : JSON.stringify(itemsPedido),
	                	"montoPedido" : totalPedido,
	                	"_csrf" : $("#_csrf").val()
                		},
                dataType: "text",
                success: function(resultData) {
                    window.location.href = "redirectToListar";
                },
                error: function (request, status, error) {
                    alert(request.responseText);
                }
            });
        }
    });
    
    
});

function calcularTotalItem(dataItem){
    
    var total = 0;
    
    var cantidad = parseInt($(".cantidad-item" + dataItem).val());
    var precioUnitario = parseFloat($(".precio-unitario-item" + dataItem).val());

    total += cantidad * precioUnitario;

    $(".total-item"+dataItem).val(total.toFixed(2));
    
}

function calcularTotalPedido(){
    
    totalPedido = 0;
    
    $.each($("input[class^='validate cantidad-item']"), function(i, input){
        
        var cantidad = parseInt($(input).val());
        var precioUnitario = parseFloat($(input).attr("data-precio-unitario"));
        
        totalPedido += cantidad * precioUnitario;
    });
    
    $("#total-pedido").html("Total pedido: $" + totalPedido.toFixed(2));
}