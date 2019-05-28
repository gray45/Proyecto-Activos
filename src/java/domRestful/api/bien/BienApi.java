/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.bien;

import Dao.BienCategoriaDao;
import Dao.BienDao;
import activos.logic.Bien;
import activos.logic.Biencategoria;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author grave
 */
@Path("/Bien")
public class BienApi {

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Bien> findAll(@PathParam("id") String id) {
        BienDao dao = new BienDao();
        String query = "FROM Bien\n"
                + "WHERE solicitud = " + id;
        List<Bien> Bienes = dao.findByQuery(query);
        
        return Bienes;
    }
    
    @POST
    @Path("{asignar}")
    @Produces({MediaType.TEXT_HTML})
    public String asignar(@PathParam("asignar") String asignar) {
        try {
            String[] splitAsinar = asignar.split(",");
            Biencategoria bienCate = new Biencategoria(Integer.parseInt(splitAsinar[0]),
                    Integer.parseInt(splitAsinar[1]));
            BienCategoriaDao dao = new BienCategoriaDao();
            dao.save(bienCate);
            return "bien";
        } catch (Exception ex) {
            return "mal";
        }
        
    }
}
