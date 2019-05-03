<%-- 
    Document   : funcionario
    Created on : 29/03/2019, 02:33:39 PM
    Author     : grave
--%>

<%@page import="activos.logic.Funcionario"%>
<%@page import="activos.logic.Dependencia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../../Head.jsp" %>
        <title>Funcionario</title>
    </head>
    <body>
        <%@ include file="../../Header.jsp" %>
        <% List<Dependencia> dependencias = (List<Dependencia>) request.getAttribute("dependencias");%>
        <div  class="container-fluid">
            <h1 id="blue" >Funcionarios</h1>
            <div class="row">
                <div class="col-md-2 col-sm-1"></div>
                <div class="col-md-8 col-sm-10">
                    <form action="Controller/FuncionarioController">
                        <h3>Nuevo Funcionario</h3>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-4 form-group">
                                <label>
                                    Cedula
                                </label>
                                <input type="text" name="cedula" class="form-control" />
                            </div>
                            <div class="col-md-4 form-group"> 
                               <label>
                                    Nombre
                                </label>
                                <input type="text" name="nombre" class="form-control" />
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                            <br>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-4 form-group">
                                <label>
                                    Dependencia
                                </label>
                                <% if (dependencias != null) {%>
                                <select name="dependencia" class="form-control" >
                                    <% for (Dependencia dependencia : dependencias) {
                                            String option = dependencia.getId().toString() + "," + dependencia.getDescripcion();
                                    %>
                                    <option value="<%= option%>" ><%= dependencia.getDescripcion()%></option>
                                    <% } %>
                                </select>
                                <% } else { %>
                                <input type="text" name="action" placeholder="No hay Dependencias disponibles" class="form-control" />
                                <% } %>
                                <input type="text" name="action" value="agregar" style= "display: none;"/>
                            </div>
                            <div class="col-md-4 form-group">
                                <input type="submit" value="Agregar" class="btn btn-lg btn-success"/>
                            </div>
                               <div class="col-md-2"></div> 
                        </div>

                    </form>
                    <br>
                    <% List<Funcionario> model = (List<Funcionario>) request.getAttribute("funcionarios");%>
                    <table class="table table-hover table-striped">
                        <tr>
                            <th>Cedula</th><th>Dependencia</th>
                        </tr>
                        <% if (model != null) {
                        for (Funcionario funcionario : model) {%>
                        <tr>
                            <td><%=funcionario.getCedula()%></td>
                            <td><%=funcionario.getDependencia_1()%></td>
                        </tr>
                        <% }
                    }%>

                    </table>
                </div>
                <div class="col-md-2 col-sm-1"></div>
            </div>
        </div>

    </body>
</html>
