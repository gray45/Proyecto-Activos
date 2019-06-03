/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.Activo;

import Dao.ActivoDao;
import Dao.FuncionarioDao;
import activos.logic.Activo;
import activos.logic.Funcionario;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author grave
 */
@Path("/Activo")
public class ActivoApi {
    @GET
    @Path("{findAll}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Activo> findAll() {
        ActivoDao dao = new ActivoDao();
        List<Activo> ativos = dao.findAll();

        return ativos;
    }
    
    
    @PATCH
    @Path("{quest}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Activo> findQuest(@PathParam("quest") String quest ) {
        ActivoDao dao = new ActivoDao();
        String query = "FROM Activo\n"
                + "WHERE codigo LIKE '" + "%" + quest + "%' OR descripcion LIKE '" + "%" + quest + "%' " +
                "OR categoria LIKE '" + "%" + quest + "%' OR funcionarioNombre LIKE '" + "%" + quest + "%' ";
        List<Activo> ativos = dao.findByQuery(query);

        return ativos;
    }
    
    @POST
    @Path("{findAllFuncionario}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Funcionario> findQuest() {
        FuncionarioDao dao = new FuncionarioDao();
         List<Funcionario> funcionarios = dao.findAll();

        return funcionarios;
    }
    
    
    @PUT
    @Path("{asignar}")
    @Produces({MediaType.TEXT_HTML})
    public String asignar(@PathParam("asignar") String asignar) {
        try {
            String[] splitAsinar = asignar.split(",");
            String codigo = splitAsinar[0];
            int idFuncionario = Integer.parseInt(splitAsinar[1]);
            ActivoDao dao = new ActivoDao();
             String query = "FROM Activo\n"
                + "WHERE codigo = '"  + codigo + "'";
            Activo activo = (Activo) dao.findByQuery(query).get(0);
            FuncionarioDao daoFun = new FuncionarioDao();
            Funcionario funcionario = daoFun.findByID(idFuncionario);
            activo.setFuncionario(idFuncionario);
            activo.setFuncionarioNombre(funcionario.getNombre());
            dao.merge(activo);

            return "bien";
        } catch (Exception ex) {
            return "mal";
        }

    }
}
