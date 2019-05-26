/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function showAlert(clase, texto) {
    $("#icono").removeClass("Recibida");
    $("#icono").removeClass("Verificar");
    $("#icono").removeClass("Rechazada");
    $("#icono").removeClass("Asignada");
    $("#mensaje").text("");
    
    $("#icono").addClass(clase);
    $("#mensaje").text(texto);
    
    $("#alerModalInfo").modal("show");
}