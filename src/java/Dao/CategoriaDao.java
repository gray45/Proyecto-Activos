/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import activos.logic.Categoria;
import Utils.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author grave
 */
public class CategoriaDao extends HibernateUtil implements IBaseDao<Categoria, Integer> {

    @Override
    public void save(Categoria obj) {
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
    public void merge(Categoria obj) {
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
    public Categoria findByID(Integer key) {
        Categoria usuario = null;

        try {
            iniciarOperacion();
            usuario = (Categoria) getSesion().get(Categoria.class, key);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    @Override
    public void delete(Categoria obj) {
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
    public List<Categoria> findAll() {
        try {
            List<Categoria> choferes;
            iniciarOperacion();
            choferes = (List<Categoria>) getSesion().createQuery("FROM Categoria").list();
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
            List<Categoria> choferes;
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
    public List<Categoria> findByQueryLimit(String query, int limit ) {
        try {
            List<Categoria> usuarios;
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
