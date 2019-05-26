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
                <button type="button" onclick="add()" id="boton" class="btn btn-lg btn-success">Agregar</button>
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
    </body>
</html>
