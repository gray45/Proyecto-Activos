/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    findAllBySolicitud(1);
    findAllCategoria(1);
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


function findAllCategoria(numPage) {
    $.ajax({

        url: "api/Categoria/findAll"
        ,
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarPanel(numPage, data);
            paginadorModal(numPage, data.length / 9);
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

function dibujarPanel(numpag, dataJson) {

    $("#panel").html("");
    var cont = 0;
    var i = 9 * (numpag - 1);
    for (; i < dataJson.length && (cont < 9); i += 3, cont += 3) {
        var row = $('<div/>');
        row.addClass("row");
        $("#panel").append(row);
        if (dataJson[i] !== "undefined") {
            row.append($("<div class = 'col-md-4' >" +
                    '<i id="check' + dataJson[i].id + '" class="fas fa-check-circle btn-lg  prefix check " onclick="setCategoria(' + dataJson[i].id + ')"></i>' +
                    '<b style="display: inline">' + dataJson[i].descripcion + "</b></div>"));
        }
        if (dataJson[i + 1] !== "undefined") {
            row.append($("<div class = 'col-md-4' >" +
                    '<i id="check' + dataJson[i + 1].id + '" class="fas fa-check-circle btn-lg prefix check" onclick="setCategoria(' + dataJson[i + 1].id + ')"></i>' +
                    '<b style="display: inline">' + dataJson[i + 1].descripcion + "</b></div>"));
        }

        if (dataJson[i + 2] !== "undefined") {
            row.append($("<div class = 'col-md-4' >" +
                    '<i id="check' + dataJson[i + 2].id + '" class="fas fa-check-circle btn-lg prefix check" onclick="setCategoria(' + dataJson[i + 2].id + ')"></i>' +
                    '<b style="display: inline">' + dataJson[i + 2].descripcion + "</b></div>"));
        }

    }

}

function paginadorModal(pagAct, tam) {
    var ini = 1;
    $("#paginacionOpcModal").html("");
    if (pagAct > 3) {
        ini = pagAct - 3;
        $("#paginacionOpcModal").append('<li onclick="findAllCategoria(' + ini + '),paginadorModal(' + (pagAct - 1) + ',' + tam + ')"><a>&laquo;</a></li>');
    } else {
        $("#paginacionOpcModal").append('<li onclick="findAllCategoria(' + ini + '), paginadorModal(' + (pagAct - 1) + ',' + tam + ')" ><a>&laquo;</a></li>');
    }
    for (var i = 0; i < tam; i++, ini++) {
        if (ini === pagAct) {
            $("#paginacionOpcModal").append('<li class="active" onclick="findAllCategoria(' + ini + '),paginadorModal(' + ini + ',' + tam + ') "><a>' + ini + '</a></li> ');
        } else {
            $("#paginacionOpcModal").append('<li onclick="findAllCategoria(' + ini + '),paginadorModal(' + ini + ',' + tam + ') "><a>' + ini + '</a></li>');
        }
    }
    $("#paginacionOpcModal").append('<li onclick="findAllCategoria(' + (ini - 1) + '), paginadorModal(' + (ini - 1) + ',' + tam + ')"><a>&raquo;</a></li>');
}

function  setCategoria(id) {
    $(".check").removeClass("Asignada");
    $("#check" + id).addClass("Asignada");
    $("#idCategoria").val(id);
}

function asignarCategoria() {
    var asignar = $("#idBien").val() + "," + $("#idCategoria").val();
    $.ajax({

        url: "api/Bien/" + asignar,

        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Hubo un problema al asignar la categoria");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data === "bien") {
                showAlert("Asignada", "Se asigno corretamente la categoria");
                $(".check").removeClass("Asignada");
                findAllBySolicitud(1);
                $("#modalCategoria").modal("hide");
            }
        },
        type: 'POST',
        dataType: "text"
    });

}