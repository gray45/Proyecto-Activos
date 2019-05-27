/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.bien;


import Dao.BienDao;
import activos.logic.Bien;
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
    @POST
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Bien> findAll(@PathParam("id") String id) {
        BienDao dao = new BienDao();
        String query = "FROM Bien\n"
                    + "WHERE solicitud = " + id;
         List<Bien> Bienes = dao.findByQuery(query);

        return Bienes;
    }
}
