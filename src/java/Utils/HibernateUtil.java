/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author grave
 */
public class HibernateUtil {

  private static final SessionFactory sessionFactory;
    private Session sesion;
    private Transaction transac;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void iniciarOperacion() throws HibernateException{
       sesion= HibernateUtil.getSessionFactory().openSession(); // toma toda la config abre sesion, inicia una transaccion por ejemplo cajero
       transac= sesion.beginTransaction();
               
    }
    
    public void manejarException(HibernateException he)throws HibernateException{
        transac.rollback(); // si hay un error con la transac hace un rollback y devuelve el error q hizo q se cayera
        throw new HibernateException(he.getMessage(),he);
    }
            
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }

    public Transaction getTransac() {
        return transac;
    }

    public void setTransac(Transaction transac) {
        this.transac = transac;
    }
}
