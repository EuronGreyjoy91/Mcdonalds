$(document).ready(function(){
   if(_alert != ""){
        M.toast({html: _alert});
   }
   
   $(".despachar").click(function(){
       var id = $(this).attr("data-id-pedido");

       $(".modal-ok").attr("href", _urlDespacharPedido+"/"+id);
       $('.modal').modal('open');
       
       $(".modal-content").html(
            "<h3>Atenci&oacute;n</h3>"+
            "<h4>&#191;Esta seguro que desea despachar el pedido "+id+"?</h4>"
       );
   });

   
   $('.customDatePicker').pickadate({
	    format: 'dd/mm/yyyy',
	    monthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	    weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
	    today: 'Hoy',
	    clear: 'Borrar',
	    close: 'Cerrar'
	  });
//   $(".customDatePicker").datepicker();
});