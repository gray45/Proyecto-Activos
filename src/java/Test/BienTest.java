/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dao.BienDao;
import activos.logic.Bien;
import java.util.List;

/**
 *
 * @author grave
 */
public class BienTest {
    static public void main(String[] args){
       
         //insertar();
         findAllUsuario();
    }
    public static  void insertar(){
     for(int i=0; i<5; i++){
     Bien cat = new Bien(2,"compuatadora", "3" ,"Dell", 350000 ,"inactivo");
     BienDao catDao = new BienDao();
            catDao.save(cat);
     }
         }
    
    public static void findAllUsuario() {
        List<Bien> usuarios;
        BienDao bl = new BienDao();
        usuarios = bl.findAll();
        // usuarios = bl.findAll();
        usuarios.forEach((aux) -> {
            System.out.println(aux.getDescripcion());
        });
    }
}
