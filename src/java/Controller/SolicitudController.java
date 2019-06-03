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
import activos.logic.Model;
import activos.logic.Solicitud;
import activos.logic.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

        if (action.equals("Agregar Solicitud")) {
            this.agregarNuevaSolicitud(request, response);
        }

        if (action.equals("detalle")) {
            this.detalle(request, response);
        }
        if (action.equals("procesar")) {
            this.procesarSolicitud(request, response);
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
            } else {
                request.setAttribute("solicitudes", findAll());
            }
        } catch (Exception ex) {
        }
        request.getSession().setAttribute("modeloBienes", null);
        request.getRequestDispatcher("/presentacion/solicitud/BuscarSolicitud.jsp").forward(request, response);
    }

    private void agregarNuevaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String NumComrobante = request.getParameter("campoComprobante");
        String tipo = request.getParameter("tipo");

        if (verificar(request)) {

            if (validar(NumComrobante, tipo).equals("0,0")) {
                SolicitudDao dao = new SolicitudDao();
                String query = "FROM Solicitud WHERE comprobante = '" + NumComrobante + "'";
                List<Solicitud> solicitudes = (List<Solicitud>) dao.findByQuery(query);
                Solicitud solicitudBase = null;
                if (!solicitudes.isEmpty()) {
                    solicitudBase = solicitudes.get(0);
                }
                if (solicitudBase == null) {
                    Boolean validate = haveData(request);
                    if (validate) {
                        try {
                            java.util.Date date = new Date();
                            String fecha = date.getDate() + "/" + (date.getMonth() + 1) + "/" + Integer.toString(date.getYear()).substring(1);
                            Usuario usuario = (Usuario) request.getSession(true).getAttribute("logged");

                            Solicitud unaSolicitud = new Solicitud(usuario.getDependencia(),
                                    "Recibida", NumComrobante, fecha, tipo, usuario.getDependecia(), "");

                            dao.save(unaSolicitud);
                             Thread.sleep(2000);
                            solicitudBase = (Solicitud) dao.findByQuery(query).get(0);
                            registrar(request, response, solicitudBase.getIdSolicitud());

                            buscarSolicitud(request, response);
                        } catch (Exception ex) {
                            request.setAttribute("errors", validar(NumComrobante, tipo));
                            request.setAttribute("numComprobante", NumComrobante);
                            request.setAttribute("tipo", tipo);
                            request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errors", "isEmpty");
                        request.setAttribute("numComprobante", NumComrobante);
                        request.setAttribute("tipo", tipo);
                        request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errors", "repetida");
                    request.setAttribute("numComprobante", NumComrobante);
                    request.setAttribute("tipo", tipo);
                    request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errors", validar(NumComrobante, tipo));
                request.setAttribute("numComprobante", NumComrobante);
                request.setAttribute("tipo", tipo);
                request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);

            }
        } else {
            request.setAttribute("errors", "1,1");
            request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
        }

    }

    private Boolean haveData(HttpServletRequest request) {
        ArrayList<Bien> bienes = new ArrayList<>();
        bienes = (ArrayList<Bien>) request.getSession().getAttribute("modeloBienes");
        if (bienes != null && bienes.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    private void nuevaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);

    }

    private void agregarBien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String NumComrobante = request.getParameter("campoComprobante");
        String tipo = request.getParameter("tipo");
        String descripcion = request.getParameter("Descripcion");
        String cantidad = request.getParameter("Cantidad");
        String marca = request.getParameter("Marca");
        String modelo = request.getParameter("Modelo");
        String precio = request.getParameter("Precio");

        ArrayList<Bien> bienes = (ArrayList<Bien>) request.getSession().getAttribute("modeloBienes");

        if (bienes == null) {
            bienes = new ArrayList<>();
        }

        String errors = validarBien(descripcion, modelo, marca, precio, cantidad);

        if (errors.equals("0,0,0,0,0")) {

            Bien unBien = new Bien();

            unBien.setDescripcion(descripcion);
            unBien.setCantidad(cantidad);
            unBien.setMarca(marca);
            unBien.setPrecio(Integer.parseInt(precio));
            unBien.setModelo(modelo);

            if (bienes != null) {
                bienes.add(unBien);
            } else {
                bienes = new ArrayList<>();
                bienes.add(unBien);
            }

            request.getSession().setAttribute("modeloBienes", bienes);
            request.setAttribute("numComprobante", NumComrobante);
            request.setAttribute("tipo", tipo);
            request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("modeloBienes", bienes);
            request.setAttribute("erroresBien", errors);
            request.setAttribute("numComprobante", NumComrobante);
            request.setAttribute("tipo", tipo);
            request.setAttribute("descripcion", descripcion);
            request.setAttribute("modelo", modelo);
            request.setAttribute("marca", marca);
            request.setAttribute("precio", precio);
            request.setAttribute("cantidad", cantidad);
            request.getRequestDispatcher("/presentacion/solicitud/NuevaSolicitud.jsp").forward(request, response);
        }
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

    private String validar(String numComprobante, String tipo) {
        String errores = "0,0";
        String isEmpty[] = new String[2];
        if (numComprobante.isEmpty()) {
            isEmpty[0] = "1";
        } else {
            isEmpty[0] = "0";
        }

        if (tipo.equals("--") || tipo.isEmpty()) {
            isEmpty[1] = "1";
        } else {
            isEmpty[1] = "0";
        }

        errores = isEmpty[0] + "," + isEmpty[1];

        return errores;
    }

    private boolean verificar(HttpServletRequest request) {
        if (request.getParameter("campoComprobante") == null) {
            return false;
        }
        if (request.getParameter("tipo") == null) {
            return false;
        }
        return true;
    }

    private String validarBien(String descripcion, String modelo, String marca, String precio, String cantidad) {
        String errores = "0,0,0,0,0";
        String isEmpty[] = new String[5];

        if (descripcion.isEmpty()) {
            isEmpty[0] = "1";
        } else {
            isEmpty[0] = "0";
        }

        if (modelo.isEmpty()) {
            isEmpty[1] = "1";
        } else {
            isEmpty[1] = "0";
        }

        if (marca.isEmpty()) {
            isEmpty[2] = "1";
        } else {
            isEmpty[2] = "0";
        }

        if (precio.isEmpty()) {
            isEmpty[3] = "1";
        } else {
            try {
                int parse_numero = Integer.parseInt(precio);
                isEmpty[3] = "0";
            } catch (Exception e) {
                isEmpty[3] = "1";
            }

        }

        if (cantidad.isEmpty()) {
            isEmpty[4] = "1";
        } else {
            try {
                int parse_numero = Integer.parseInt(cantidad);
                isEmpty[4] = "0";
            } catch (Exception e) {
                isEmpty[4] = "1";
            }

        }

        errores = isEmpty[0] + "," + isEmpty[1] + "," + isEmpty[2] + "," + isEmpty[3] + "," + isEmpty[4];

        return errores;
    }

    private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String id = request.getParameter("id");

        SolicitudDao dao = new SolicitudDao();
        Solicitud solicitud = dao.findByID(Integer.parseInt(id));

        List<Bien> bienes  = getBienesBySolicitud(id);
        solicitud.setEstado("Rotulacion");
        dao.merge(solicitud);
        
        request.setAttribute("codigos", Model.generarCodigos(bienes) );
        request.getRequestDispatcher("/presentacion/solicitud/CodigosPDF.jsp").forward(request, response);
    
    }

}
