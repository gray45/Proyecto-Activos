/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.SolicitudDao;
import Utils.Utils;
import activos.logic.Solicitud;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grave
 */
@WebServlet(name = "SecretariaController", urlPatterns = {"/SecretariaController"})
public class SecretariaController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                String json;
                Utils utils = new Utils();
                HttpSession s = request.getSession(true);
                String accion = request.getParameter("accion");
                switch (accion) {
                    case "changeState":
                        String idSolicitud = request.getParameter("idSolicitud");
                        String state = request.getParameter("state");

                        SolicitudDao dao = new SolicitudDao();
                        Solicitud solicitud = dao.findByID(Integer.parseInt(idSolicitud));
                        solicitud.setEstado(state);
                        dao.merge(solicitud);
                        String estado = "bien";
                        json = new Gson().toJson(utils.findAll());

                        // response.setContentType("application/json; charset=UTF-8");
                        out.print(json);
                        // response.setStatus(200);
                        break;
                    case "findAll":
                        List<Solicitud> all = utils.findAll();
                        json = new Gson().toJson(all);

                        // response.setContentType("application/json; charset=UTF-8");
                        out.print(json);
                        break;

                    case "buscar":

                        String quest = request.getParameter("quest");
                        json = new Gson().toJson(findByQuest(quest));

                        // response.setContentType("application/json; charset=UTF-8");
                        out.print(json);
                        break;

                    default:
                        out.print("E~No se indico la acción que se desea realizar");
                        break;
                }
            } catch (Exception e) {
                out.print("E~" + e.getMessage());
            }
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
