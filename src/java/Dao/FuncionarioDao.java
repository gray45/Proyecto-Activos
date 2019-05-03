/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Utils.HibernateUtil;
import activos.logic.Funcionario;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author grave
 */
public class FuncionarioDao extends HibernateUtil implements IBaseDao<Funcionario, Integer> {

    @Override
    public void save(Funcionario obj) {
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
    public void merge(Funcionario obj) {
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
    public Funcionario findByID(Integer key) {
        Funcionario usuario = null;

        try {
            iniciarOperacion();
            usuario = (Funcionario) getSesion().get(Funcionario.class, key);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    @Override
    public void delete(Funcionario obj) {
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
    public List<Funcionario> findAll() {
        try {
            List<Funcionario> choferes;
            iniciarOperacion();
            choferes = (List<Funcionario>) getSesion().createQuery("FROM Funcionario").list();
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
            List<Funcionario> choferes;
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
    public List<Funcionario> findByQueryLimit(String query, int limit ) {
        try {
            List<Funcionario> usuarios;
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
