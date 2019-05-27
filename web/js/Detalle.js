/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    findAllBySolicitud(1);
    findAllCategoria();
});
function findAllBySolicitud(numPage) {
    var id = $("#idSolicitud").val();
    $.ajax({

        url: "api/Bien/" + id
        ,
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'POST',
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
    var rol = $("#rol").val();
    var row = $('<tr />');
    $("#tbody").append(row);
    row.append($("<td>" + rowData.idBien + "</td>"));
    row.append($("<td>" + rowData.descripcion + "</td>"));
    row.append($("<td>" + rowData.marca + "</td>"));
    row.append($("<td>" + rowData.precio + "</td>"));
    row.append($("<td>" + rowData.cantidad + "</td>"));
    row.append($("<td>" + rowData.funcionario + "</td>"));
    row.append($("<td><button type='button' class='btn btn-md' aria-label='rigth Align' onclick='mostrarModal(" + rowData.idSolicitud + ")'>"
            + " <i class='far fa-times-circle " + rowData.estado + " ' ></i></button> &nbsp;&nbsp" + rowData.estado + "</td>"));

    row.append($("<td><button type='button' class='btn btn-md' aria-label='rigth Align' onclick='mostrarModalCategria(" + rowData.idBien + ")'>"
            + " <i class='fas fa-arrow-circle-right ' ></i> &nbsp;&nbsp Asignar</button></td>"));

}


function findAllCategoria() {
    $.ajax({

        url: "api/Categoria/findAll"
        ,
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            llenarAutoCompleteCategora(data);
        },
        type: 'GET',
        dataType: "json"
    });
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


function llenarAutoCompleteCategora(data) {
    var opcions = {
        data,
        getValue: "descripcion",

        list: {
            match: {
                enabled: true
            }
        },

        theme: "dark-light"
                //gris oscuro
    };
    $("#autoComplete").easyAutocomplete(opcions);
}

function mostrarModalCategria(idBien) {
    $("#idBien").val(idBien);
    $("#modalCategoria").modal("show");
}

//function dibujarPanel(numpag, dataJson) {
//
//    $("#panel").html("");
//    var cont = 0;
//    var i = 8 * (numpag - 1);
//    for (; i < dataJson.length && (cont < 8); i+4, cont++) {
//        var row = $('<div/>');
//        row.addClass("row");
//        $("#panel").append(row);
//        row.append($("<div class = 'col-md-3' >" + if()
//                '<i class="fas fa-exclamation-triangle btn-lg Verificar prefix "></i>' +
//                '<b style="display: inline">' + dataJson.descripcion + "</b></div>"));
//    }
//
//}