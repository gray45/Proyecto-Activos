/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



document.addEventListener("DOMContentLoaded", loaded);
function loaded(event) {
    //una vez que inicio el programa
    // se evnia  al servidor
    //traer todas las categorias...
    ajaxPromise({type: "GET", url: "api/DetalleSeleccionarCategoria", contentType: "application/json"})
            .then(function (categorias) {
                mostrarCategorias(categorias);
            });

    var campoCategorias = document.getElementById("camposCategorias");
    showCategoria("seleccione una categoria", campoCategorias);
    
    //traer los bienes a la pag y hacer la paginacion

}

function mostrarCategorias(categorias) {
    var campoCategorias = document.getElementById("camposCategorias");
    categorias.forEach((categoria) => {
        showCategoria(categoria.descripcion, campoCategorias);
    });
}

function procesarBoton() {
    var elementos = document.getElementsByClassName("seleccionadorCategoria");
    
    elementos.forEach((campo)=>{
        if(campo.value() === "seleccione una categoria" ){
             showAlert("Rechazada", "Debe seleccionar la categoria de cada uno de los bienes para procesarlos");
        }
        else{
            ajaxPromise({type: "GET", url: "api/DetalleSeleccionarCategoria", contentType: "application/json"})
            .then(function (categorias) {
                mostrarCategorias(categorias);
            });
        }
        });
}

function showCategoria(categoria, where) {
    var option = document.createElement("option");               // Create a <li> node
    var textnode = document.createTextNode(categoria);          // Create a text node
    option.appendChild(textnode);                              // Append the text to <li>
    option.addEventListener("click", categoriaSeleccionada());
    option.classList.add("seleccionadorCategoria");
    where.appendChild(option);
}

