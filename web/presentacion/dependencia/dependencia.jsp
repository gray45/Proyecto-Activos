<%-- 
    Document   : dependencia
    Created on : 22/03/2019, 08:02:24 PM
    Author     : grave
--%>

<%@page import="activos.logic.Dependencia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../../Head.jsp" %>
        <title>Dependencias</title>
    </head>
    <body>
        <%@ include file="../../Header.jsp" %>
        <div  class="container-fluid">
            <h1 id="blue" >Dependencias</h1>
            <div class="row">
                <div class="col-md-4 col-sm-2"></div>
                <div class="col-md-4 col-sm-10">
            <form action="Controller/DependenciaController">
                <h3>Nueva Dependencia</h3>
                <label>
                    Descripcion
                </label>
                <input type="text" name="descripcion" class="form-control" />
                <br>
                <input type="text" name="action" value="agregar" style= "display: none;"/>
                <input type="submit" value="Agregar" class="btn btn-lg btn-success"/>
            </form>
                    <br>
            <% List<Dependencia> model = (List<Dependencia>) request.getAttribute("dependencias");%>
            <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th><th>Dependencia</th>
                </tr>
                <% if(model != null){
                    for (Dependencia dependencia : model) {%>
                <tr>
                    <td><%=dependencia.getId()%></td>
                    <td><%=dependencia.getDescripcion()%></td>
                </tr>
                <% }
}%>

            </table>
                </div>
                <div class="col-md-4 col-sm-2"></div>
</div>
        </div>

    </body>
</html>
