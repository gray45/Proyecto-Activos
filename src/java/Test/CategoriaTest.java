/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import activos.logic.Categoria;
import Dao.CategoriaDao;
import java.util.List;

/**
 *
 * @author grave
 */
public class CategoriaTest {
    
    
    static public void main(String[] args){
       
       //  insertar();
         findAllUsuario();
    }
    public static  void insertar(){
     for(int i=0; i<5; i++){
     Categoria cat = new Categoria("electronica",1);
     CategoriaDao catDao = new CategoriaDao();
            catDao.save(cat);
     }
         }
    
    public static void findAllUsuario() {
        List<Categoria> usuarios;
        CategoriaDao bl = new CategoriaDao();
        usuarios = bl.findAll();
        // usuarios = bl.findAll();
        usuarios.forEach((aux) -> {
            System.out.println(aux.getDescripcion());
        });
    }
}
