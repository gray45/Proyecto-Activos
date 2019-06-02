<%-- 
    Document   : CodigosPDF
    Created on : Jun 1, 2019, 8:45:18 PM
    Author     : Anthony
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Codigos PDF</title>
        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="JsBarcode.all.min.js" type="text/javascript"></script>
        <script src="//code.jquery.com/jquery-latest.min.js"></script>
        <script src="JsBarcode.all.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>

        <script type="text/javascript">


            document.addEventListener("DOMContentLoaded", cargada);

            function cargada() {

                var codigos = getCodigos();
                
                for (i = 0; i < codigos.length-1; i++) {
                    elCodigo =codigos[i].toString();
                    JsBarcode("#c"+elCodigo,elCodigo);
                }

                if (window.print) {
                    window.print();
                }
            }

            function getCodigos() {
                var str = $("#codigos").val();
                var codigos = str.split(",");
                return codigos;

            }
        </script>

    </head>
    <body>

        <% List<String> codigos = (List<String>) request.getAttribute("codigos");%>
        <%String losCodigos = "";%>

        <%for (int i = 0; i < codigos.size(); i++) {%>
        <svg id="c<%=codigos.get(i)%>"></svg>     
        <%losCodigos = losCodigos + codigos.get(i) + ",";
            }
        %>
        <svg id="test"></svg>     
        <input type="hidden"  id="codigos" value="<%=losCodigos%>"/>   

    </body>
</html>
