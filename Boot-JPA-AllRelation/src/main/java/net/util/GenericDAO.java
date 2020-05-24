package net.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAO<T,K> {
	
	@PersistenceContext
	private EntityManager em;

	protected Class<T> clazz;
	
	public GenericDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public GenericDAO( Class<T> clazz) {
		this.clazz = clazz;
	}

	
	
	public EntityManager getEntityManager() {
		return em;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, timeout = 30)
	public T findById(K id)  throws Exception {
		T obj = em.find( clazz, id);
		
		return obj;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<T> findAll(){
    	Query objects = em.createQuery("select object(o) from " + clazz.getSimpleName() + " as o");
    	
		return objects.getResultList();
    }

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public T insert(T obj)  throws Exception {
		try {
			em.persist(obj);
			
			em.flush();
		} catch (Exception e) {
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}
		
    	return obj;
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
    public T update(T obj) throws Exception {
		T objResult = null;
		
		try {
			objResult = em.merge(obj);
			
			em.flush();
		} catch (Exception e) {
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}
		
    	return objResult;
    }

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
    public boolean delete(K id) throws Exception {
		try {
			T objResult = findById(id);

			em.remove(objResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}

		return true;
    }

}
