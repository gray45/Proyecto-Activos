/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import activos.logic.Dependencia;
import Utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author grave
 */
public class DependenciaDao extends HibernateUtil implements IBaseDao<Dependencia, Integer> {

    @Override
    public void save(Dependencia obj) {
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
    public void merge(Dependencia obj) {
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
    public Dependencia findByID(Integer key) {
        Dependencia usuario = null;

        try {
            iniciarOperacion();
            usuario = (Dependencia) getSesion().get(Dependencia.class, key);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    @Override
    public void delete(Dependencia obj) {
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
    public List<Dependencia> findAll() {
        try {
            List<Dependencia> choferes;
            iniciarOperacion();
            choferes = (List<Dependencia>) getSesion().createQuery("FROM Dependencia").list();
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
            List<Dependencia> choferes;
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
    public List<Dependencia> findByQueryLimit(String query, int limit ) {
        try {
            List<Dependencia> usuarios;
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

