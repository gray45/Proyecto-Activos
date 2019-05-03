/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dao.FuncionariobienDao;
import activos.logic.Funcionariobien;
import java.util.List;

/**
 *
 * @author grave
 */
public class FuniconariobienTest {
    
     static public void main(String[] args){
       
         //insertar();
         findAllUsuario();
         //editNombre(3);
    }
    public static  void insertar(){
     
     Funcionariobien usuario = new Funcionariobien("dgr45",2,4);
     
     FuncionariobienDao usuariodao = new FuncionariobienDao();
            usuariodao.save(usuario);
     
         }
    
    public static void findAllUsuario() {
        List<Funcionariobien> usuarios;
        FuncionariobienDao bl = new FuncionariobienDao();
        usuarios = bl.findAll();
        // usuarios = bl.findAll();
        usuarios.forEach((aux) -> {
            System.out.println(aux.getBien());
        });
        
    }
}
