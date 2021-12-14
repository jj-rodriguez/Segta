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
import segta.clases.Contrato;
import segta.clases.Lote;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Lotecontrato;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author Ruso
 */
public class LotecontratoJpaController implements Serializable {

    public LotecontratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lotecontrato lotecontrato) {
        if (lotecontrato.getLoteCollection() == null) {
            lotecontrato.setLoteCollection(new ArrayList<Lote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato idContrato = lotecontrato.getIdContrato();
            if (idContrato != null) {
                idContrato = em.getReference(idContrato.getClass(), idContrato.getIdContrato());
                lotecontrato.setIdContrato(idContrato);
            }
            Collection<Lote> attachedLoteCollection = new ArrayList<Lote>();
            for (Lote loteCollectionLoteToAttach : lotecontrato.getLoteCollection()) {
                loteCollectionLoteToAttach = em.getReference(loteCollectionLoteToAttach.getClass(), loteCollectionLoteToAttach.getIdLote());
                attachedLoteCollection.add(loteCollectionLoteToAttach);
            }
            lotecontrato.setLoteCollection(attachedLoteCollection);
            em.persist(lotecontrato);
            if (idContrato != null) {
                idContrato.getLotecontratoCollection().add(lotecontrato);
                idContrato = em.merge(idContrato);
            }
            for (Lote loteCollectionLote : lotecontrato.getLoteCollection()) {
                Lotecontrato oldIdLoteContratoOfLoteCollectionLote = loteCollectionLote.getIdLoteContrato();
                loteCollectionLote.setIdLoteContrato(lotecontrato);
                loteCollectionLote = em.merge(loteCollectionLote);
                if (oldIdLoteContratoOfLoteCollectionLote != null) {
                    oldIdLoteContratoOfLoteCollectionLote.getLoteCollection().remove(loteCollectionLote);
                    oldIdLoteContratoOfLoteCollectionLote = em.merge(oldIdLoteContratoOfLoteCollectionLote);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lotecontrato lotecontrato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lotecontrato persistentLotecontrato = em.find(Lotecontrato.class, lotecontrato.getIdloteContrato());
            Contrato idContratoOld = persistentLotecontrato.getIdContrato();
            Contrato idContratoNew = lotecontrato.getIdContrato();
            Collection<Lote> loteCollectionOld = persistentLotecontrato.getLoteCollection();
            Collection<Lote> loteCollectionNew = lotecontrato.getLoteCollection();
            if (idContratoNew != null) {
                idContratoNew = em.getReference(idContratoNew.getClass(), idContratoNew.getIdContrato());
                lotecontrato.setIdContrato(idContratoNew);
            }
            Collection<Lote> attachedLoteCollectionNew = new ArrayList<Lote>();
            for (Lote loteCollectionNewLoteToAttach : loteCollectionNew) {
                loteCollectionNewLoteToAttach = em.getReference(loteCollectionNewLoteToAttach.getClass(), loteCollectionNewLoteToAttach.getIdLote());
                attachedLoteCollectionNew.add(loteCollectionNewLoteToAttach);
            }
            loteCollectionNew = attachedLoteCollectionNew;
            lotecontrato.setLoteCollection(loteCollectionNew);
            lotecontrato = em.merge(lotecontrato);
            if (idContratoOld != null && !idContratoOld.equals(idContratoNew)) {
                idContratoOld.getLotecontratoCollection().remove(lotecontrato);
                idContratoOld = em.merge(idContratoOld);
            }
            if (idContratoNew != null && !idContratoNew.equals(idContratoOld)) {
                idContratoNew.getLotecontratoCollection().add(lotecontrato);
                idContratoNew = em.merge(idContratoNew);
            }
            for (Lote loteCollectionOldLote : loteCollectionOld) {
                if (!loteCollectionNew.contains(loteCollectionOldLote)) {
                    loteCollectionOldLote.setIdLoteContrato(null);
                    loteCollectionOldLote = em.merge(loteCollectionOldLote);
                }
            }
            for (Lote loteCollectionNewLote : loteCollectionNew) {
                if (!loteCollectionOld.contains(loteCollectionNewLote)) {
                    Lotecontrato oldIdLoteContratoOfLoteCollectionNewLote = loteCollectionNewLote.getIdLoteContrato();
                    loteCollectionNewLote.setIdLoteContrato(lotecontrato);
                    loteCollectionNewLote = em.merge(loteCollectionNewLote);
                    if (oldIdLoteContratoOfLoteCollectionNewLote != null && !oldIdLoteContratoOfLoteCollectionNewLote.equals(lotecontrato)) {
                        oldIdLoteContratoOfLoteCollectionNewLote.getLoteCollection().remove(loteCollectionNewLote);
                        oldIdLoteContratoOfLoteCollectionNewLote = em.merge(oldIdLoteContratoOfLoteCollectionNewLote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lotecontrato.getIdloteContrato();
                if (findLotecontrato(id) == null) {
                    throw new NonexistentEntityException("The lotecontrato with id " + id + " no longer exists.");
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
            Lotecontrato lotecontrato;
            try {
                lotecontrato = em.getReference(Lotecontrato.class, id);
                lotecontrato.getIdloteContrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lotecontrato with id " + id + " no longer exists.", enfe);
            }
            Contrato idContrato = lotecontrato.getIdContrato();
            if (idContrato != null) {
                idContrato.getLotecontratoCollection().remove(lotecontrato);
                idContrato = em.merge(idContrato);
            }
            Collection<Lote> loteCollection = lotecontrato.getLoteCollection();
            for (Lote loteCollectionLote : loteCollection) {
                loteCollectionLote.setIdLoteContrato(null);
                loteCollectionLote = em.merge(loteCollectionLote);
            }
            em.remove(lotecontrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lotecontrato> findLotecontratoEntities() {
        return findLotecontratoEntities(true, -1, -1);
    }

    public List<Lotecontrato> findLotecontratoEntities(int maxResults, int firstResult) {
        return findLotecontratoEntities(false, maxResults, firstResult);
    }

    private List<Lotecontrato> findLotecontratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lotecontrato.class));
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

    public Lotecontrato findLotecontrato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lotecontrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getLotecontratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lotecontrato> rt = cq.from(Lotecontrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
