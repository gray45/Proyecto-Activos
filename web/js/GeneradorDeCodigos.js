/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function crearCodigoBarras(codigos) {
    //aqui se debe generar los codigos de barras segun 

}

function generarCodigos(bien, categoria) {
    var codigos = new Array();
    var unCodigo;

    for (var i = 0, max = bien.cantidad; i < max; i++) {
        unCodigo = categoria.id +"-"+ bien.id +"-"+ i;
        codigos.push[unCodigo];
    }
    var div = $("<div></div>"); 
    $("#bcTarget").barcode("1234567890128", "ean13"); 
    
    return codigos;
}


function getBienes(){
    
    
   
}