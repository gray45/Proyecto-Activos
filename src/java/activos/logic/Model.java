package activos.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Escinf
 */
public class Model {
    static Map<String,Funcionario> funcionarios=new HashMap<>();
    
    static Map<String,Usuario> usuarios=initUsuarios();

    private static Map<String,Usuario> initUsuarios(){
        Map<String,Usuario> result = new HashMap<>();
        
       // result.put("207640246", new Usuario(1,207640246,"Anthony Oviedo","ADM", "Informatica","admin"));
        
        return result;
    }    
    
    public static void agregar(Funcionario p) throws Exception{
        if(funcionarios.containsKey( p.getCedula() )) throw new Exception("Persona ya existe");
        funcionarios.put(p.getCedula(), p);
    }
    
//    public static List<Persona> listar(){
//        return new ArrayList(personas.values());
//    }
    
//    public static Persona consultar(Persona id)throws Exception{
//        Persona result = personas.get(id.getNombre());
//        if (result==null) throw new Exception("Persona no existe");
//        return result;
//    }   
    
    public static Usuario login(Usuario id)throws Exception{
        Usuario result = usuarios.get(""+id.getId());
        if (result==null) throw new Exception("Usuario no existe");
        return result;
    }      

    public static  ArrayList<String> generarCodigos(List<Bien> bienes) {
        ArrayList<String> codigos  = new ArrayList(){};
        
        //generar codigos aqui 
        Bien b = null;
        for (int i = 0; i < bienes.size(); i++) {
            b = bienes.get(i);
            for (int j = 0; j <  Integer.parseInt(b.getCantidad()); j++) {
                String unCodigo = b.getCategoria()+"-"+ b.getIdBien()+"-"+j;
                codigos.add(unCodigo);
            }
        }
        
        
        return codigos;
    }
}
