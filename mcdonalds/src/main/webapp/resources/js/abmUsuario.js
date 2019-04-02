$(document).ready(function(){
   if(_alert != ""){
        M.toast({html: _alert});
   }
   
   $(".reasignar").click(function(){
       
       var id = $(this).attr("data-id");
       var nombre = $(this).attr("data-nombre");
       
       $(".modal-ok").attr("href", _urlReasignar + "/" + id);
       $('.modal').modal('open');
       
       $(".modal-content").html(
            "<h3>Atenci&oacute;n</h3>"+
            "<h4>&#191;Esta seguro que desea reasignar al usuario "+nombre+"?</h4>"
       );
   });
});