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
                <div class="col-md-4">
                    <form method="POST" name="formulario" action="Controller/SolicitudController">
                        <div class="form-group" >
                            <label class="control-label">
                                Comprobante
                            </label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-hashtag" aria-hidden="true"></i></span>
                                <input type="text" name="campoComprobante" id="userName" class="form-control" placeholder="Numero Comprobante"/>
                            </div>
                        </div>
                        <div class="form-group" id="divUserName">
                            <label class="control-label">
                                Tipo
                            </label>
                            <div class="input-group">
                                <span class="input-group-addon"><i id="iconTipo" class="fas fa-money-bill-wave-alt" aria-hidden="true"></i></span>
                                <select class="form-control" name="tipo" id="tipo" onchange="changeType()">
                                    <option name="tipo" value="--" selected >--</option>
                                    <option name="tipo" value="compra">Compra</option>
                                    <option name="tipo" value="donacion">Donacion</option>
                                </select>
                            </div>
                            <br>
                            <input type="submit" name="action" class="btn btn-lg btn-success" value="Agregar Solicitud">
                            </form>

                        </div>
                </div>
                <div class="col-md-4"></div>
            </div>


            <br>

            <div class="row">
                <form method="POST" name="formulario" action="Controller/SolicitudController">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                        <div class="escondida" id="divIsEmpty">
                            <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                            <h5 style="display: inline"><b>Debe agregar bienes primero</b></h5>
                            <br>
                        </div>
                           
                        <h4 >Agregar Bien</h4>
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
                                    <span class="input-group-addon"><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <input type="text" name="Descripcion" id="descripcion" class="form-control"/>
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
                                    <span class="input-group-addon"><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <input type="text" name="Modelo" id="modelo" class="form-control" />
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
                                    <span class="input-group-addon"><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <input type="text" name="Marca" id="marca" class="form-control" />
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
                                    <span class="input-group-addon"><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <input type="text" name="Precio" id="precio" class="form-control"/>
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
                                    <span class="input-group-addon "><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <input type="text" name="Cantidad" id="cantidad" class="form-control curva" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>
                    <center>
                        <input type="submit" value="Agregar Bien" class="btn btn-lg btn-success" name="action">
                        <br>
                    </center>

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
                            <th>Descripcion</th><th>Marca</th><th>Modelo</th><th>Precio Unitario</th><th>Cantidad</th>
                        </tr>

                        <% if (model != null) {
                            for (Bien bien : model) {%>
                        <tr>
                            <td><%=bien.getDescripcion()%></td>
                            <td><%=bien.getMarca()%></td>
                            <td>modelo</td>                    
                            <td><%=bien.getPrecio()%></td>
                            <td><%=bien.getCantidad()%></td>
                        </tr>   
                        <%}%>
                        <%}%>
                    </table>
                </div>
                <div class="col-md-1"></div>
            </div>

            <% String errrors = (String) request.getAttribute("errors");%>
            <% if (errrors != null) {%>
            <input type="text" id="error"  value="<%=errrors.toString()%>"/>
            <% } else { %>
            <input type="text"  id="error" value=""/>
            <% }%>      
        </div>
    </body>
</html>