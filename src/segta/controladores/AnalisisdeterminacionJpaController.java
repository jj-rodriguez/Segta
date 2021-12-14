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
import segta.clases.Analisis;
import segta.clases.Analisisdeterminacion;
import segta.clases.Determinacion;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class AnalisisdeterminacionJpaController implements Serializable {

    public AnalisisdeterminacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Analisisdeterminacion analisisdeterminacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analisis idAnalisis = analisisdeterminacion.getIdAnalisis();
            if (idAnalisis != null) {
                idAnalisis = em.getReference(idAnalisis.getClass(), idAnalisis.getIdAnalisis());
                analisisdeterminacion.setIdAnalisis(idAnalisis);
            }
            Determinacion idDeterminacion = analisisdeterminacion.getIdDeterminacion();
            if (idDeterminacion != null) {
                idDeterminacion = em.getReference(idDeterminacion.getClass(), idDeterminacion.getIdDeterminacion());
                analisisdeterminacion.setIdDeterminacion(idDeterminacion);
            }
            em.persist(analisisdeterminacion);
            if (idAnalisis != null) {
                idAnalisis.getAnalisisdeterminacionCollection().add(analisisdeterminacion);
                idAnalisis = em.merge(idAnalisis);
            }
            if (idDeterminacion != null) {
                idDeterminacion.getAnalisisdeterminacionCollection().add(analisisdeterminacion);
                idDeterminacion = em.merge(idDeterminacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Analisisdeterminacion analisisdeterminacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analisisdeterminacion persistentAnalisisdeterminacion = em.find(Analisisdeterminacion.class, analisisdeterminacion.getIdAnalisisDeterminacion());
            Analisis idAnalisisOld = persistentAnalisisdeterminacion.getIdAnalisis();
            Analisis idAnalisisNew = analisisdeterminacion.getIdAnalisis();
            Determinacion idDeterminacionOld = persistentAnalisisdeterminacion.getIdDeterminacion();
            Determinacion idDeterminacionNew = analisisdeterminacion.getIdDeterminacion();
            if (idAnalisisNew != null) {
                idAnalisisNew = em.getReference(idAnalisisNew.getClass(), idAnalisisNew.getIdAnalisis());
                analisisdeterminacion.setIdAnalisis(idAnalisisNew);
            }
            if (idDeterminacionNew != null) {
                idDeterminacionNew = em.getReference(idDeterminacionNew.getClass(), idDeterminacionNew.getIdDeterminacion());
                analisisdeterminacion.setIdDeterminacion(idDeterminacionNew);
            }
            analisisdeterminacion = em.merge(analisisdeterminacion);
            if (idAnalisisOld != null && !idAnalisisOld.equals(idAnalisisNew)) {
                idAnalisisOld.getAnalisisdeterminacionCollection().remove(analisisdeterminacion);
                idAnalisisOld = em.merge(idAnalisisOld);
            }
            if (idAnalisisNew != null && !idAnalisisNew.equals(idAnalisisOld)) {
                idAnalisisNew.getAnalisisdeterminacionCollection().add(analisisdeterminacion);
                idAnalisisNew = em.merge(idAnalisisNew);
            }
            if (idDeterminacionOld != null && !idDeterminacionOld.equals(idDeterminacionNew)) {
                idDeterminacionOld.getAnalisisdeterminacionCollection().remove(analisisdeterminacion);
                idDeterminacionOld = em.merge(idDeterminacionOld);
            }
            if (idDeterminacionNew != null && !idDeterminacionNew.equals(idDeterminacionOld)) {
                idDeterminacionNew.getAnalisisdeterminacionCollection().add(analisisdeterminacion);
                idDeterminacionNew = em.merge(idDeterminacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analisisdeterminacion.getIdAnalisisDeterminacion();
                if (findAnalisisdeterminacion(id) == null) {
                    throw new NonexistentEntityException("The analisisdeterminacion with id " + id + " no longer exists.");
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
            Analisisdeterminacion analisisdeterminacion;
            try {
                analisisdeterminacion = em.getReference(Analisisdeterminacion.class, id);
                analisisdeterminacion.getIdAnalisisDeterminacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analisisdeterminacion with id " + id + " no longer exists.", enfe);
            }
            Analisis idAnalisis = analisisdeterminacion.getIdAnalisis();
            if (idAnalisis != null) {
                idAnalisis.getAnalisisdeterminacionCollection().remove(analisisdeterminacion);
                idAnalisis = em.merge(idAnalisis);
            }
            Determinacion idDeterminacion = analisisdeterminacion.getIdDeterminacion();
            if (idDeterminacion != null) {
                idDeterminacion.getAnalisisdeterminacionCollection().remove(analisisdeterminacion);
                idDeterminacion = em.merge(idDeterminacion);
            }
            em.remove(analisisdeterminacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Analisisdeterminacion> findAnalisisdeterminacionEntities() {
        return findAnalisisdeterminacionEntities(true, -1, -1);
    }

    public List<Analisisdeterminacion> findAnalisisdeterminacionEntities(int maxResults, int firstResult) {
        return findAnalisisdeterminacionEntities(false, maxResults, firstResult);
    }

    private List<Analisisdeterminacion> findAnalisisdeterminacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Analisisdeterminacion.class));
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

    public Analisisdeterminacion findAnalisisdeterminacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Analisisdeterminacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalisisdeterminacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Analisisdeterminacion> rt = cq.from(Analisisdeterminacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
