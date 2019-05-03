<%-- 
    Document   : AgregarUsuario
    Created on : 29/03/2019, 05:34:47 PM
    Author     : grave
--%>

<%@page import="activos.logic.Funcionario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../../Head.jsp" %>
        <title>Agregar Usuario</title>
    </head>
    <body>
        <%@ include file="../../Header.jsp" %>
        <% List<Funcionario> funcionarios = (List<Funcionario>) request.getAttribute("funcionarios");%>
        <div  class="container-fluid">
            <h1 id="blue" >Registrarse</h1>
            <div class="row">
                <div class="col-md-3 col-sm-1"></div>
                <div class="col-md-8 col-sm-10">
                    <form action="Controller/UsuarioController">
                       
                        <div class="row">
                            <div class="col-md-4 form-group">
                                <label>
                                    UserName
                                </label>
                                <input type="text" name="UserName" class="form-control"/>
                            </div>
                            <div class="col-md-4 form-group">
                                <label>
                                    Password
                                </label>
                                <input type="password" name="password" class="form-control"/>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4 form-group"> 
                                <label>
                                    Funcionarios
                                </label>
                                <% if (funcionarios != null) {%>
                                <select name="funcionario" class="form-control">
                                    <% for (Funcionario funcionario : funcionarios) {
                                            %>
                                            <option value="<%= funcionario.getIdFuncionario() %>" ><%= funcionario.getCedula()%> -- <%= funcionario.getNombre()%></option>
                                    <% } %>
                                </select>
                                <% } else { %>
                                <input type="text" name="action" placeholder="No hay Dependencias disponibles" class="form-control"/>
                                <% }%>
                            </div>
                            <div class="col-md-4 form-group"> 
                                <label>
                                    Rol
                                </label>
                                <select name="rol" class="form-control"><option value="Administrador" >Admistrador</option>
                                    <option value="Secretaria" >Secretaria</option>
                                    <option value="Jefe" >Jefe</option>
                                    <option value="Registrador" >Registrador</option>
                                </select>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="action" value="agregar" style= "display: none;"/>
                            </div>
                            <div class="col-md-2">
                                <input type="submit" value="Agregar" class="btn btn-lg btn-success"/>
                            </div>
                        </div>

                    </form>
                    <br>

                </div>
                <div class="col-md-1 col-sm-1"></div>
            </div>
        </div>

    </body>
</html>
