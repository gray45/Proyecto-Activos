package activos.logic;
// Generated 26/04/2019 05:23:55 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categoria generated by hbm2java
 */
public class Categoria  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private int activo;

    public Categoria() {
    }

	
    public Categoria(String descripcion, int activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getActivo() {
        return this.activo;
    }
    
    public void setActivo(int activo) {
        this.activo = activo;
    }
   




}


