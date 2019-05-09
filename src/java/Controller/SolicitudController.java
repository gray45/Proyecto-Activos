/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.BienDao;
import Dao.SolicitudDao;
import Dao.UsuarioDao;
import activos.logic.Bien;
import activos.logic.Solicitud;
import activos.logic.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "activos.presentacion.solicitudes.create",
        urlPatterns = {"/Controller/SolicitudController"})
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

        if (action.equals("nuevaSolicitud")) {
            this.nuevaSolicitud(request, response);
        }

        if (action.equals("Agregar Bien")) {
            this.agregarBien(request, response);
        }

        if (action.equals("agregarNuevaSolicitud")) {
            this.agregarNuevaSolicitud(request, response);
        }
        
         if (action.equals("detalle")) {
            this.detalle(request, response);
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

    private void agregarNuevaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String NumComrobante = request.getParameter("campoComprobante");
            String fecha = request.getParameter("campoFecha");
            String tipo = request.getParameter("tipo");
            Usuario usuario = (Usuario) request.getSession(true).getAttribute("logged");

            Solicitud unaSolicitud = new Solicitud(usuario.getDependencia(),
                    "Recibida", NumComrobante, fecha, tipo, usuario.getDependecia(), "");
            SolicitudDao dao = new SolicitudDao();
            dao.save(unaSolicitud);

            String query = "FROM Solicitud WHERE comprobante = '" + NumComrobante
                    + "'";
            Solicitud solicitudBase = (Solicitud) dao.findByQuery(query).get(0);
            registrar(request, response, solicitudBase.getIdSolicitud());

            buscarSolicitud(request, response);
        } catch (Exception ex) {
        }
    }

    private void nuevaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);

    }

    private void agregarBien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bien unBien = new Bien();
        ArrayList<Bien> bienes = new ArrayList<>();

        unBien.setDescripcion(request.getParameter("Descripcion"));
        unBien.setCantidad(request.getParameter("Cantidad"));
        unBien.setMarca(request.getParameter("Marca"));
        unBien.setPrecio(Integer.parseInt(request.getParameter("Precio")));

        bienes = (ArrayList<Bien>) request.getSession().getAttribute("modeloBienes");
        if (bienes != null) {
            bienes.add(unBien);
        } else {
            bienes = new ArrayList<>();
            bienes.add(unBien);
        }

        request.getSession().setAttribute("modeloBienes", bienes);
        request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response, Integer idSolicitud) throws ServletException, IOException {
        try {

            ArrayList<Bien> bienes = (ArrayList<Bien>) request.getSession().getAttribute("modeloBienes");
            BienDao dao = new BienDao();
            for (Bien bien : bienes) {
                bien.setEstado("inactivo");
                bien.setSolicitud(idSolicitud);
                dao.save(bien);
            }
        } catch (Exception ex) {
        }

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
    
    
    private void detalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        SolicitudDao dao = new SolicitudDao();
       Solicitud solicitud = dao.findByID(Integer.parseInt(id));
       
        request.setAttribute("solicitud", solicitud);
        request.setAttribute("bienes", getBienesBySolicitud(id));
        request.getRequestDispatcher("/presentacion/solicitud/Detalle.jsp").forward(request, response);
    }
    
    
     private List<Bien> getBienesBySolicitud(String quest) {
        List<Bien> Bienes = null;
        BienDao dao = new BienDao();
        try {
            String query = "FROM Bien\n"
                    + "WHERE solicitud = " + quest;
            Bienes = dao.findByQuery(query);
            return Bienes;
        } catch (Exception ex) {
        }
        return null;
    }

}
