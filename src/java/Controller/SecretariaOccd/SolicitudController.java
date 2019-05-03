/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SecretariaOccd;

import Dao.SolicitudDao;
import activos.logic.Solicitud;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anthony
 */
@WebServlet(name = "SolicitudController", urlPatterns = {"/Controller/SecretariaOccd/SolicitudController"})
public class SolicitudController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = request.getParameter("action");

        if (action.equals("Buscar")) {
            this.buscarSolicitud(request, response);
        }
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

    private void buscarSolicitud(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
        try {
            String actionHide = request.getParameter("actionHide");
            if (actionHide != null) {
                if (actionHide.equals("find")) {
                    String quest = request.getParameter("quest");
                    request.setAttribute("solicitudes", findByQuest(quest));
                }
            }
            else{
            request.setAttribute("solicitudes", findAll());
            }
        } catch (Exception ex) {
        }
        request.getRequestDispatcher("/presentacion/solicitud/BuscarSolicitud.jsp").forward(request, response);
    }
    
    
     private List<Solicitud> findAll() {
        List<Solicitud> solicitudes = null;
        SolicitudDao dao = new SolicitudDao();
        try {
            solicitudes = dao.findAll();
            return solicitudes;
        } catch (Exception ex) {
        }
        return null;
    }

    private List<Solicitud> findByQuest(String quest) {
        List<Solicitud> solicitudes = null;
        SolicitudDao dao = new SolicitudDao();
        try {
            String query = "FROM Solicitud\n"
                    + "WHERE comprobante LIKE '" + "%" + quest + "%'";
            solicitudes = dao.findByQuery(query);
            return solicitudes;
        } catch (Exception ex) {
        }
        return null;
    }
    

}
