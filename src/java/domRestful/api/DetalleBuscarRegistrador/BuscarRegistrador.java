/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.DetalleBuscarRegistrador;

import Dao.SolicitudDao;
import Dao.UsuarioDao;
import activos.logic.Solicitud;
import activos.logic.Usuario;
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
    @Path("{findRegister}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> listRegistradores() {

        UsuarioDao dao = new UsuarioDao();
        String query = "FROM Usuario\n"
                + "WHERE rol = 'Registrador'";
        List<Usuario> usuariosRegistradores = dao.findByQuery(query);

        return usuariosRegistradores;
    }
    
    @POST
    @Path("{parametros}")
    @Produces({MediaType.TEXT_HTML})
    public String SeleccionRegistrador(@PathParam("parametros") String parametros) {
        String[] values = parametros.split(",");
        if(values.length == 2){
            try{
            SolicitudDao solicitudDao = new SolicitudDao(); 
            Solicitud solicitud = solicitudDao.findByID(Integer.parseInt(values[1]));
            solicitud.setRegistrador(values[0]);
            solicitud.setEstado("Asignada");
            solicitudDao.merge(solicitud);
            
             return "bien";}
            catch(Exception ex){
            return "mal";
            }
        }
        else{
        return "mal";
        }
 
    }
//        UsuarioDao dao = new UsuarioDao();
//        String query = "FROM Usuario\n"
//                + "WHERE rol LIKE '" + "%" + quest + "%'";
//        List<Usuario> usuariosRegistradores = dao.findByQuery(query);
//        Usuario registrador;
//
//        return registrador;

    



}
