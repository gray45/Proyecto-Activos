/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Dao.DependenciaDao;
import Dao.FuncionarioDao;
import activos.logic.Dependencia;
import activos.logic.Funcionario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grave
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/Controller/FuncionarioController"})
public class FuncionarioController extends HttpServlet {

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

        if ("agregar".equals(action)) {
            String cedula = request.getParameter("cedula");
            String dependencia = request.getParameter("dependencia");
            String nombre = request.getParameter("nombre");
            String [] split = dependencia.split(",");
            add(Integer.parseInt(split[0]),cedula,split[1],nombre);
        }

        request.setAttribute("funcionarios", findAll());
        request.setAttribute("dependencias", findAllDependencias());
        request.getRequestDispatcher("/presentacion/funcionario/funcionario.jsp").forward(request, response);

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

    
     private List<Funcionario> findAll() {
        List<Funcionario> funcionarios = null;
        FuncionarioDao dao = new FuncionarioDao();
        try {
            funcionarios = dao.findAll();
            return funcionarios;
        } catch (Exception ex) {
        }
        return null;
    }
     
private List<Dependencia> findAllDependencias() {
        List<Dependencia> dependencias = null;
        DependenciaDao dao = new DependenciaDao();
        try {
            dependencias = dao.findAll();
            return dependencias;
        } catch (Exception ex) {
        }
        return null;
    }

    private void add(Integer dependencia, String cedula, String dependenciaNombre, String nombre) {
        Funcionario funcionario = new Funcionario(dependencia, cedula, dependenciaNombre, nombre);
        FuncionarioDao dao = new FuncionarioDao();
        try {
            dao.save(funcionario);
        } catch (Exception ex) {
        }
    }
}
