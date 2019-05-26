<%-- 
    Document   : categoria
    Created on : 24/05/2019, 07:02:39 PM
    Author     : grave
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
        <%@ include file= "../../Head.jsp"%>
        <script src="js/categoria.js" type="text/javascript"></script>
    </head>
    <body>
        <%@ include file="../../Header.jsp" %>
        <div  class="container-fluid">
            <h1 id="blue" >Categorias</h1>
            <div class="row">
                <div class="col-md-4 col-sm-2"></div>
                <div class="col-md-4 col-sm-10">
                    <form >
                        <h3 id="h3Categoria" >Nueva Categoria</h3>
                        <div id="divDescripcionCategoria" class="form-group">
                            <label class="control-label">
                                Descripcion
                            </label>
                            <div id="inpuGroup" class="input-group">
                                <span class="input-group-addon"><i class="fas fa-comment-dots" aria-hidden="true"></i></span>
                                <input type="text" name="descripcion" id="descripcion" class="form-control" />

                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-6">
                                <div  id="divAdd">
                                    <button type="button" onclick="add()" class="btn btn-lg btn-success">Agregar</button>
                                </div>
                                <div  id="divEdit" class="escondida">
                                    <button type="button" onclick="edit()"  class="btn btn-lg btn-info">Editar</button>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div  id="divCancel" class="left escondida">
                                    <button type="button" onclick="cancel()" class="btn btn-lg btn-danger">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br>
                    <table class="table table-hover table-striped sombra">
                        <thead>
                            <tr>
                                <th>Id</th><th>Descripcion</th><th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="bodyTabla"></tbody>
                    </table>
                    <div class="card-footer">
                        <li class="pagination pagination-sm"  align-content="center" id="paginacionOpc"></ul>
                    </div>
                </div>
                <div class="col-md-4 col-sm-2"></div>
            </div>
        </div>
        <input type="text" id="idCategoria" class="escondida" />

        <div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">
                        <div class="md-form mb-5">
                            <i class="fas fa-exclamation-triangle Rechazada btn-lg prefix "></i>
                            <h3 id="mensajeDelete" style="display: inline"></h3>
                        </div>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <div>
                            <i class="fas fa-times-circle  btn-lg Rechazada prefix" onclick="borrar()"></i>
                            <h3 style="display: inline">Eliminar</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
