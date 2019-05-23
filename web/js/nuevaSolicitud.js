/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    verficarErrores();
    var tipoTxt = $("#tipoTxt").val();
    if(tipoTxt !== "--"){
     $("#tipo").val(tipoTxt);
     changeType();
 } 
});

function verficarErrores(){
    var error = $("#error").val();
    var erroresBien = $("#erroresBien").val(); 
    
    if(error !== ""){
       
        
        if(error !== "0,0"){
             if(error === "isEmpty"){
            $("#divDescripcion").addClass("has-error");
            $("#divModelo").addClass("has-error");
            $("#divMarca").addClass("has-error");
            $("#divPrecio").addClass("has-error");
            $("#divCantidad").addClass("has-error");
            $("#alerModal").modal("show");
        }
            var splitError = error.split(",");
            if(splitError[0] === "1"){
                 $("#divComprobante").addClass("has-error");
                 $("#divComprobanteRequired").show();
            }
            if(splitError[1] === "1"){
                 $("#divTipo").addClass("has-error");
                 $("#divTipoRequired").show();
            }
            
            if(error === "repetida"){
                $("#divComprobante").addClass("has-error");
                 $("#alerModal2").modal("show");
            }
        }
    }
    
    if(erroresBien !== ""){
       
        
        if(erroresBien !== "0,0,0,0,0"){
            $("#divErrores").show();
            var splitError = erroresBien.split(",");
            if(splitError[0] === "1"){
                  $("#divDescripcion").addClass("has-error");
            }
            
            if(splitError[1] === "1"){
                 $("#divModelo").addClass("has-error");
            }
            
             if(splitError[2] === "1"){
                  $("#divMarca").addClass("has-error");
            }
            
            if(splitError[3] === "1"){
                    $("#divPrecio").addClass("has-error");
            }
            
             if(splitError[4] === "1"){
                $("#divCantidad").addClass("has-error");
            }
        }
    }
}

function agregar(action){
     $("#action").val(action);
    $("#form").submit();
} 


function changeType(){
    var tipo = $("#tipo").val();
    if(tipo === "compra"){
       $("#iconTipo").removeClass("fas fa-money-bill-wave-alt");
       $("#iconTipo").removeClass("fas fa-heart Verificar");
       $("#iconTipo").addClass("fas fa-dollar-sign Asignada");
    }
    if(tipo === "donacion"){
       $("#iconTipo").removeClass("fas fa-money-bill-wave-alt");
       $("#iconTipo").removeClass("fas fa-dollar-sign Asignada");
       $("#iconTipo").addClass("fas fa-heart Verificar");
    }
    
    if(tipo === "--"){
       $("#iconTipo").removeClass("fas fa-heart Verificar");
       $("#iconTipo").removeClass("fas fa-dollar-sign Asignada");
       $("#iconTipo").addClass("fas fa-money-bill-wave-alt");
    }
}