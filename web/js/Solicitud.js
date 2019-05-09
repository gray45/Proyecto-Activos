/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    findAll(1);
});

function findAll(numPage) {
    $.ajax({

        url: "SecretariaController",

        data: {
            accion: "findAll"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'GET',
        dataType: "json"
    });

}

function dibujarTabla(numpag, dataJson) {
    $("#tbody").html("");
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
    $("#tbody").append(row);
    row.append($("<td>" + rowData.comprobante + "</td>"));
    row.append($("<td>" + rowData.dependecia + "</td>"));
    row.append($("<td>" + rowData.fecha + "</td>"));
    row.append($("<td>" + rowData.tipo + "</td>"));
    row.append($("<td> <i class='fas fa-check-circle  btn-md Aprobada' onclick='mostrarModal()'></i>&nbsp;&nbsp" + rowData.estado + "</td>"));
    row.append($('<td><button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="detalle()">' +
            '<i class="fas fa-info-circle " style="color : blue;" aria-hidden="true"></i></td>'));
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

function buscar(numPage) {
    $.ajax({

        url: "SecretariaController",

        data: {
            accion: "buscar",
            quest: $("#quest").val()
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'GET',
        dataType: "json"
    });

}

function mostrarModal() {
    $('#modalLoginForm').modal('show');
}
