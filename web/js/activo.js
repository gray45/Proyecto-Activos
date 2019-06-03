/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    findAll(1);
    findAllFuncionario(1);
});

function findAll(numPage) {
    var findAll = "findAll";
     setTimeout (
    $.ajax({

        url: "api/Activo/" + findAll
        ,
        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada","Se presento un error a la hora de cargar la información en la base de datos");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'GET',
        dataType: "json"
    }), 2000); 
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
    row.append($("<td>" + rowData.codigo + "</td>"));
    row.append($("<td>" + rowData.descripcion + "</td>"));
    row.append($("<td>" + rowData.categoria + "</td>"));
    if(rowData.funcionario !== undefined){
    row.append($("<td><i class='fas fa-arrow-circle-right Asignada' ></i> &nbsp;&nbsp" + rowData.funcionarioNombre + "</td>"));
}
else{
    row.append($("<td><button type='button' class='btn btn-md' aria-label='rigth Align' onclick='mostrarModalFuncionarios(" +'"' + rowData.codigo + '"' + ")'>"
            + " <i class='fas fa-arrow-circle-right ' ></i> &nbsp;&nbsp Asignar</button></td>"));
    }
    
   row.append($('<td><button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="gfhg("' + rowData.codigo + '")">' +
            '<i class="fas fa-pencil-alt " style="color : blue;" aria-hidden="true"></i>'
            + '<button type="button" class="btn btn-md" aria-label="rigth Align"  onclick="showDelete(' + rowData.codigo + ')">' +
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

function findQuest(numPage) {
    var quest = $("#quest").val();
    $.ajax({

        url: "api/Activo/" + quest
        ,
        error: function () { //si existe un error en la respuesta del ajax
           
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(numPage, data);
            paginador(numPage, data.length / 10);
        },
        type: 'PATCH',
        dataType: "json"
    });
}

function mostrarModalFuncionarios(codigo) {
    $("#codigo").val(codigo);
    $("#modalFuncionarios").modal("show");
}

function dibujarPanel(numpag, dataJson) {

    $("#panel").html("");
    var cont = 0;
    var i = 6 * (numpag - 1);
    for (; i < dataJson.length && (cont < 6); i += 2, cont += 2) {
        var row = $('<div/>');
        row.addClass("row");
        $("#panel").append(row);
        if (dataJson[i] !== "undefined") {
            row.append($("<div class = 'col-md-6' >" +
                    '<i id="check' + dataJson[i].idFuncionario + '" class="fas fa-check-circle btn-lg  prefix check " onclick="setFuncionario(' + dataJson[i].idFuncionario + ')"></i>' +
                    '<b style="display: inline">' + dataJson[i].cedula + " - " + dataJson[i].nombre + "</b></div>"));
        }
        if (dataJson[i+1] !== "undefined") {
            row.append($("<div class = 'col-md-6' >" +
                    '<i id="check' + dataJson[i+1].idFuncionario + '" class="fas fa-check-circle btn-lg  prefix check " onclick="setFuncionario(' + dataJson[i+1].idFuncionario + ')"></i>' +
                    '<b style="display: inline">' + dataJson[i+1].cedula + " - " + dataJson[i+1].nombre + "</b></div>"));
        }


    }

}

function paginadorModal(pagAct, tam) {
    var ini = 1;
    $("#paginacionOpcModal").html("");
    if (pagAct > 2) {
        ini = pagAct - 2;
        $("#paginacionOpcModal").append('<li onclick="findAllFuncionario(' + ini + '),paginadorModal(' + (pagAct - 1) + ',' + tam + ')"><a>&laquo;</a></li>');
    } else {
        $("#paginacionOpcModal").append('<li onclick="findAllFuncionario(' + ini + '), paginadorModal(' + (pagAct - 1) + ',' + tam + ')" ><a>&laquo;</a></li>');
    }
    for (var i = 0; i < tam; i++, ini++) {
        if (ini === pagAct) {
            $("#paginacionOpcModal").append('<li class="active" onclick="findAllFuncionario(' + ini + '),paginadorModal(' + ini + ',' + tam + ') "><a>' + ini + '</a></li> ');
        } else {
            $("#paginacionOpcModal").append('<li onclick="findAllFuncionario(' + ini + '),paginadorModal(' + ini + ',' + tam + ') "><a>' + ini + '</a></li>');
        }
    }
    $("#paginacionOpcModal").append('<li onclick="findAllFuncionario(' + (ini - 1) + '), paginadorModal(' + (ini - 1) + ',' + tam + ')"><a>&raquo;</a></li>');
}

function  setFuncionario(id) {
    $(".check").removeClass("Asignada");
    $("#check" + id).addClass("Asignada");
    $("#idFuncionario").val(id);
}

function asignarFuncionario() {
    if( $("#idFuncionario").val()!== ""){
    var asignar = $("#codigo").val() + "," + $("#idFuncionario").val();
    $.ajax({

        url: "api/Activo/" + asignar,

        error: function () { //si existe un error en la respuesta del ajax
            showAlert("Rechazada", "Hubo un problema al asignar la categoria");
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data === "bien") {
                $("#modalFuncionario").modal("hide");
                showAlert("Asignada", "Se asigno corretamente la categoria");
                $(".check").removeClass("Asignada");
                findAll(1);
                 $("#idFuncionario").val("");
                 $("#divMensaje").hide();
            }
        },
        type: 'PUT',
        dataType: "text"
    });
    }
    else{
       $("#divMensaje").show();
    }
}

function cancel() {
    $("#idFuncionario").val("");
    $("#divMensaje").hide();
     $(".check").removeClass("Asignada");
     $("#modalCategoria").modal("hide");
    
}

function findAllFuncionario(numPage) {
    var findAllFuncionario = "findAllFuncionario";
    $.ajax({

        url: "api/Activo/" + findAllFuncionario
        ,
        error: function () { //si existe un error en la respuesta del ajax
            //mostrarModal("mensajeAlert", "Error al cargar en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarPanel(numPage, data);
            paginadorModal(numPage, data.length / 6);
        },
        type: 'POST',
        dataType: "json"
    });
}