/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.FuncionarioDao;
import Dao.UsuarioDao;
import activos.logic.Funcionario;
import activos.logic.Usuario;
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
@WebServlet(name = "UsuarioController", urlPatterns = {"/Controller/UsuarioController"})
public class UsuarioController extends HttpServlet {

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
        String action = request.getParameter("action");

        if ("agregar".equals(action)) {
            String descripcion = request.getParameter("descripcion");
            String funcionarioId = request.getParameter("funcionario");
            String userName = request.getParameter("UserName");
            String rol = request.getParameter("rol");
            String password = request.getParameter("password");

            Funcionario funcionario = getFuncionarioById(Integer.parseInt(funcionarioId));
            add(funcionario.getDependencia(), funcionario.getIdFuncionario(),
                    userName, rol, funcionario.getDependencia_1(), password);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        request.setAttribute("funcionarios", findAll());
        request.getRequestDispatcher("/presentacion/usuario/AgregarUsuario.jsp").forward(request, response);

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

    private Funcionario getFuncionarioById(Integer id) {

        FuncionarioDao dao = new FuncionarioDao();
        return (Funcionario) dao.findByID(id);
    }

    private void add(Integer dependencia, Integer funcionario, String nombre, String rol, String dependenciaDescricion, String password) {
        Usuario usuario = new Usuario(dependencia, funcionario, nombre, rol, dependenciaDescricion, password);
        UsuarioDao dao = new UsuarioDao();
        try {
            dao.save(usuario);
        } catch (Exception ex) {
        }
    }
}
