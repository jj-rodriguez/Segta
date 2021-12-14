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
import segta.clases.Lotecontrato;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Contrato;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author Ruso
 */
public class ContratoJpaController implements Serializable {

    public ContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contrato contrato) {
        if (contrato.getLotecontratoCollection() == null) {
            contrato.setLotecontratoCollection(new ArrayList<Lotecontrato>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Lotecontrato> attachedLotecontratoCollection = new ArrayList<Lotecontrato>();
            for (Lotecontrato lotecontratoCollectionLotecontratoToAttach : contrato.getLotecontratoCollection()) {
                lotecontratoCollectionLotecontratoToAttach = em.getReference(lotecontratoCollectionLotecontratoToAttach.getClass(), lotecontratoCollectionLotecontratoToAttach.getIdloteContrato());
                attachedLotecontratoCollection.add(lotecontratoCollectionLotecontratoToAttach);
            }
            contrato.setLotecontratoCollection(attachedLotecontratoCollection);
            em.persist(contrato);
            for (Lotecontrato lotecontratoCollectionLotecontrato : contrato.getLotecontratoCollection()) {
                Contrato oldIdContratoOfLotecontratoCollectionLotecontrato = lotecontratoCollectionLotecontrato.getIdContrato();
                lotecontratoCollectionLotecontrato.setIdContrato(contrato);
                lotecontratoCollectionLotecontrato = em.merge(lotecontratoCollectionLotecontrato);
                if (oldIdContratoOfLotecontratoCollectionLotecontrato != null) {
                    oldIdContratoOfLotecontratoCollectionLotecontrato.getLotecontratoCollection().remove(lotecontratoCollectionLotecontrato);
                    oldIdContratoOfLotecontratoCollectionLotecontrato = em.merge(oldIdContratoOfLotecontratoCollectionLotecontrato);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contrato contrato) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato persistentContrato = em.find(Contrato.class, contrato.getIdContrato());
            Collection<Lotecontrato> lotecontratoCollectionOld = persistentContrato.getLotecontratoCollection();
            Collection<Lotecontrato> lotecontratoCollectionNew = contrato.getLotecontratoCollection();
            List<String> illegalOrphanMessages = null;
            for (Lotecontrato lotecontratoCollectionOldLotecontrato : lotecontratoCollectionOld) {
                if (!lotecontratoCollectionNew.contains(lotecontratoCollectionOldLotecontrato)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lotecontrato " + lotecontratoCollectionOldLotecontrato + " since its idContrato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Lotecontrato> attachedLotecontratoCollectionNew = new ArrayList<Lotecontrato>();
            for (Lotecontrato lotecontratoCollectionNewLotecontratoToAttach : lotecontratoCollectionNew) {
                lotecontratoCollectionNewLotecontratoToAttach = em.getReference(lotecontratoCollectionNewLotecontratoToAttach.getClass(), lotecontratoCollectionNewLotecontratoToAttach.getIdloteContrato());
                attachedLotecontratoCollectionNew.add(lotecontratoCollectionNewLotecontratoToAttach);
            }
            lotecontratoCollectionNew = attachedLotecontratoCollectionNew;
            contrato.setLotecontratoCollection(lotecontratoCollectionNew);
            contrato = em.merge(contrato);
            for (Lotecontrato lotecontratoCollectionNewLotecontrato : lotecontratoCollectionNew) {
                if (!lotecontratoCollectionOld.contains(lotecontratoCollectionNewLotecontrato)) {
                    Contrato oldIdContratoOfLotecontratoCollectionNewLotecontrato = lotecontratoCollectionNewLotecontrato.getIdContrato();
                    lotecontratoCollectionNewLotecontrato.setIdContrato(contrato);
                    lotecontratoCollectionNewLotecontrato = em.merge(lotecontratoCollectionNewLotecontrato);
                    if (oldIdContratoOfLotecontratoCollectionNewLotecontrato != null && !oldIdContratoOfLotecontratoCollectionNewLotecontrato.equals(contrato)) {
                        oldIdContratoOfLotecontratoCollectionNewLotecontrato.getLotecontratoCollection().remove(lotecontratoCollectionNewLotecontrato);
                        oldIdContratoOfLotecontratoCollectionNewLotecontrato = em.merge(oldIdContratoOfLotecontratoCollectionNewLotecontrato);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contrato.getIdContrato();
                if (findContrato(id) == null) {
                    throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getIdContrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Lotecontrato> lotecontratoCollectionOrphanCheck = contrato.getLotecontratoCollection();
            for (Lotecontrato lotecontratoCollectionOrphanCheckLotecontrato : lotecontratoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contrato (" + contrato + ") cannot be destroyed since the Lotecontrato " + lotecontratoCollectionOrphanCheckLotecontrato + " in its lotecontratoCollection field has a non-nullable idContrato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contrato> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contrato> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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

    public Contrato findContrato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contrato> rt = cq.from(Contrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
