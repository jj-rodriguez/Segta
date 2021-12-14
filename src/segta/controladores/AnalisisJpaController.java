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
import segta.clases.Analisisdeterminacion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Analisis;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class AnalisisJpaController implements Serializable {

    public AnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Analisis analisis) {
        if (analisis.getAnalisisdeterminacionCollection() == null) {
            analisis.setAnalisisdeterminacionCollection(new ArrayList<Analisisdeterminacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Analisisdeterminacion> attachedAnalisisdeterminacionCollection = new ArrayList<Analisisdeterminacion>();
            for (Analisisdeterminacion analisisdeterminacionCollectionAnalisisdeterminacionToAttach : analisis.getAnalisisdeterminacionCollection()) {
                analisisdeterminacionCollectionAnalisisdeterminacionToAttach = em.getReference(analisisdeterminacionCollectionAnalisisdeterminacionToAttach.getClass(), analisisdeterminacionCollectionAnalisisdeterminacionToAttach.getIdAnalisisDeterminacion());
                attachedAnalisisdeterminacionCollection.add(analisisdeterminacionCollectionAnalisisdeterminacionToAttach);
            }
            analisis.setAnalisisdeterminacionCollection(attachedAnalisisdeterminacionCollection);
            em.persist(analisis);
            for (Analisisdeterminacion analisisdeterminacionCollectionAnalisisdeterminacion : analisis.getAnalisisdeterminacionCollection()) {
                Analisis oldIdAnalisisOfAnalisisdeterminacionCollectionAnalisisdeterminacion = analisisdeterminacionCollectionAnalisisdeterminacion.getIdAnalisis();
                analisisdeterminacionCollectionAnalisisdeterminacion.setIdAnalisis(analisis);
                analisisdeterminacionCollectionAnalisisdeterminacion = em.merge(analisisdeterminacionCollectionAnalisisdeterminacion);
                if (oldIdAnalisisOfAnalisisdeterminacionCollectionAnalisisdeterminacion != null) {
                    oldIdAnalisisOfAnalisisdeterminacionCollectionAnalisisdeterminacion.getAnalisisdeterminacionCollection().remove(analisisdeterminacionCollectionAnalisisdeterminacion);
                    oldIdAnalisisOfAnalisisdeterminacionCollectionAnalisisdeterminacion = em.merge(oldIdAnalisisOfAnalisisdeterminacionCollectionAnalisisdeterminacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Analisis analisis) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analisis persistentAnalisis = em.find(Analisis.class, analisis.getIdAnalisis());
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionOld = persistentAnalisis.getAnalisisdeterminacionCollection();
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionNew = analisis.getAnalisisdeterminacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Analisisdeterminacion analisisdeterminacionCollectionOldAnalisisdeterminacion : analisisdeterminacionCollectionOld) {
                if (!analisisdeterminacionCollectionNew.contains(analisisdeterminacionCollectionOldAnalisisdeterminacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Analisisdeterminacion " + analisisdeterminacionCollectionOldAnalisisdeterminacion + " since its idAnalisis field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Analisisdeterminacion> attachedAnalisisdeterminacionCollectionNew = new ArrayList<Analisisdeterminacion>();
            for (Analisisdeterminacion analisisdeterminacionCollectionNewAnalisisdeterminacionToAttach : analisisdeterminacionCollectionNew) {
                analisisdeterminacionCollectionNewAnalisisdeterminacionToAttach = em.getReference(analisisdeterminacionCollectionNewAnalisisdeterminacionToAttach.getClass(), analisisdeterminacionCollectionNewAnalisisdeterminacionToAttach.getIdAnalisisDeterminacion());
                attachedAnalisisdeterminacionCollectionNew.add(analisisdeterminacionCollectionNewAnalisisdeterminacionToAttach);
            }
            analisisdeterminacionCollectionNew = attachedAnalisisdeterminacionCollectionNew;
            analisis.setAnalisisdeterminacionCollection(analisisdeterminacionCollectionNew);
            analisis = em.merge(analisis);
            for (Analisisdeterminacion analisisdeterminacionCollectionNewAnalisisdeterminacion : analisisdeterminacionCollectionNew) {
                if (!analisisdeterminacionCollectionOld.contains(analisisdeterminacionCollectionNewAnalisisdeterminacion)) {
                    Analisis oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion = analisisdeterminacionCollectionNewAnalisisdeterminacion.getIdAnalisis();
                    analisisdeterminacionCollectionNewAnalisisdeterminacion.setIdAnalisis(analisis);
                    analisisdeterminacionCollectionNewAnalisisdeterminacion = em.merge(analisisdeterminacionCollectionNewAnalisisdeterminacion);
                    if (oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion != null && !oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion.equals(analisis)) {
                        oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion.getAnalisisdeterminacionCollection().remove(analisisdeterminacionCollectionNewAnalisisdeterminacion);
                        oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion = em.merge(oldIdAnalisisOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analisis.getIdAnalisis();
                if (findAnalisis(id) == null) {
                    throw new NonexistentEntityException("The analisis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analisis analisis;
            try {
                analisis = em.getReference(Analisis.class, id);
                analisis.getIdAnalisis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analisis with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionOrphanCheck = analisis.getAnalisisdeterminacionCollection();
            for (Analisisdeterminacion analisisdeterminacionCollectionOrphanCheckAnalisisdeterminacion : analisisdeterminacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Analisis (" + analisis + ") cannot be destroyed since the Analisisdeterminacion " + analisisdeterminacionCollectionOrphanCheckAnalisisdeterminacion + " in its analisisdeterminacionCollection field has a non-nullable idAnalisis field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(analisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Analisis> findAnalisisEntities() {
        return findAnalisisEntities(true, -1, -1);
    }

    public List<Analisis> findAnalisisEntities(int maxResults, int firstResult) {
        return findAnalisisEntities(false, maxResults, firstResult);
    }

    private List<Analisis> findAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Analisis.class));
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

    public Analisis findAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Analisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Analisis> rt = cq.from(Analisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
