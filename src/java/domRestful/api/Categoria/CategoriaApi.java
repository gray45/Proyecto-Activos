/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.Categoria;

import Dao.CategoriaDao;
import activos.logic.Categoria;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("/Categoria")
public class CategoriaApi {

    @GET
    @Path("{findAll}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findAll() {
        CategoriaDao dao = new CategoriaDao();
        String query = "FROM Categoria\n"
                + "WHERE activo = 1";
        List<Categoria> categorias = dao.findByQuery(query);

        return categorias;
    }
    
      @POST
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Categoria getCategoria(@PathParam("id") String id) {
        CategoriaDao dao = new CategoriaDao();
        
       Categoria categoria = dao.findByID(Integer.parseInt(id));

        return categoria;
    }

    @POST
    @Path("{add}")
    @Produces({MediaType.TEXT_HTML})
    public String SeleccionRegistrador(@PathParam("add") String descripcion) {
        String respuesta = null;
        try {
            CategoriaDao categoriaDao = new CategoriaDao();
            Categoria cat = new Categoria(descripcion, 1);
            categoriaDao.save(cat);
            respuesta = "bien";
            return respuesta;
        } catch (Exception ex) {
            respuesta = "mal";
            return respuesta;
        }

    }
    
    @PUT
    @Path("{edit}")
    @Produces({MediaType.TEXT_HTML})
    public String edit(@PathParam("edit") String edit) {
        String respuesta = null;
        try {
            CategoriaDao categoriaDao = new CategoriaDao();
           String[] splitCateria = edit.split(",");
            Categoria cat = categoriaDao.findByID(Integer.parseInt(splitCateria[0]));
            cat.setDescripcion(splitCateria[1]);
            categoriaDao.merge(cat);
            respuesta = "bien";
            return respuesta;
        } catch (Exception ex) {
            respuesta = "mal";
            return respuesta;
        }

    }
    
    @DELETE
    @Path("{borrar}")
    @Produces({MediaType.TEXT_HTML})
    public String delete(@PathParam("borrar") String idCategoria) {
        String respuesta = null;
        try {
            CategoriaDao categoriaDao = new CategoriaDao();
            Categoria cat = categoriaDao.findByID(Integer.parseInt(idCategoria));
            cat.setActivo(0);
            categoriaDao.merge(cat);
            respuesta = "bien";
            return respuesta;
        } catch (Exception ex) {
            respuesta = "mal";
            return respuesta;
        }

    }
    
    
}
