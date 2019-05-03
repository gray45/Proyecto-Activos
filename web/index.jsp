<!--<!DOCTYPE html>

To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.

<html>
    <head>
        <title>Registro Telefonico</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    </head>
    
    <body>
         
    
        <label>Para crear una solicitud </label> <a href="/Activos/presentacion/solicitud/create/View.jsp"> click aqui! </a>
        <label>Para crear una dependencia </label> <a href="Controller/DependenciaController"> click aqui! </a>
         <label>Para crear un funcionario </label> <a href="Controller/FuncionarioController"> click aqui! </a>
         <label>Registrarse </label> <a href="Controller/UsuarioController"> click aqui! </a>
    
        <form action="/Controller/DependenciaController">
            <input type="submit" value="dependecia"/>
        </form>
    </body>
</html>
-->
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/estilo.css" type="text/css"> 
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <%@include file="Head.jsp"%> 
        
     </head>
    <body class="inicio">
        <%@include  file="Header.jsp" %>
        <div>
            <div class="contenedor">
                    <div class="titulo">
                        <div>
                            <h1>Bienvenidos al Sistema de Gestion de Activos</h1>
                        </div>  
                        <div id="lista">
                            <div>
                                El sistema de activos brinda los servicios de informacion requeridos para la ejecucion</br>
                                de un conjunto de procesos y actividades realizados por la Oficina Central de Control</br>
                                de Bienes (OCCB) y las Unidades de Apoyo Administrativo ascritas a cada Facultad o</br>
                                Dependencia Central y que permitan:
                            </div>
                            <div>
                            <ul>
                                <li>Realizar el control fisico de los bienes muebles que forman parte de los activos</br>
                                    fijos tangibles de la Universidad
                                </li>
                                <li>Efectuar el registro de todas las adquisiciones de bienes realizados por la</br>
                                    institucion por concepto de compra , donacion o produccion industrial.
                                </li>
                                <li>
                                    Efectuar el contorl dobre el estado, uso, conservacion y custodia de los bienes de</br>
                                    la institucion
                                </li>
                            </ul>
                            </div>
                        </div>
                    </div>
            </div>
            
            <div class="container-fluid">
                <a>Para mas informacion www.una.ac.cr &nbsp; o &nbsp; Tel: 2277 3000</a>
            </div>
            
        </div>
    </body>
</html>
