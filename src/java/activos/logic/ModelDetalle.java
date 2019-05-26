/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activos.logic;

import Dao.CategoriaDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anthony
 */
public class ModelDetalle {

    static List<Categoria> categorias = new ArrayList(){};

    public static List<Categoria> listarCategorias() throws Exception {
        
        CategoriaDao dao = new CategoriaDao();
        String query = "FROM Categoria\n"
                + "WHERE activo = 1";
        categorias = dao.findByQuery(query);
        
        return categorias;

    }

}
