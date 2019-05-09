/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dao.SolicitudDao;
import activos.logic.Solicitud;
import java.util.List;

/**
 *
 * @author grave
 */
public class SolicitudTest {
    static public void main(String[] args){
       
        // insertar();
         findAllUsuario();
    }
    public static  void insertar(){
     for(int i=0; i<5; i++){
     Solicitud solicitud = new Solicitud(3,"recibido"  + i, "j54", "hoy" ,"donacion","informatica" ,"gray");
     
     SolicitudDao usuariodao = new SolicitudDao();
            usuariodao.save(solicitud);
     }
         }
    
    public static void findAllUsuario() {
        List<Solicitud> usuarios;
        SolicitudDao bl = new SolicitudDao();
        usuarios = bl.findAll();
        // usuarios = bl.findAll();
        usuarios.forEach((aux) -> {
            System.out.println(aux.getComprobante());
        });
        
       
    }
    
}
