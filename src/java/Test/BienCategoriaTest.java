/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dao.BienCategoriaDao;
import Dao.UsuarioDao;
import activos.logic.Biencategoria;
import java.util.List;

/**
 *
 * @author grave
 */
public class BienCategoriaTest {
    
     static public void main(String[] args){
       
        // insertar();
         findAllUsuario();
         //editNombre(3);
    }
    public static  void insertar(){
     
     Biencategoria usuario = new Biencategoria(2,4);
     
     BienCategoriaDao usuariodao = new BienCategoriaDao();
            usuariodao.save(usuario);
     
         }
    
    public static void findAllUsuario() {
        List<Biencategoria> usuarios;
        BienCategoriaDao bl = new BienCategoriaDao();
        usuarios = bl.findAll();
        // usuarios = bl.findAll();
        usuarios.forEach((aux) -> {
            System.out.println(aux.getBien());
        });
        
    }
    
   }
