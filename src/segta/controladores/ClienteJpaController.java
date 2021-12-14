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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Cliente;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getContratoCollection() == null) {
            cliente.setContratoCollection(new ArrayList<Contrato>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Contrato> attachedContratoCollection = new ArrayList<Contrato>();
            for (Contrato contratoCollectionContratoToAttach : cliente.getContratoCollection()) {
                contratoCollectionContratoToAttach = em.getReference(contratoCollectionContratoToAttach.getClass(), contratoCollectionContratoToAttach.getIdContrato());
                attachedContratoCollection.add(contratoCollectionContratoToAttach);
            }
            cliente.setContratoCollection(attachedContratoCollection);
            em.persist(cliente);
            for (Contrato contratoCollectionContrato : cliente.getContratoCollection()) {
                Cliente oldIdClienteOfContratoCollectionContrato = contratoCollectionContrato.getIdCliente();
                contratoCollectionContrato.setIdCliente(cliente);
                contratoCollectionContrato = em.merge(contratoCollectionContrato);
                if (oldIdClienteOfContratoCollectionContrato != null) {
                    oldIdClienteOfContratoCollectionContrato.getContratoCollection().remove(contratoCollectionContrato);
                    oldIdClienteOfContratoCollectionContrato = em.merge(oldIdClienteOfContratoCollectionContrato);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Collection<Contrato> contratoCollectionOld = persistentCliente.getContratoCollection();
            Collection<Contrato> contratoCollectionNew = cliente.getContratoCollection();
            Collection<Contrato> attachedContratoCollectionNew = new ArrayList<Contrato>();
            for (Contrato contratoCollectionNewContratoToAttach : contratoCollectionNew) {
                contratoCollectionNewContratoToAttach = em.getReference(contratoCollectionNewContratoToAttach.getClass(), contratoCollectionNewContratoToAttach.getIdContrato());
                attachedContratoCollectionNew.add(contratoCollectionNewContratoToAttach);
            }
            contratoCollectionNew = attachedContratoCollectionNew;
            cliente.setContratoCollection(contratoCollectionNew);
            cliente = em.merge(cliente);
            for (Contrato contratoCollectionOldContrato : contratoCollectionOld) {
                if (!contratoCollectionNew.contains(contratoCollectionOldContrato)) {
                    contratoCollectionOldContrato.setIdCliente(null);
                    contratoCollectionOldContrato = em.merge(contratoCollectionOldContrato);
                }
            }
            for (Contrato contratoCollectionNewContrato : contratoCollectionNew) {
                if (!contratoCollectionOld.contains(contratoCollectionNewContrato)) {
                    Cliente oldIdClienteOfContratoCollectionNewContrato = contratoCollectionNewContrato.getIdCliente();
                    contratoCollectionNewContrato.setIdCliente(cliente);
                    contratoCollectionNewContrato = em.merge(contratoCollectionNewContrato);
                    if (oldIdClienteOfContratoCollectionNewContrato != null && !oldIdClienteOfContratoCollectionNewContrato.equals(cliente)) {
                        oldIdClienteOfContratoCollectionNewContrato.getContratoCollection().remove(contratoCollectionNewContrato);
                        oldIdClienteOfContratoCollectionNewContrato = em.merge(oldIdClienteOfContratoCollectionNewContrato);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Collection<Contrato> contratoCollection = cliente.getContratoCollection();
            for (Contrato contratoCollectionContrato : contratoCollection) {
                contratoCollectionContrato.setIdCliente(null);
                contratoCollectionContrato = em.merge(contratoCollectionContrato);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
