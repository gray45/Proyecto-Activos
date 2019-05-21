/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    verficarErrores();
});

function verficarErrores(){
    var error = $("#error").val();
    
    if(error !== ""){
        if(error === "isEmpty"){
            $("#divDescripcion").addClass("has-error");
            $("#divModelo").addClass("has-error");
            $("#divMarca").addClass("has-error");
            $("#divPrecio").addClass("has-error");
            $("#divCantidad").addClass("has-error");
            $("#divIsEmpty").show();
        }
        
        if(error !== "0,0"){
            var splitError = error.split(",");
            if(splitError[0] === "1"){
                 $("#divUserName").addClass("has-error");
                 $("#divUserNameRequired").show();
            }
            if(splitError[1] === "1"){
                 $("#divPassword").addClass("has-error");
                 $("#divPasswordRequired").show();
            }
        }
    }
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