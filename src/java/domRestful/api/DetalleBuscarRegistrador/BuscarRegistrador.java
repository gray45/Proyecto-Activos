/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.DetalleBuscarRegistrador;

import Dao.UsuarioDao;
import activos.logic.Usuario;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Anthony
 */
@Path("/DetalleBuscarRegistrador")
public class BuscarRegistrador {

    @GET
    @Path("{txt}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> listRegistradores(@PathParam("txt") String quest) {

        System.out.println(" input recibido " + quest);
        UsuarioDao dao = new UsuarioDao();
        String query = "FROM Usuario\n"
                + "WHERE rol = 'Registrador' AND nombre LIKE '" + "%" + quest + "%'";
        List<Usuario> usuariosRegistradores = dao.findByQuery(query);

        return usuariosRegistradores;
    }
    
    @POST
    @Path("{parametros}")
    @Produces({MediaType.APPLICATION_JSON})
    public void SeleccionRegistrador(@PathParam("parametros") String parametros) {
        String[] values = parametros.split(",");
        System.out.println("llego el malparido"+Arrays.toString(values));
 
    }
//        UsuarioDao dao = new UsuarioDao();
//        String query = "FROM Usuario\n"
//                + "WHERE rol LIKE '" + "%" + quest + "%'";
//        List<Usuario> usuariosRegistradores = dao.findByQuery(query);
//        Usuario registrador;
//
//        return registrador;

    



}
