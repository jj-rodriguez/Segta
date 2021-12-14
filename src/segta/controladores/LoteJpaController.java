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
import segta.clases.Lote;
import segta.clases.Lotecontrato;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author Ruso
 */
public class LoteJpaController implements Serializable {

    public LoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lote lote) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lotecontrato idLoteContrato = lote.getIdLoteContrato();
            if (idLoteContrato != null) {
                idLoteContrato = em.getReference(idLoteContrato.getClass(), idLoteContrato.getIdloteContrato());
                lote.setIdLoteContrato(idLoteContrato);
            }
            em.persist(lote);
            if (idLoteContrato != null) {
                idLoteContrato.getLoteCollection().add(lote);
                idLoteContrato = em.merge(idLoteContrato);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lote lote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote persistentLote = em.find(Lote.class, lote.getIdLote());
            Lotecontrato idLoteContratoOld = persistentLote.getIdLoteContrato();
            Lotecontrato idLoteContratoNew = lote.getIdLoteContrato();
            if (idLoteContratoNew != null) {
                idLoteContratoNew = em.getReference(idLoteContratoNew.getClass(), idLoteContratoNew.getIdloteContrato());
                lote.setIdLoteContrato(idLoteContratoNew);
            }
            lote = em.merge(lote);
            if (idLoteContratoOld != null && !idLoteContratoOld.equals(idLoteContratoNew)) {
                idLoteContratoOld.getLoteCollection().remove(lote);
                idLoteContratoOld = em.merge(idLoteContratoOld);
            }
            if (idLoteContratoNew != null && !idLoteContratoNew.equals(idLoteContratoOld)) {
                idLoteContratoNew.getLoteCollection().add(lote);
                idLoteContratoNew = em.merge(idLoteContratoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lote.getIdLote();
                if (findLote(id) == null) {
                    throw new NonexistentEntityException("The lote with id " + id + " no longer exists.");
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
            Lote lote;
            try {
                lote = em.getReference(Lote.class, id);
                lote.getIdLote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lote with id " + id + " no longer exists.", enfe);
            }
            Lotecontrato idLoteContrato = lote.getIdLoteContrato();
            if (idLoteContrato != null) {
                idLoteContrato.getLoteCollection().remove(lote);
                idLoteContrato = em.merge(idLoteContrato);
            }
            em.remove(lote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lote> findLoteEntities() {
        return findLoteEntities(true, -1, -1);
    }

    public List<Lote> findLoteEntities(int maxResults, int firstResult) {
        return findLoteEntities(false, maxResults, firstResult);
    }

    private List<Lote> findLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lote.class));
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

    public Lote findLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lote> rt = cq.from(Lote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
