<%-- 
    Document   : activo
    Created on : 02/06/2019, 04:52:06 PM
    Author     : grave
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include  file ="../../Head.jsp" %>
        <script src="js/activo.js" type="text/javascript"></script>
        <title>Activos</title>
    </head>
    <body>
        <%@include  file ="../../Header.jsp" %>
        <div  class="container-fluid">
            <h1 id="blue" >Activos</h1>
            <div class="row">
                <div class="col-md-1 col-sm-1"></div>
                <div class="col-md-10 col-sm-10 ">

                        <div class="row ">
                            <div class="col-md-3"></div>
                            <div class="col-md-5 form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fas fa-search" aria-hidden="true"></i></span>
                                    <input type="text" name="quest" id="quest" class="form-control" placeholder="Varios Criterios" oninput="findQuest(1)"/>
                                </div>

                            </div>
                            <div class="col-md-2 form-group"> 

                            </div>

                        </div>
                    <div id="divConsejo" >
                            <i class="fas fa-exclamation-triangle  btn-lg Verificar prefix"></i>
                            <h4 style="display: inline">Puede buscar por varios criterios </h4>
                        </div>

                    <br>
                    <div class="card-body">
                        
                        <table class="table table-hover table-striped table-responsive">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Descripcion</th>
                                    <th>Categoria</th>
                                    <th>Funcionario</th>
                                    <th>Aciones</th>
                                </tr>
                            </thead>
                            <tbody id="tbody"></tbody>                        
                        </table>
                    </div>
                    <div class="card-footer">
                        <li class="pagination pagination-sm"  align-content="center" id="paginacionOpc"></ul>
                    </div>
                </div>
                <div class="col-md-1 col-sm-1"></div>
            </div>
            <input class="escondida" id="idFuncionario"/>
            <input class="escondida" id="codigo"/>

           <div class="modal fade"  id="modalFuncionarios" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <b> Asignar Funcionario </b>
                        <button type="button" class="close" onclick="cancel()" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">
                        <div class="md-form mb-8" id="panel">

                        </div>

                    </div>
                    <div class="modal-body mx-3">
                        <div class="card-footer">
                            <li class="pagination pagination-sm"  id="paginacionOpcModal"></ul>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <div class="left escondida" id="divMensaje">
                            <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                            <h5 style="display: inline">Selecione un funcionario primero</h5>
                        </div>
                        <button type='button' class='btn btn-lg btn-success' aria-label='rigth Align' onclick='asignarFuncionario()'>
                            <i class='fas fa-arrow-circle-right ' ></i> &nbsp;&nbsp Asignar</button>
                    </div>
                </div>
            </div>
        </div>


        </div>
    </body>

</html>
