<%-- 
    Document   : Detalle
    Created on : 31/03/2019, 11:45:02 PM
    Author     : grave
--%>

<%@page import="activos.logic.Bien"%>
<%@page import="java.util.List"%>
<%@page import="activos.logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include  file="../../Head.jsp" %>
        <script src="js/ajax.js"></script> 
        <title>Detalle Solicitud</title>
        <script src="js/Detalle.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include  file="../../Header.jsp" %>
        <% Solicitud solicitud = (Solicitud) request.getAttribute("solicitud");%>
        <% List<Bien> bienes = (List<Bien>) request.getAttribute("bienes");%>
        <h1>Detalle</h1>
        <div  class="container-fluid">
            <input id="idSolicitud" type="text" class="escondida" value="<%=solicitud. getIdSolicitud()%>" >

            <% if (solicitud != null) {%>

            <div class="row">  
                <div class="col-md-2 col-lg-1"></div>
                <div class="col-md-8 col-lg-10">


                    <div class="row">
                        <div class="col-md-5">
                            <i class= "fas fa-hashtag Verificar btn-lg prefix"></i>
                            <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getComprobante()%></h3></span>
                        </div>
                        <div class="col-md-4">
                            <i class="fas fa-calendar-alt Verificar btn-lg prefix"></i>
                            <span class="subrayada"><h3 style="display: inline" > <%= solicitud.getFecha()%></h3></span>
                        </div>
                        <div class="col-md-3">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <div>
                                <i class="fas fa-building Verificar btn-lg prefix"></i>
                                <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getDependecia()%></h3></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div>
                                <i class="fas fa-money-bill-wave-alt Verificar btn-lg prefix"></i>
                                <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getTipo()%></h3></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div>
                                <i class="fas fa-check-circle <%= solicitud.getEstado()%> btn-lg prefix"></i>
                                <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getEstado()%></h3></span>
                             </div>
                        </div>
                        <div class="col-md-4">
                            <div>
                                <i class="fas fa-user Verificar btn-lg prefix"></i>
                                <% if (!solicitud.getRegistrador().isEmpty()) {%>
                                <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getRegistrador()%></h3></span>
                                <% } else {%>
                                <span class="subrayada"><h3 style="display: inline"> Sin Registrador</h3></span>
                                <% } %>
                            </div>
                        </div>
                        <div class="col-md-3">
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-md-8">
                            <div>

                                <%if (solicitud.getRasonRechazo() != null) {
                                    if (!solicitud.getRasonRechazo().isEmpty()) {%>
                                <i class="fas fa-exclamation-circle Rechazada btn-lg prefix"></i>
                                <span class="subrayada"><h3 style="display: inline"> <%= solicitud.getRasonRechazo()%></h3></span>
                                <% } } %>
                                
                            </div>
                        </div>
                        <div class="col-md-4">
                            <% if (logged.getRol().equals("Jefe")) {%>  
                            <script src="js/DetalleJefe.js" type="text/javascript"></script>
                            <div id="campoSeleccionRegistrador">
                                <h3>Seleccionar Buscador</h3>
                                <div class="row">
                                    <div class="col-md-2">
                                <button class="btn btn-md right" ><i class="fas fa-search" aria-hidden="true"></i></button>
                                    </div >
                                    <div class="col-md-10">
                                <input type="text" name="quest" id="quest" class="form-control" />
                                    </div>
                                    </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-8"></div>
                            <div class="col-md-4">
                                <button type="button" class="btn btn-primary" onclick="asignarRegistrador()"><i class="fas fa-arrow-circle-right" aria-hidden="true"></i>&nbsp;&nbsp Asignar</button>                          
                            </div>
                        </div>
                            </div>
                            <%}%>
                        </div>
                    </div>

                    <br>
                    <div class="row">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Descripcion</th>
                                    <th>Modelo</th>
                                    <th>Marca</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>Estado</th>
                                    <th>Categoria</th>
                                </tr>
                            </thead>
                            <tbody id="tbody">

                            </tbody>

                        </table>
                        <div class="card-footer">
                            <li class="pagination pagination-sm"  align-content="center" id="paginacionOpc"></ul>
                        </div>
                        <% if (logged.getRol().equals("Registrador")) {%> 
                        <div class="row">
                            <div class="col-md-5 col-lg-1">
                                <button type="button" id="btnProcesar" class="btn btn-success" onclick="window.open('Controller/SolicitudController?action=procesar&&id=<%=solicitud.getIdSolicitud()%>')" target="_blank" >Procesar Solicitud</button>  
<!--                                <button type="button" onclick="window.open('lapaginanueva'); window.location.href = 'lapaginanueva'" target="_blank">boton</button>-->
                                
                            </div>
                            <div class="col-md-7 col-lg-1"></div>
                        </div>
                        
                        <%}%>
                    </div>
                </div>
                <div class="col-md-2 col-lg-1"></div>
            </div>
            <% }%>
        </div>


        <div class="modal fade"  id="modalCategoria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <b> Asignar Categoria </b>
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
                            <h5 style="display: inline">Selecione una categoria primero</h5>
                        </div>
                        <button type='button' class='btn btn-lg btn-success' aria-label='rigth Align' onclick='asignarCategoria()'>
                            <i class='fas fa-arrow-circle-right ' ></i> &nbsp;&nbsp Asignar</button>
                    </div>
                </div>
            </div>
        </div>

        <input type="text"  id="idBien" class="escondida"  />
        <input type="text"  id="idCategoria" class="escondida"  />

    </body>
</html>
