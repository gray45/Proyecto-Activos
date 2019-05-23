<%-- 
    Document   : NuevaSolicitud
    Created on : 26/03/2019, 10:07:09 PM
    Author     : grave
--%>

<%@page import="activos.logic.Bien"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Nueva Solicitud</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@ include file="../../Head.jsp" %>
        <script src="js/nuevaSolicitud.js" type="text/javascript"></script>
    </head>
    <body >
        <%@include file="../../Header.jsp" %>

        <div class="container-fluid">
            <h3>SOLICITUD</h3>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-6">
                            <form method="POST" name="form" action="Controller/SolicitudController">
                                <div class="form-group" id="divComprobante">
                                    <label class="control-label">
                                        Comprobante
                                    </label>
                                    <div class="input-group">
                                        <% String numComprobante = (String) request.getAttribute("numComprobante");%>
                                        <% if (numComprobante != null) {%>
                                        <span class="input-group-addon"><i class="fas fa-hashtag" aria-hidden="true"></i></span>
                                        <input type="text" name="campoComprobante" id="campoComprobante" class="form-control" value="<%=numComprobante.toString()%>" />
                                        <% } else { %>
                                        <span class="input-group-addon"><i class="fas fa-hashtag" aria-hidden="true"></i></span>
                                        <input type="text" name="campoComprobante" id="campoComprobante" class="form-control" placeholder="Numero Comprobante"/>
                                        <% } %>
                                    </div>
                                </div>
                        </div>
                        <div class="col-md-4">
                            <div class="escondida" id="divComprobanteRequired">
                                <br>
                                <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                                <h5 style="display: inline">Campo Requerido</h5>
                            </div>
                           
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group" id="divTipo">
                                <label class="control-label">
                                    Tipo
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i id="iconTipo" class="fas fa-money-bill-wave-alt" aria-hidden="true"></i></span>
                                    <select class="form-control" name="tipo" id="tipo" onchange="changeType()">
                                        <option name="tipo" value="--" selected >--</option>
                                        <option name="tipo" id="compra" value="compra">Compra</option>
                                        <option name="tipo" id="donacion" value="donacion">Donacion</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="escondida" id="divTipoRequired">
                                <br>
                                <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                                <h5 style="display: inline">Campo Requerido</h5>
                            </div>
                        </div>
                    </div>
                    <br>
                    <input type="submit" onclick="agregar('Agregar Solicitud')" class="btn btn-lg btn-success" value="Agregar Solicitud">


                </div>

            </div>


            <br>

            <div class="row">

                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-10">


                        <h4 >Agregar Bien</h4>
                        <div class="escondida" id="divErrores">
                            <i  style="display: inline" class="fas fa-exclamation-triangle btn-lg Rechazada prefix "></i>
                            <h4 style="display: inline">Rellene los campos marcados con rojo</h4>
                            
                        </div>
                        <br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-2"> 

                        <div class="black curva">
                            <center>
                                <label class="control-label">
                                    Descripcion
                                </label>
                            </center>
                        </div>
                        <div class="form-group" id="divDescripcion">
                            <div class="input-group">
                                <% String descripcion = (String) request.getAttribute("descripcion");%>
                                <% if (descripcion != null) {%>
                                <span class="input-group-addon"><i class="fas fa-comment-dots" aria-hidden="true"></i></span>
                                <input type="text" name="Descripcion" id="descripcion" value="<%=descripcion.toString()%>" class="form-control"/>
                                <% } else { %>
                                <span class="input-group-addon"><i class="fas fa-comment-dots" aria-hidden="true"></i></span>
                                <input type="text" name="Descripcion" id="descripcion" class="form-control"/>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">

                        <div class="black curva">
                            <center>
                                <label class="control-label">
                                    Modelo
                                </label>
                            </center>
                        </div>
                        <div class="form-group" id="divModelo">
                            <div class="input-group">
                                <% String modelo = (String) request.getAttribute("modelo");%>
                                <% if (modelo != null) {%>
                                <span class="input-group-addon"><i class="fas fa-globe" aria-hidden="true"></i></span>
                                <input type="text" name="Modelo" id="modelo" value="<%=modelo.toString()%>" class="form-control" />
                                <% } else { %>
                                <span class="input-group-addon"><i class="fas fa-globe" aria-hidden="true"></i></span>
                                <input type="text" name="Modelo" id="modelo"  class="form-control" />
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="black curva">
                            <center>
                                <label class="control-label">
                                    Marca
                                </label>
                            </center>
                        </div>
                        <div class="form-group" id="divMarca">
                            <div class="input-group">
                                <% String marca = (String) request.getAttribute("marca");%>
                                <% if (marca != null) {%>
                                <span class="input-group-addon"><i class="fas fa-tag" aria-hidden="true"></i></span>
                                <input type="text" name="Marca" id="marca" value="<%=marca.toString()%>" class="form-control" />
                                <% } else { %>
                                <span class="input-group-addon"><i class="fas fa-tag" aria-hidden="true"></i></span>
                                <input type="text" name="Marca" id="marca" class="form-control" />
                                <% }%>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">

                        <div class="black curva">
                            <center>
                                <label class="control-label">
                                    Precio
                                </label>
                            </center>
                        </div>
                        <div class="form-group" id="divPrecio">
                            <div class="input-group">
                                <% String precio = (String) request.getAttribute("precio");%>
                                <% if (precio != null) {%>
                                <span class="input-group-addon"><i class="fas fa-dollar-sign" aria-hidden="true"></i></span>
                                <input type="text" name="Precio" id="precio" value="<%=precio.toString()%>" class="form-control"/>
                                <% } else { %>
                                <span class="input-group-addon"><i class="fas fa-dollar-sign" aria-hidden="true"></i></span>
                                <input type="text" name="Precio" id="precio" class="form-control"/>
                                <% }%>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">

                        <div class="black curva">
                            <center>
                                <label class="control-label">
                                    Cantidad
                                </label>
                            </center>
                        </div>
                        <div class="form-group" id="divCantidad">
                            <div class="input-group">
                                <% String cantidad = (String) request.getAttribute("cantidad");%>
                                <% if (cantidad != null) {%>
                                <span class="input-group-addon "><i class="fab fa-neos" aria-hidden="true"></i></span>
                                <input type="text" name="Cantidad" id="cantidad" value="<%=cantidad.toString()%>" class="form-control curva" />
                                <% } else { %>
                                <span class="input-group-addon "><i class="fab fa-neos" aria-hidden="true"></i></span>
                                <input type="text" name="Cantidad" id="cantidad" class="form-control curva" />
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <center>
                    <input type="submit" onclick="agregar('Agregar Bien')" value="Agregar Bien" class="btn btn-lg btn-success">
                    <br>
                </center>
                
                <input type="text"  id="action"  name="action"class="escondida"/>

                </form>
                <br>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">

                    <% List<Bien> model = (List<Bien>) request.getSession().getAttribute("modeloBienes");%>

                    <table class="table table-hover table-striped"> 
                        <h4>Bienes de la solicitud</h4>
                        <tr>
                            <th>Descripcion</th><th>Marca</th><th>Precio Unitario</th><th>Cantidad</th>
                        </tr>

                        <% if (model != null) {
                                for (Bien bien : model) {%>
                        <tr>
                            <td><%=bien.getDescripcion()%></td>
                            <td><%=bien.getMarca()%></td>

                            <td><%=bien.getPrecio()%></td>
                            <td><%=bien.getCantidad()%></td>
                        </tr>   
                        <%}%>
                        <%}%>
                    </table>
                </div>
                <div class="col-md-1"></div>
            </div>

            <div class="modal fade" id="alerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                                <i class="fas fa-exclamation-triangle btn-lg Rechazada prefix "></i>
                                <h3 style="display: inline">Debe agregar bienes primero</h3>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">

                        </div>
                    </div>
                </div>
            </div>
                    
                    <div class="modal fade" id="alerModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                                <i class="fas fa-exclamation-triangle btn-lg Rechazada prefix "></i>
                                <h3 style="display: inline">Ya existe una solicitud con ese numero</h3>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">

                        </div>
                    </div>
                </div>
            </div>

            <% String errrors = (String) request.getAttribute("errors");%>
            <% if (errrors != null) {%>
            <input type="text" id="error" class="escondida" value="<%=errrors.toString()%>"/>
            <% } else { %>
            <input type="text"  id="error" class="escondida" value=""/>
            <% }%>


            <% String tipo = (String) request.getAttribute("tipo");%>
            <% if (tipo != null) {%>
            <input type="text" id="tipoTxt" class="escondida" value="<%=tipo.toString()%>"/>
            <% } else { %>
            <input type="text"  id="tipoTxt"  class="escondida" value=""/>
            <% }%>

            <% String errroresBien = (String) request.getAttribute("erroresBien");%>
            <% if (errroresBien != null) {%>
            <input type="text" id="erroresBien" class="escondida"  value="<%=errroresBien.toString()%>"/>
            <% } else { %>
            <input type="text"  id="erroresBien" class="escondida" value=""/>
            <% }%>
            
            
        </div>
    </body>
</html>