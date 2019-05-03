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
        <title>Detalle Solicitud</title>
    </head>
    <body>
        <%@include  file="../../Header.jsp" %>
        <% Solicitud solicitud = (Solicitud) request.getAttribute("solicitud");%>
        <% List<Bien> bienes = (List<Bien>) request.getAttribute("bienes");%>
        <h1>Detalle</h1>
        <div  class="container-fluid">

            <% if (solicitud != null) {%>

            <div class="row">  
                <div class="col-md-2 col-lg-1"></div>
                <div class="col-md-8 col-lg-10">

                    <div class="row">
                        <div class="col-md-8">
                            <div >
                                <h3>Comprobante : <%= solicitud.getComprobante()%></h3>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-8">
                            <div >
                                <h3>Fecha : <%= solicitud.getFecha()%></h3>
                            </div>
                        </div>
                    </div>
                    <br>

                    <div class="row">

                        <table class="table table-hover table-striped">
                            <tr>
                                <th>Descripcion</th>
                                <th>Marca</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>Funcionario</th>
                                <td>Estado</td>
                            </tr>
                            <% if (bienes != null) {
                                for (Bien bien : bienes) {%>
                            <tr>
                                <td><%=bien.getDescripcion()%></td>
                                <td><%=bien.getMarca()%></td>
                                <td><%=bien.getPrecio()%></td>
                                <td><%=bien.getCantidad()%></td>
                                <td><%=bien.getFuncionario()%></td>
                                <td><%=bien.getEstado()%></td>
                            </tr>
                            <% }
                            }%>

                        </table>
                    </div>
                </div>
                <div class="col-md-2 col-lg-1"></div>
            </div>
            <% }%>
        </div>
    </body>
</html>
