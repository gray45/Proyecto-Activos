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
        if(error === "usuarioInvalid"){
            $("#divUserName").addClass("has-error");
            $("#divPassword").addClass("has-error");
            $("#divUsuarioInvalid").show();
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