/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    findAll(1);
    $("#descripcion").val("");
});

function findAll(numPage) {
    $.ajax({

        url: "api/Categoria/findAll",

        data: {
            accion: "findAll"
        },
        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Se presento un error a la hora de cargar la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'GET',
        contentType: "application/json"
    });

}

function add() {
    var add = $("#descripcion").val();
    $.ajax({

        url: "api/Categoria/" + add,

        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Hubo un problema al agregar la categoria");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data === "bien") {
                showAlert("Asignada", "Se agrego corretamente");

                findAll(1);
                $("#descripcion").val("");
            } else {
                showAlert("Rechazada", "Hubo un problema al agregar la categoria");
            }
        },
        type: 'POST',
        dataType: "text"
    });

}

function dibujarTabla(numpag, dataJson) {

    $("#bodyTabla").html("");
    var cont = 0;
    var i = 10 * (numpag - 1);
    for (; i < dataJson.length && (cont < 10); i++, cont++) {
        dibujarFila(dataJson[i]);
    }

}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    //de una persona
    var row = $('<tr />');
    $("#bodyTabla").append(row);
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.descripcion + "</td>"));

    row.append($('<td><button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="showEdit(' + rowData.id + ')">' +
            '<i class="fas fa-pencil-alt " style="color : blue;" aria-hidden="true"></i>'
            + '<button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="delete(' + rowData.id + ')">' +
            '<i class="fas fa-trash-alt Rechazada " aria-hidden="true"></i></td>'));

}

function paginador(pagAct, tam) {
    var ini = 1;
    $("#paginacionOpc").html("");
    if (pagAct > 5) {
        ini = pagAct - 5;
        $("#paginacionOpc").append('<li onclick="findAll(' + ini + '),paginador(' + (pagAct - 1) + ',' + tam + ')"><a>&laquo;</a></li>');
    } else {
        $("#paginacionOpc").append('<li onclick="findAll(' + ini + '), paginador(' + (pagAct - 1) + ',' + tam + ')" ><a>&laquo;</a></li>');
    }
    for (var i = 0; i < tam; i++, ini++) {
        if (ini === pagAct) {
            $("#paginacionOpc").append('<li class="active" onclick="findAll(' + ini + '),paginador(' + ini + ',' + tam + ') "><a>' + ini + '</a></li> ');
        } else {
            $("#paginacionOpc").append('<li onclick="findAll(' + ini + '),paginador(' + ini + ',' + tam + ') "><a>' + ini + '</a></li>');
        }
    }
    $("#paginacionOpc").append('<li onclick="findAll(' + (ini - 1) + '), paginador(' + (ini - 1) + ',' + tam + ')"><a>&raquo;</a></li>');
}

function showEdit(id) {
    $.ajax({

        url: "api/Categoria/" + id,

        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Hubo un problema al buscar la categoria");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            $("#descripcion").val(data.descripcion);
            $('html, body').animate({scrollTop: 0}, 'slow');
            $("#boton").removeClass("btn-success");
            $("#boton").text("");
            $("#boton").text("Editar");
            $("#h3Categoria").text("");
            $("#h3Categoria").text("Editar Categoria");
            $("#boton").addClass("btn-info");
            $("#divDescripcionCategoria").addClass("has-success");
            $("#divCancel").show("slow");
        },
        type: 'POST',
        contentType: "application/json"
    });

}

function Edit(id) {
    var edit = id + "," + $("#descripcion").val();
    $.ajax({

        url: "api/Categoria/" + edit,

        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Hubo un problema al buscar la categoria");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data === "bien") {
                showAlert("Asignada", "Se edito la categoria exitosamente");
                cancel();
            } else {
                showAlert("Rechazada", "Hubo un problema al editar la categoria");
            }
        },
        type: 'PUT',
        dataType: "text"
    });


}

function cancel() {
    $("#descripcion").val("");
    $("#boton").removeClass("btn-info");
    $("#boton").text("");
    $("#boton").text("Agregar");
    $("#h3Categoria").text("");
    $("#h3Categoria").text("Agregar Categoria");
    $("#boton").addClass("btn-success");
    $("#divDescripcionCategoria").removeClass("has-success");
    $("#divCancel").hide();
}