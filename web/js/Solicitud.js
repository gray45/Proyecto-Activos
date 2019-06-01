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
    var rol = $("#rol").val();
    if(rol === "Secretaria "){
        $("#divConsejo").show();
    }
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
    row.append($("<td>" + rowData.comprobante + "</td>"));
    row.append($("<td>" + rowData.dependecia + "</td>"));
    row.append($("<td>" + rowData.fecha + "</td>"));
    row.append($("<td>" + rowData.tipo + "</td>"));
    if (rol === "Secretaria ") {
        row.append($("<td><button type='button' class='btn btn-md' aria-label='rigth Align' onclick='mostrarModal(" + rowData.idSolicitud + ")'>"
                + " <i class='fas fa-check-circle " + rowData.estado + " ' ></i> &nbsp;&nbsp " + rowData.estado + "</button></td>"));
    } else {
        row.append($("<td><i class='fas fa-check-circle " + rowData.estado + " ' ></i> &nbsp;&nbsp" + rowData.estado + "</td>"));
    }
    row.append($('<td><button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="detalle(' + rowData.idSolicitud + ')">' +
            '<i class="fas fa-info-circle " style="color : blue;" aria-hidden="true"></i>&nbsp;&nbsp Detalle</td>'));
}


function detalle(idSolicitud) {
    location.href = "Controller/SolicitudController?action=detalle&&id=" + idSolicitud;
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

function mostrarModal(id) {
    $('#modalLoginForm').modal('show');
    $("#idSolicitud").val(id);
    $('#divRechazada').show();
    getSolicitud(id);
}


function changeState(state, razon) {
    $('#modalLoginForm').modal('hide');

    $.ajax({

        url: "SecretariaController",

        data: {
            accion: "changeState",
            idSolicitud: $("#idSolicitud").val(),
            state: state,
            razon: razon
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function () { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            findAll(1);
            $('#successModal').modal('show');

        },
        type: 'GET',
        dataType: "json"
    });
}

function rechazar(state) {
    $("#divTextArea").show();
    $("#estado").val(state);
    $('#divRechazada').hide();
    $("#confirmarRechazo").show();
}

function razonRechazo() {
    if (validar()) {
        var state = $("#estado").val();
        var razon = $("#razon").val();
        changeState(state, razon);
        $("#divTextArea").hide();
        $("#divAprobar").show();
        $("#confirmarRechazo").hide();
    }
}

function validar() {
    var error = true;
    $("#divMensaje").hide();
    $("#divTextArea").removeClass("has-error");
    var razon = $("#razon").val();
    if (razon === "") {
        $("#divTextArea").addClass("has-error");
        $("#divMensaje").show();
        error = false;
    } else {
        $("#divMensaje").hide();
        error = true;
    }
    return error;
}

function salir() {
    $("#razon").val("");
    $("#divAprobar").show();
    $('#divRechazada').show();
    $("#divTextArea").hide();
    $('#modalLoginForm').modal('hide');
    $("#confirmarRechazo").hide();
    $("#divTextArea").removeClass("has-error");
    $("#divMensaje").hide();
}

function getSolicitud(idSolicitud) {
    $.ajax({

        url: "SecretariaController",

        data: {
            accion: "getSolicitud",
            idSolicitud: idSolicitud
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data

            if (data.estado === "Rechazada") {
                if (data.rasonRechazo !== "" || data.rasonRechazo !== undefined) {
                    $('#razon').val(data.rasonRechazo);
                    $('#divTextArea').show();
                    $('#divRechazada').hide();
                    $('#confirmarRechazo').hide();
                } else {
                    $('#razon').val("");
                    $('#divTextArea').hide();
                }
            }


        },
        type: 'GET',
        dataType: "json"
    });
}

/*function llenarAutoCompleteUsuario(data) {
 var opcions = {
 data,
 getValue: "nombreUsuario",
 
 list: {
 match: {
 enabled: true
 }
 },
 template: {
 type: "description",
 fields: {
 description: "nombre"
 }
 },
 theme: "dark-light"
 //gris oscuro
 };
 $("#buscarUsu").easyAutocomplete(opcions);
 }*/