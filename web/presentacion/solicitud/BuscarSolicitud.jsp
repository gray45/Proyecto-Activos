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
        <title>Principal</title>
        <script>

            document.addEventListener("DOMContentLoaded",paginaCargada);

            function paginaCargada() {
                document.querySelectorAll(".ColRowEstado").addActionListener();
            }
        </script>
    </head>
    <body>
        <%@include  file ="../../Header.jsp" %>
        <% List<Solicitud> solicitudes = (List<Solicitud>) request.getAttribute("solicitudes");%>
        <div  class="container-fluid">
            <h1 id="blue" >Solicitudes</h1>
            <div class="row">
                <div class="col-md-1 col-sm-1"></div>
                <div class="col-md-10 col-sm-10 ">
                    <form action="Controller/SolicitudController">

                        <div class="row ">
                            <div class="col-md-3"></div>
                            <div class="col-md-5 form-group">
                                <label>
                                    # Comprobante :
                                </label>
                                <input type="text" name="quest"  class="form-control"/>
                                <input type="text" name="actionHide"  value="find" class="escondida"/>
                            </div>
                            <div class="col-md-2 form-group"> 
                                <input type="submit" value="Buscar" name="action" class="btn btn-lg btn-success"/>
                            </div>

                        </div>

                    </form>
                    <br>
                    <table class="table table-hover table-striped">
                        <tr>
                            <th>Comprobante</th>
                            <th>Dependencia</th>
                            <th>Fecha</th>
                            <th>Tipo</th>
                            <th>Estado</th>
                            <td>Detalle</td>
                        </tr>
                        <% if (solicitudes != null) {
                                for (Solicitud solicitud : solicitudes) {%>
                        <tr>
                            <td><%=solicitud.getComprobante()%></td>
                            <td><%=solicitud.getDependecia()%></td>
                            <td><%=solicitud.getFecha()%></td>
                            <td><%=solicitud.getTipo()%></td>

                            <!--Opcion de Modificar para el Secretario-->
                            <% if (logged.getRol().equals("Secretaria")) { %>
                            <td class="ColRowEstado">
                                <button class="boton" >Aceptar </button>
                                <button class="boton" >Rechazar</button>
                            </td>    
                            <%}%>


                            <% if (logged.getRol().equals("Administrador")) {%>
                            <td><%=solicitud.getEstado()%></td>
                            <%}%>

                            <td><a class="btn btn-primary btn-sm" href="Controller/SolicitudController?action=detalle&&id=<%=solicitud.getIdSolicitud()%>" >Detalle</a></td>


                        </tr>


                        <% }
                            }%>

                    </table>
                </div>
                <div class="col-md-1 col-sm-1"></div>
            </div>
        </div>
    </body>

</html>
