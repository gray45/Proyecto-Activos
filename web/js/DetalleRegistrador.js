/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



document.addEventListener("DOMContentLoaded", loaded);
function loaded(event) {
    //una vez que inicio el programa


    var campoCategorias = document.getElementById("camposCategorias");

    var categorias = new Array();
    //traer todas las categorias...


    // se evnia  al servidor
    ajaxPromise({type: "GET", url: "api/DetalleSeleccionarCategoria", contentType: "application/json"})
            .then(function (){ console.log("funciono"); });
    showCategoria("seleccione una categoria", campoCategorias);
    categorias.forEach((categoria) => {
        showCategoria(categoria, campoCategorias);
    });
}
function procesarBoton() {
    console.log("procesando numeros de activos...");

}

function showCategoria(categoria, where) {

    var option = document.createElement("option");                 // Create a <li> node
    var textnode = document.createTextNode(categoria);         // Create a text node
    option.appendChild(textnode);                              // Append the text to <li>

    where.appendChild(option);
}

