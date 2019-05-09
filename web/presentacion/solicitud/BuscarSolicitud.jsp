<%-- 
    Document   : BuscarSolicitud
    Created on : Mar 30, 2019, 2:10:14 AM
    Author     : Anthony
--%>

<%@page import="activos.logic.Solicitud"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include  file ="../../Head.jsp" %>
        <script src="js/Solicitud.js" type="text/javascript"></script>
        <title>Principal</title>

    </head>
    <body>
        <%@include  file ="../../Header.jsp" %>
        <div  class="container-fluid">
            <h1 id="blue" >Solicitudes</h1>
            <div class="row">
                <div class="col-md-1 col-sm-1"></div>
                <div class="col-md-10 col-sm-10 ">
                    <form action="Controller/SolicitudController">

                        <div class="row ">
                            <div class="col-md-3"></div>
                            <div class="col-md-5 form-group">
                                <label class="control-label">
                                    # Comprobante :
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fas fa-search" aria-hidden="true"></i></span>
                                    <input type="text" name="quest" id="quest" class="form-control" onchange="buscar(1)"/>
                                </div>

                            </div>
                            <div class="col-md-2 form-group"> 

                            </div>

                        </div>

                    </form>
                    <br>
                    <div class="card-body">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>Comprobante</th>
                                    <th>Dependencia</th>
                                    <th>Fecha</th>
                                    <th>Tipo</th>
                                    <th>Estado</th>
                                    <td>Detalle</td>
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


            <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h4 class="modal-title w-100 font-weight-bold">Cambiar Estado</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                                <i class=" fas fa-check-circle  btn-lg Aprobada prefix " onclick="changeState('Aprobada')"></i>
                                <h3 style="display: inline" >Aprobar</h3>
                            </div>

                            <div class="md-form mb-4">
                                <i class="fas fa-check-circle  btn-lg Rechazada prefix" onclick="changeState('Rechazada')"></i>
                                <h3 style="display: inline">Rechazar</h3>
                            </div>

                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>

</html>
