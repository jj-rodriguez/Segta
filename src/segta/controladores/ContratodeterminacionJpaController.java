/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import segta.clases.Contratodeterminacion;
import segta.clases.Determinacion;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class ContratodeterminacionJpaController implements Serializable {

    public ContratodeterminacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratodeterminacion contratodeterminacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Determinacion idDeterminacion = contratodeterminacion.getIdDeterminacion();
            if (idDeterminacion != null) {
                idDeterminacion = em.getReference(idDeterminacion.getClass(), idDeterminacion.getIdDeterminacion());
                contratodeterminacion.setIdDeterminacion(idDeterminacion);
            }
            em.persist(contratodeterminacion);
            if (idDeterminacion != null) {
                idDeterminacion.getContratodeterminacionCollection().add(contratodeterminacion);
                idDeterminacion = em.merge(idDeterminacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contratodeterminacion contratodeterminacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratodeterminacion persistentContratodeterminacion = em.find(Contratodeterminacion.class, contratodeterminacion.getIdContratoDeterminacion());
            Determinacion idDeterminacionOld = persistentContratodeterminacion.getIdDeterminacion();
            Determinacion idDeterminacionNew = contratodeterminacion.getIdDeterminacion();
            if (idDeterminacionNew != null) {
                idDeterminacionNew = em.getReference(idDeterminacionNew.getClass(), idDeterminacionNew.getIdDeterminacion());
                contratodeterminacion.setIdDeterminacion(idDeterminacionNew);
            }
            contratodeterminacion = em.merge(contratodeterminacion);
            if (idDeterminacionOld != null && !idDeterminacionOld.equals(idDeterminacionNew)) {
                idDeterminacionOld.getContratodeterminacionCollection().remove(contratodeterminacion);
                idDeterminacionOld = em.merge(idDeterminacionOld);
            }
            if (idDeterminacionNew != null && !idDeterminacionNew.equals(idDeterminacionOld)) {
                idDeterminacionNew.getContratodeterminacionCollection().add(contratodeterminacion);
                idDeterminacionNew = em.merge(idDeterminacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contratodeterminacion.getIdContratoDeterminacion();
                if (findContratodeterminacion(id) == null) {
                    throw new NonexistentEntityException("The contratodeterminacion with id " + id + " no longer exists.");
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
            Contratodeterminacion contratodeterminacion;
            try {
                contratodeterminacion = em.getReference(Contratodeterminacion.class, id);
                contratodeterminacion.getIdContratoDeterminacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratodeterminacion with id " + id + " no longer exists.", enfe);
            }
            Determinacion idDeterminacion = contratodeterminacion.getIdDeterminacion();
            if (idDeterminacion != null) {
                idDeterminacion.getContratodeterminacionCollection().remove(contratodeterminacion);
                idDeterminacion = em.merge(idDeterminacion);
            }
            em.remove(contratodeterminacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contratodeterminacion> findContratodeterminacionEntities() {
        return findContratodeterminacionEntities(true, -1, -1);
    }

    public List<Contratodeterminacion> findContratodeterminacionEntities(int maxResults, int firstResult) {
        return findContratodeterminacionEntities(false, maxResults, firstResult);
    }

    private List<Contratodeterminacion> findContratodeterminacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratodeterminacion.class));
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

    public Contratodeterminacion findContratodeterminacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratodeterminacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratodeterminacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratodeterminacion> rt = cq.from(Contratodeterminacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
