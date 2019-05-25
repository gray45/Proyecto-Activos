<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="activos.logic.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@ include file= "../../../Head.jsp"%>
        <script src="js/Usuario.js" type="text/javascript"></script>
    </head>

    <body>
        <%@ include file="../../../Header.jsp" %>
 <% String userName = (String) request.getAttribute("userName");%>
 <% String password = (String) request.getAttribute("password");%>
    <center>
        <div class="card">
            <div class="card-body">


                <form action="Controller/LoginController"  >
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <div class="form-group" id="divUserName">
                                <label class="control-label">
                                    UserName
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fas fa-user" aria-hidden="true"></i></span>
                                     <% if (userName != null) {%>
                                    <input type="text" name="userName" id="userName" class="form-control" value="<%= userName.toString()%>"/>
                                <%  }
                                else{ %>
                                 <input type="text" name="userName" id="userName" class="form-control" placeholder="UserName"/>
                                <% }%>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="left escondida" id="divUserNameRequired">
                                <br>
                                <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                                <h5 style="display: inline">Campo Requerido</h5>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <div class="form-group" id="divPassword">
                                <label class="control-label">
                                    Password
                                </label>
                                <div class="input-group">
                                    
                                    <span class="input-group-addon"><i class="fas fa-key" aria-hidden="true"></i></span>
                                    <% if (password != null) {%>
                                    <input type="password" name="password" id="password" class="form-control" value="<%= password.toString()%>"/>
                                <%  }
                                else{ %>
                                 <input type="password" name="password" id="password" class="form-control" placeholder="contraseña"/>
                                <% }%>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="left escondida" id="divPasswordRequired">
                                <br>
                                <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                                <h5 style="display: inline">Campo Requerido</h5>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">

                        </div>
                        <div class="col-md-4">
                            <div class="left escondida" id="divUsuarioInvalid">
                                <i class= "fas fa-exclamation-triangle btn-lg Rechazada prefix"></i>
                                <h5 style="display: inline">UserName o contraseña invalidos</h5>
                            </div>
                            <input class="btn btn-lg btn-info" type="submit" name="action" value="login" >
                        </div>
                        <div class="col-md-4">

                        </div>
                    </div>
                    <% String errrors = (String) request.getAttribute("errors");%>
                    <% if (errrors != null) {%>
                    <input type="text" id="error"  class="escondida" value="<%=errrors.toString()%>"/>
                    <% } else { %>
                    <input type="text" class="escondida" id="error" value=""/>
                    <% }%>
                </form>   
            </div>
        </div>
    </center>
                
</body>
</html>
