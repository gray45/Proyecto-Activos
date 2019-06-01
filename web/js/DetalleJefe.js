/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function buscar() {
    var data = new Array();
    var txt = $("#quest").val();
    if (txt !== "") {
        ajax({type: "GET",
            url: "api/DetalleBuscarRegistrador/" + txt,
            contentType: "application/json",
            success: function (data) {
                $("#camposDeSugerencias").html('');
                list(data, document.getElementById("camposDeSugerencias"));
            },
            error: function (status) {
                alert(errorMessage(status));
            }
        });
    } else {
        $("#camposDeSugerencias").html('');
    }
}

function list(data, where) {
    data.forEach((registrador) => {
        row(registrador, where);
    });
}

function mostrarRegistrador(registrador, where) {
    where.html('');

    var nombre = registrador.nombre;
    var id = registrador.id;
    var div = document.createElement("div");
    div.classList.add("contenedor2");
    div.innerHTML = "<h1>Registrador: </h1>" +
            "<span>" + nombre + " " + id + "</span>";
    where.appendChild(div);
}


function row(registrador, where) {

    var nombre = registrador.nombre;
    var id = registrador.id;
    var div = document.createElement("div");
    div.classList.add("contenedor2");

    //div.addEventListener("mouseover", onRowRegistradores(div));

    div.innerHTML = "<div onclick='seleccionRegistrador(" + '"' + nombre + '"' + ");'>" + nombre + " " + id + "</div>";


    where.appendChild(div);

}

