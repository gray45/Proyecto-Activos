/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Utils.HibernateUtil;
import activos.logic.Biencategoria;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author grave
 */
public class BienCategoriaDao extends HibernateUtil implements IBaseDao<Biencategoria, Integer> {

    @Override
    public void save(Biencategoria obj) {
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
    public void merge(Biencategoria obj) {
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
    public Biencategoria findByID(Integer key) {
        Biencategoria usuario = null;

        try {
            iniciarOperacion();
            usuario = (Biencategoria) getSesion().get(BienCategoriaDao.class, key);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    @Override
    public void delete(Biencategoria obj) {
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
    public List<Biencategoria> findAll() {
        try {
            List<Biencategoria> choferes;
            iniciarOperacion();
            choferes = (List<Biencategoria>) getSesion().createQuery("FROM Biencategoria").list();
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
            List<Biencategoria> choferes;
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
    public List<Biencategoria> findByQueryLimit(String query, int limit ) {
        try {
            List<Biencategoria> usuarios;
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
