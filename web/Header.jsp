<%-- 
    Document   : Header
    Created on : Mar 18, 2019, 7:49:10 PM
    Author     : Anthony


debe de ir alguna variable en este jsp para que cambie el nombre en el menu o header
--%>

<%@page import="activos.logic.Usuario"%>
<% Usuario logged = (Usuario) session.getAttribute("logged");%>

<nav class="navbar navbar-inverse">

    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="#">Activos</a>
        </div>


        <ul class="nav navbar-nav">


            <!-- NAVEBAR INICIO -->
            <% if (logged == null) { %>
            <li> <a  href="Controller/LoginController?action=prepareLogin">Ingresar</a> </li> 
            <li> <a  href="Controller/UsuarioController">Registrarse</a> </li>   
                <% }%>

            <%  if (logged != null) {  %>

            <input type="text" value="<%= logged.getRol()%> " class="escondida" id="rol"/>

            <!-- items del administrador de dependencia -->
            <% if (logged.getRol().equals("Administrador")) {%>  

            <!--dropdown de la solicitud-->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Solicitud<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>  <a href="Controller/SolicitudController?action=nuevaSolicitud">Nueva Solicitud</a>     </li>
                    <li> <a href="Controller/SolicitudController?action=Buscar">Buscar Solicitud</a> </li>
                </ul>
            </li>

            <!--Dropdown otros -->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Otros<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>  <a href="Controller/FuncionarioController">Funcionarios</a>   </li>
                    <li>  <a href="Controller/DependenciaController">Dependencias</a>   </li>
                </ul>
            </li>

           
            <% } %>
            
            <% if (logged.getRol().equals("Secretaria")) {%>  

            <!--dropdown de la solicitud-->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Solicitud<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>  <a href="Controller/SecretariaOccd/SolicitudController?action=Buscar">Buscar Solicitud</a>     </li>
                 </ul>
            </li>

            <% }%>
            
            
            <% if (logged.getRol().equals("Jefe")) {%>  

            <!--dropdown de la solicitud-->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Solicitud<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>  <a href="Controller/SecretariaOccd/SolicitudController?action=Buscar">Buscar Solicitud</a>     </li>
                 </ul>
            </li>

            <% }%>
               
            <% if (logged.getRol().equals("Registrador")) {%>  

            <!--dropdown de la solicitud-->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Solicitud<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>  <a href="Controller/SecretariaOccd/SolicitudController?action=Buscar">Buscar Solicitud</a>     </li>
                 </ul>
            </li>

            <% }%>
            
            
             <!--Dropdown logout-->
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> <%= logged.getNombre() %> <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="Controller/LoginController?action=logout">Log out</a></li>
                </ul>
            </li>

            <% }%>



        </ul>
           
            
</nav>
















