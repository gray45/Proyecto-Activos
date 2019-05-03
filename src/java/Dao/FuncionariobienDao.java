/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Utils.HibernateUtil;
import activos.logic.Funcionariobien;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author grave
 */
public class FuncionariobienDao extends HibernateUtil implements IBaseDao<Funcionariobien, Integer> {

    @Override
    public void save(Funcionariobien obj) {
        try {
            iniciarOperacion();
            getSesion().save(obj);
            getTransac().commit();
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }

    }

    @Override
    public void merge(Funcionariobien obj) {
        try {
            iniciarOperacion();
            getSesion().merge(obj);
            getTransac().commit();
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    @Override
    public Funcionariobien findByID(Integer key) {
        Funcionariobien usuario = null;

        try {
            iniciarOperacion();
            usuario = (Funcionariobien) getSesion().get(Funcionariobien.class, key);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    @Override
    public void delete(Funcionariobien obj) {
        try {
            iniciarOperacion();
            getSesion().delete(obj);
            getTransac().commit();
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }

    }

    @Override
    public List<Funcionariobien> findAll() {
        try {
            List<Funcionariobien> choferes;
            iniciarOperacion();
            choferes = (List<Funcionariobien>) getSesion().createQuery("FROM Funcionariobien").list();
            return choferes;
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    @Override
    public List findByQuery(String query) {
        try {
            List<Funcionariobien> choferes;
            iniciarOperacion();
            choferes =  getSesion().createQuery(query).list();
            return choferes;
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    
    @Override
    public List<Funcionariobien> findByQueryLimit(String query, int limit ) {
        try {
            List<Funcionariobien> usuarios;
            iniciarOperacion();
            usuarios =  getSesion().createQuery(query).setMaxResults(limit).list();
            return usuarios;
        } catch (HibernateException he) {
            manejarException(he);
            throw he;
        } finally {
            getSesion().close();
        }}
    
    @Override
    public List findHQLQuery(String query) {
        List lista=null;
        try{
            iniciarOperacion();
            lista = getSesion().createQuery(query).list();
            return lista;
        }catch(HibernateException he){
            manejarException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        
    }

    
}
