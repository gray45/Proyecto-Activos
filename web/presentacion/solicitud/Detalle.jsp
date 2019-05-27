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
            <input id="idSolicitud" type="text" class="escondida" value="<%=solicitud.getIdSolicitud()%>" >

            <% if (solicitud != null) {%>

            <div class="row">  
                <div class="col-md-2 col-lg-1"></div>
                <div class="col-md-8 col-lg-10">


                    <div class="col-md-4 " >
                        <div class="row">
                            <div class="col-md-8">
                                <h3>Comprobante: <%= solicitud.getComprobante()%></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div >
                                    <h3>Fecha : <%= solicitud.getFecha()%></h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 " ></div>


                    <% if (logged.getRol().equals("Jefe")) {%>  
                    <script src="js/DetalleJefe.js" type="text/javascript"></script>
                    <div class="col-md-4" id="campoSeleccionRegistrador">
                        <h3>Seleccionar Buscador</h3>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="fas fa-search" aria-hidden="true"></i></span>
                            <input type="text" name="quest" id="quest" class="form-control"  oninput="buscar()" placeholder="Numero de Cedula del usuario"/>
                        </div>


                        <div class="row">
                            <div class="col-md-12" id="camposDeSugerencias"  >

                            </div>
                        </div>


                    </div>
                    <%}%>


                    <div class="row">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Descripcion</th>
                                    <th>Marca</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>Funcionario</th>
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
                                <button type="button" class="btn btn-success" onclick="ProcesarBoton()">Procesar</button>                          
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
          
            
        <div class="modal fade" id="modalCategoria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body mx-3">
                            <div class="md-form mb-5" id="panel">
                               
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">

                        </div>
                    </div>
                </div>
            </div>
        
        <input type="text"  id="idBien" class=""  />
    </body>
</html>
