/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import segta.clases.Tambor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Pool;
import segta.exceptions.NonexistentEntityException;

/**
 *
 * @author Quales Group
 */
public class PoolJpaController implements Serializable {

    public PoolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pool pool) {
        if (pool.getTamborCollection() == null) {
            pool.setTamborCollection(new ArrayList<Tambor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tambor> attachedTamborCollection = new ArrayList<Tambor>();
            for (Tambor tamborCollectionTamborToAttach : pool.getTamborCollection()) {
                tamborCollectionTamborToAttach = em.getReference(tamborCollectionTamborToAttach.getClass(), tamborCollectionTamborToAttach.getIdTambor());
                attachedTamborCollection.add(tamborCollectionTamborToAttach);
            }
            pool.setTamborCollection(attachedTamborCollection);
            em.persist(pool);
            for (Tambor tamborCollectionTambor : pool.getTamborCollection()) {
                Pool oldIdPoolOfTamborCollectionTambor = tamborCollectionTambor.getIdPool();
                tamborCollectionTambor.setIdPool(pool);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
                if (oldIdPoolOfTamborCollectionTambor != null) {
                    oldIdPoolOfTamborCollectionTambor.getTamborCollection().remove(tamborCollectionTambor);
                    oldIdPoolOfTamborCollectionTambor = em.merge(oldIdPoolOfTamborCollectionTambor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pool pool) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pool persistentPool = em.find(Pool.class, pool.getIdpool());
            Collection<Tambor> tamborCollectionOld = persistentPool.getTamborCollection();
            Collection<Tambor> tamborCollectionNew = pool.getTamborCollection();
            Collection<Tambor> attachedTamborCollectionNew = new ArrayList<Tambor>();
            for (Tambor tamborCollectionNewTamborToAttach : tamborCollectionNew) {
                tamborCollectionNewTamborToAttach = em.getReference(tamborCollectionNewTamborToAttach.getClass(), tamborCollectionNewTamborToAttach.getIdTambor());
                attachedTamborCollectionNew.add(tamborCollectionNewTamborToAttach);
            }
            tamborCollectionNew = attachedTamborCollectionNew;
            pool.setTamborCollection(tamborCollectionNew);
            pool = em.merge(pool);
            for (Tambor tamborCollectionOldTambor : tamborCollectionOld) {
                if (!tamborCollectionNew.contains(tamborCollectionOldTambor)) {
                    tamborCollectionOldTambor.setIdPool(null);
                    tamborCollectionOldTambor = em.merge(tamborCollectionOldTambor);
                }
            }
            for (Tambor tamborCollectionNewTambor : tamborCollectionNew) {
                if (!tamborCollectionOld.contains(tamborCollectionNewTambor)) {
                    Pool oldIdPoolOfTamborCollectionNewTambor = tamborCollectionNewTambor.getIdPool();
                    tamborCollectionNewTambor.setIdPool(pool);
                    tamborCollectionNewTambor = em.merge(tamborCollectionNewTambor);
                    if (oldIdPoolOfTamborCollectionNewTambor != null && !oldIdPoolOfTamborCollectionNewTambor.equals(pool)) {
                        oldIdPoolOfTamborCollectionNewTambor.getTamborCollection().remove(tamborCollectionNewTambor);
                        oldIdPoolOfTamborCollectionNewTambor = em.merge(oldIdPoolOfTamborCollectionNewTambor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pool.getIdpool();
                if (findPool(id) == null) {
                    throw new NonexistentEntityException("The pool with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pool pool;
            try {
                pool = em.getReference(Pool.class, id);
                pool.getIdpool();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pool with id " + id + " no longer exists.", enfe);
            }
            Collection<Tambor> tamborCollection = pool.getTamborCollection();
            for (Tambor tamborCollectionTambor : tamborCollection) {
                tamborCollectionTambor.setIdPool(null);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
            }
            em.remove(pool);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pool> findPoolEntities() {
        return findPoolEntities(true, -1, -1);
    }

    public List<Pool> findPoolEntities(int maxResults, int firstResult) {
        return findPoolEntities(false, maxResults, firstResult);
    }

    private List<Pool> findPoolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pool.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pool findPool(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pool.class, id);
        } finally {
            em.close();
        }
    }

    public int getPoolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pool> rt = cq.from(Pool.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
