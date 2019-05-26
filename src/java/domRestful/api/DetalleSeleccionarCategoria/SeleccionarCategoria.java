/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.DetalleSeleccionarCategoria;
 
import Dao.CategoriaDao;
import Dao.SolicitudDao;
import Dao.UsuarioDao;
import activos.logic.Categoria;
import activos.logic.ModelDetalle;
import activos.logic.Solicitud;
import activos.logic.Usuario;
import java.util.ArrayList;
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
@Path("/DetalleSeleccionarCategoria")
public class SeleccionarCategoria {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Categoria> listRegistradores() throws Exception {
        System.out.print("llego al servidor");
        return ModelDetalle.listarCategorias();
    }
    
}
