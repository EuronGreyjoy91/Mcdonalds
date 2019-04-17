var itemPedido = {
    item : null,
    cantidad : null,
    monto : null
}

var itemsPedido = [];
var totalPedido = 0;

$(document).ready(function(){
    
    $("#btn-guardar").click(function(){
    	//VALIDAMOS QUE EL NOMBRE DE USUARIO NO ESTE DUPLICADO
    
    	if($("#usuario-form").valid()){
    		
    		var usuarioValido = true;
    		var valorUsuarioOriginal = $("#usuario").attr("data-usuario-original");

    		if($("#usuario").val() != valorUsuarioOriginal){
    			$.ajax({
    				async: false,
    				type: 'GET',
    				url: "validar",
    				data: {
    					"usuario" : $("#usuario").val(),
    					"_csrf" : $("#_csrf").val()
    				},
    				success: function(valido) {
    					if(valido)
    						usuarioValido = true;
    					else
    						usuarioValido = false;
    				},
    				error: function (request, status, error) {
    					console.log(request.responseText);
    				}
    			});
    		}
    		
    		
    		if(usuarioValido)
    			$("#usuario-form").submit();
    		else{
    			M.toast({html: "El nombre de usuario ya se encuentra en uso, ingrese otro por favor."});
    			$("#usuario").val("");
    		}
    	}
    	
    });
    
});
