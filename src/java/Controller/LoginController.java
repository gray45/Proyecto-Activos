/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.FuncionarioDao;
import Dao.UsuarioDao;
import activos.logic.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Escinf
 */
@WebServlet(name = "presentation.usuarios.login", 
        urlPatterns = 
                {
//                    "/presentacion/usuarios/login/prepareLogin",
//                    "/presentacion/usuarios/login/login",
//                    "/presentacion/usuarios/login/logout"
                      "/Controller/LoginController"
                })

public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException {
        String action  = request.getParameter("action");
                
            if (action.equals("prepareLogin"))
                this.prepareLogin(request, response);        
            if (action.equals("login"))
                this.login(request, response);
            if (action.equals("logout"))
                this.logout(request, response);            
    }

    protected void prepareLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Usuario model = new Usuario();
        request.setAttribute("model", model);
        request.getRequestDispatcher("/presentacion/usuario/login/View.jsp").forward( request, response); 
    } 

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        if(this.verificar(request)){
            Map<String,String> errors =  this.validar(request);
            if(errors.isEmpty()){
                Usuario model = new Usuario();
                updateModel(model,request);
                request.setAttribute("model", model);
                Usuario logged=null;
                try {
                    String userName = request.getParameter("userName");
                    String password = request.getParameter("password");
                    logged= getusuarioForLogin(userName, password);
                    request.getSession(true).setAttribute("logged", logged);
                    request.getRequestDispatcher("/index.jsp").forward( request, response); 
                } catch (Exception ex) {
                    request.getRequestDispatcher("/presentacion/usuarios/login/View.jsp").forward(request, response);
                }                  
            }
            else{
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/presentacion/usuarios/login/View.jsp").forward(request, response);
            }            
        }
        else{
            request.getRequestDispatcher("/presentacion/Error.jsp").forward(request, response);            
        }
    }
        
    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            session.removeAttribute("logged");
            session.invalidate();
           prepareLogin(request, response); 
    }           

    boolean verificar(HttpServletRequest request){
       if (request.getParameter("userName")==null) return false;
       if (request.getParameter("password")==null) return false; 
       return true;
    }
    
    Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("userName").isEmpty()){
            errores.put("userName","userName requerido");
        }

        if (request.getParameter("password").isEmpty()){
            errores.put("password","password requerida");
        }
        return errores;
    }
        
    void updateModel(Usuario model, HttpServletRequest request){
        model.setNombre(request.getParameter("userName"));
        model.setPassword(request.getParameter("password"));
    }
    
    private Usuario getusuarioForLogin(String userName, String password){
        UsuarioDao dao = new UsuarioDao();
        String query = "FROM Usuario WHERE nombre = '" + userName 
                + "'  AND password = '" + password + "'";
   return (Usuario) dao.findByQuery(query).get(0);
    }
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
