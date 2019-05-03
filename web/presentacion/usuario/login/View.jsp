<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="activos.logic.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@ include file= "../../../Head.jsp"%>
    </head>

    <body>
        <%@ include file="../../../Header.jsp" %>

    <center>
        <div class="card">
            <div class="card-body">


                <form action="Controller/LoginController"  >
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="userName"> UserName</label>
                                <input  class="form-control" type="text" name="userName" placeholder="UserName"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input class="form-control" type="password" name="password" placeholder="contraseña"/>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">
                            <input class="btn-info" type="submit" name="action" value="login" >
                        </div>
                    </div>
                </form>   
            </div>
        </div>
    </center>



</body>
</html>
<%--<%!
    private String validity(String field, Map<String,String> errors){
      if ( (errors!=null) && (errors.get(field)!=null) )
        return "is-invalid";
      else
        return "";
    }

    private String value(String field, Map<String,String[]> values){
        return values.get(field)[0];
    }

    private Map<String,String[]> getValues(Usuario model){
       Map<String,String[]> values = new HashMap<>();
       values.put("id", new String[]{""+model.getId()});
       values.put("clave", new String[]{model.getPassword()});
       return values;
    } 

   %>--%>