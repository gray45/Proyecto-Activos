/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Dao.FuncionarioDao;
import activos.logic.Funcionario;
/**
 *
 * @author grave
 */
public class FuncionarioTest {
     static public void main(String[] args){
       
         insertar();
         //findAllUsuario();
    }
    public static  void insertar(){
     Funcionario cat = new Funcionario(1,"402110725", "Informatica", "Greivin");
     FuncionarioDao catDao = new FuncionarioDao();
            catDao.save(cat);
     
         }
    
    
}
