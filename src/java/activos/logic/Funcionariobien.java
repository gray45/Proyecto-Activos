package activos.logic;
// Generated 26/04/2019 05:23:55 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




/**
 * Funcionariobien generated by hbm2java
 */
@Entity
@Table(name="Funcionariosbien"
    ,catalog="Activos"
)
public class Funcionariobien  implements java.io.Serializable {


     private String codigo;
     private Integer bien;
     private Integer funcionario;

    public Funcionariobien() {
    }

    public Funcionariobien(String codigo, Integer bien, Integer funcionario) {
       this.codigo = codigo;
       this.bien = bien;
       this.funcionario = funcionario;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="codigo", unique=true, nullable=false)
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bien", nullable=false)
    public Integer getBien() {
        return this.bien;
    }
    
    public void setBien(Integer bien) {
        this.bien = bien;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="funcionario", nullable=false)
    public Integer getFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }


}


