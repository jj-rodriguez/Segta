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
import segta.clases.Contratodeterminacion;
import segta.clases.Determinacion;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class DeterminacionJpaController implements Serializable {

    public DeterminacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Determinacion determinacion) {
        if (determinacion.getAnalisisdeterminacionCollection() == null) {
            determinacion.setAnalisisdeterminacionCollection(new ArrayList<Analisisdeterminacion>());
        }
        if (determinacion.getContratodeterminacionCollection() == null) {
            determinacion.setContratodeterminacionCollection(new ArrayList<Contratodeterminacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Analisisdeterminacion> attachedAnalisisdeterminacionCollection = new ArrayList<Analisisdeterminacion>();
            for (Analisisdeterminacion analisisdeterminacionCollectionAnalisisdeterminacionToAttach : determinacion.getAnalisisdeterminacionCollection()) {
                analisisdeterminacionCollectionAnalisisdeterminacionToAttach = em.getReference(analisisdeterminacionCollectionAnalisisdeterminacionToAttach.getClass(), analisisdeterminacionCollectionAnalisisdeterminacionToAttach.getIdAnalisisDeterminacion());
                attachedAnalisisdeterminacionCollection.add(analisisdeterminacionCollectionAnalisisdeterminacionToAttach);
            }
            determinacion.setAnalisisdeterminacionCollection(attachedAnalisisdeterminacionCollection);
            Collection<Contratodeterminacion> attachedContratodeterminacionCollection = new ArrayList<Contratodeterminacion>();
            for (Contratodeterminacion contratodeterminacionCollectionContratodeterminacionToAttach : determinacion.getContratodeterminacionCollection()) {
                contratodeterminacionCollectionContratodeterminacionToAttach = em.getReference(contratodeterminacionCollectionContratodeterminacionToAttach.getClass(), contratodeterminacionCollectionContratodeterminacionToAttach.getIdContratoDeterminacion());
                attachedContratodeterminacionCollection.add(contratodeterminacionCollectionContratodeterminacionToAttach);
            }
            determinacion.setContratodeterminacionCollection(attachedContratodeterminacionCollection);
            em.persist(determinacion);
            for (Analisisdeterminacion analisisdeterminacionCollectionAnalisisdeterminacion : determinacion.getAnalisisdeterminacionCollection()) {
                Determinacion oldIdDeterminacionOfAnalisisdeterminacionCollectionAnalisisdeterminacion = analisisdeterminacionCollectionAnalisisdeterminacion.getIdDeterminacion();
                analisisdeterminacionCollectionAnalisisdeterminacion.setIdDeterminacion(determinacion);
                analisisdeterminacionCollectionAnalisisdeterminacion = em.merge(analisisdeterminacionCollectionAnalisisdeterminacion);
                if (oldIdDeterminacionOfAnalisisdeterminacionCollectionAnalisisdeterminacion != null) {
                    oldIdDeterminacionOfAnalisisdeterminacionCollectionAnalisisdeterminacion.getAnalisisdeterminacionCollection().remove(analisisdeterminacionCollectionAnalisisdeterminacion);
                    oldIdDeterminacionOfAnalisisdeterminacionCollectionAnalisisdeterminacion = em.merge(oldIdDeterminacionOfAnalisisdeterminacionCollectionAnalisisdeterminacion);
                }
            }
            for (Contratodeterminacion contratodeterminacionCollectionContratodeterminacion : determinacion.getContratodeterminacionCollection()) {
                Determinacion oldIdDeterminacionOfContratodeterminacionCollectionContratodeterminacion = contratodeterminacionCollectionContratodeterminacion.getIdDeterminacion();
                contratodeterminacionCollectionContratodeterminacion.setIdDeterminacion(determinacion);
                contratodeterminacionCollectionContratodeterminacion = em.merge(contratodeterminacionCollectionContratodeterminacion);
                if (oldIdDeterminacionOfContratodeterminacionCollectionContratodeterminacion != null) {
                    oldIdDeterminacionOfContratodeterminacionCollectionContratodeterminacion.getContratodeterminacionCollection().remove(contratodeterminacionCollectionContratodeterminacion);
                    oldIdDeterminacionOfContratodeterminacionCollectionContratodeterminacion = em.merge(oldIdDeterminacionOfContratodeterminacionCollectionContratodeterminacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Determinacion determinacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Determinacion persistentDeterminacion = em.find(Determinacion.class, determinacion.getIdDeterminacion());
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionOld = persistentDeterminacion.getAnalisisdeterminacionCollection();
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionNew = determinacion.getAnalisisdeterminacionCollection();
            Collection<Contratodeterminacion> contratodeterminacionCollectionOld = persistentDeterminacion.getContratodeterminacionCollection();
            Collection<Contratodeterminacion> contratodeterminacionCollectionNew = determinacion.getContratodeterminacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Analisisdeterminacion analisisdeterminacionCollectionOldAnalisisdeterminacion : analisisdeterminacionCollectionOld) {
                if (!analisisdeterminacionCollectionNew.contains(analisisdeterminacionCollectionOldAnalisisdeterminacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Analisisdeterminacion " + analisisdeterminacionCollectionOldAnalisisdeterminacion + " since its idDeterminacion field is not nullable.");
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
            determinacion.setAnalisisdeterminacionCollection(analisisdeterminacionCollectionNew);
            Collection<Contratodeterminacion> attachedContratodeterminacionCollectionNew = new ArrayList<Contratodeterminacion>();
            for (Contratodeterminacion contratodeterminacionCollectionNewContratodeterminacionToAttach : contratodeterminacionCollectionNew) {
                contratodeterminacionCollectionNewContratodeterminacionToAttach = em.getReference(contratodeterminacionCollectionNewContratodeterminacionToAttach.getClass(), contratodeterminacionCollectionNewContratodeterminacionToAttach.getIdContratoDeterminacion());
                attachedContratodeterminacionCollectionNew.add(contratodeterminacionCollectionNewContratodeterminacionToAttach);
            }
            contratodeterminacionCollectionNew = attachedContratodeterminacionCollectionNew;
            determinacion.setContratodeterminacionCollection(contratodeterminacionCollectionNew);
            determinacion = em.merge(determinacion);
            for (Analisisdeterminacion analisisdeterminacionCollectionNewAnalisisdeterminacion : analisisdeterminacionCollectionNew) {
                if (!analisisdeterminacionCollectionOld.contains(analisisdeterminacionCollectionNewAnalisisdeterminacion)) {
                    Determinacion oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion = analisisdeterminacionCollectionNewAnalisisdeterminacion.getIdDeterminacion();
                    analisisdeterminacionCollectionNewAnalisisdeterminacion.setIdDeterminacion(determinacion);
                    analisisdeterminacionCollectionNewAnalisisdeterminacion = em.merge(analisisdeterminacionCollectionNewAnalisisdeterminacion);
                    if (oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion != null && !oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion.equals(determinacion)) {
                        oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion.getAnalisisdeterminacionCollection().remove(analisisdeterminacionCollectionNewAnalisisdeterminacion);
                        oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion = em.merge(oldIdDeterminacionOfAnalisisdeterminacionCollectionNewAnalisisdeterminacion);
                    }
                }
            }
            for (Contratodeterminacion contratodeterminacionCollectionOldContratodeterminacion : contratodeterminacionCollectionOld) {
                if (!contratodeterminacionCollectionNew.contains(contratodeterminacionCollectionOldContratodeterminacion)) {
                    contratodeterminacionCollectionOldContratodeterminacion.setIdDeterminacion(null);
                    contratodeterminacionCollectionOldContratodeterminacion = em.merge(contratodeterminacionCollectionOldContratodeterminacion);
                }
            }
            for (Contratodeterminacion contratodeterminacionCollectionNewContratodeterminacion : contratodeterminacionCollectionNew) {
                if (!contratodeterminacionCollectionOld.contains(contratodeterminacionCollectionNewContratodeterminacion)) {
                    Determinacion oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion = contratodeterminacionCollectionNewContratodeterminacion.getIdDeterminacion();
                    contratodeterminacionCollectionNewContratodeterminacion.setIdDeterminacion(determinacion);
                    contratodeterminacionCollectionNewContratodeterminacion = em.merge(contratodeterminacionCollectionNewContratodeterminacion);
                    if (oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion != null && !oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion.equals(determinacion)) {
                        oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion.getContratodeterminacionCollection().remove(contratodeterminacionCollectionNewContratodeterminacion);
                        oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion = em.merge(oldIdDeterminacionOfContratodeterminacionCollectionNewContratodeterminacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = determinacion.getIdDeterminacion();
                if (findDeterminacion(id) == null) {
                    throw new NonexistentEntityException("The determinacion with id " + id + " no longer exists.");
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
            Determinacion determinacion;
            try {
                determinacion = em.getReference(Determinacion.class, id);
                determinacion.getIdDeterminacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The determinacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Analisisdeterminacion> analisisdeterminacionCollectionOrphanCheck = determinacion.getAnalisisdeterminacionCollection();
            for (Analisisdeterminacion analisisdeterminacionCollectionOrphanCheckAnalisisdeterminacion : analisisdeterminacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Determinacion (" + determinacion + ") cannot be destroyed since the Analisisdeterminacion " + analisisdeterminacionCollectionOrphanCheckAnalisisdeterminacion + " in its analisisdeterminacionCollection field has a non-nullable idDeterminacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Contratodeterminacion> contratodeterminacionCollection = determinacion.getContratodeterminacionCollection();
            for (Contratodeterminacion contratodeterminacionCollectionContratodeterminacion : contratodeterminacionCollection) {
                contratodeterminacionCollectionContratodeterminacion.setIdDeterminacion(null);
                contratodeterminacionCollectionContratodeterminacion = em.merge(contratodeterminacionCollectionContratodeterminacion);
            }
            em.remove(determinacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Determinacion> findDeterminacionEntities() {
        return findDeterminacionEntities(true, -1, -1);
    }

    public List<Determinacion> findDeterminacionEntities(int maxResults, int firstResult) {
        return findDeterminacionEntities(false, maxResults, firstResult);
    }

    private List<Determinacion> findDeterminacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Determinacion.class));
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

    public Determinacion findDeterminacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Determinacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeterminacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Determinacion> rt = cq.from(Determinacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
