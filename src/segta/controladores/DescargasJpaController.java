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
import segta.clases.Descargas;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class DescargasJpaController implements Serializable {

    public DescargasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Descargas descargas) {
        if (descargas.getTamborCollection() == null) {
            descargas.setTamborCollection(new ArrayList<Tambor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tambor> attachedTamborCollection = new ArrayList<Tambor>();
            for (Tambor tamborCollectionTamborToAttach : descargas.getTamborCollection()) {
                tamborCollectionTamborToAttach = em.getReference(tamborCollectionTamborToAttach.getClass(), tamborCollectionTamborToAttach.getIdTambor());
                attachedTamborCollection.add(tamborCollectionTamborToAttach);
            }
            descargas.setTamborCollection(attachedTamborCollection);
            em.persist(descargas);
            for (Tambor tamborCollectionTambor : descargas.getTamborCollection()) {
                Descargas oldIdDescargaOfTamborCollectionTambor = tamborCollectionTambor.getIdDescarga();
                tamborCollectionTambor.setIdDescarga(descargas);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
                if (oldIdDescargaOfTamborCollectionTambor != null) {
                    oldIdDescargaOfTamborCollectionTambor.getTamborCollection().remove(tamborCollectionTambor);
                    oldIdDescargaOfTamborCollectionTambor = em.merge(oldIdDescargaOfTamborCollectionTambor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Descargas descargas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Descargas persistentDescargas = em.find(Descargas.class, descargas.getIdDescargas());
            Collection<Tambor> tamborCollectionOld = persistentDescargas.getTamborCollection();
            Collection<Tambor> tamborCollectionNew = descargas.getTamborCollection();
            List<String> illegalOrphanMessages = null;
            for (Tambor tamborCollectionOldTambor : tamborCollectionOld) {
                if (!tamborCollectionNew.contains(tamborCollectionOldTambor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tambor " + tamborCollectionOldTambor + " since its idDescarga field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tambor> attachedTamborCollectionNew = new ArrayList<Tambor>();
            for (Tambor tamborCollectionNewTamborToAttach : tamborCollectionNew) {
                tamborCollectionNewTamborToAttach = em.getReference(tamborCollectionNewTamborToAttach.getClass(), tamborCollectionNewTamborToAttach.getIdTambor());
                attachedTamborCollectionNew.add(tamborCollectionNewTamborToAttach);
            }
            tamborCollectionNew = attachedTamborCollectionNew;
            descargas.setTamborCollection(tamborCollectionNew);
            descargas = em.merge(descargas);
            for (Tambor tamborCollectionNewTambor : tamborCollectionNew) {
                if (!tamborCollectionOld.contains(tamborCollectionNewTambor)) {
                    Descargas oldIdDescargaOfTamborCollectionNewTambor = tamborCollectionNewTambor.getIdDescarga();
                    tamborCollectionNewTambor.setIdDescarga(descargas);
                    tamborCollectionNewTambor = em.merge(tamborCollectionNewTambor);
                    if (oldIdDescargaOfTamborCollectionNewTambor != null && !oldIdDescargaOfTamborCollectionNewTambor.equals(descargas)) {
                        oldIdDescargaOfTamborCollectionNewTambor.getTamborCollection().remove(tamborCollectionNewTambor);
                        oldIdDescargaOfTamborCollectionNewTambor = em.merge(oldIdDescargaOfTamborCollectionNewTambor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descargas.getIdDescargas();
                if (findDescargas(id) == null) {
                    throw new NonexistentEntityException("The descargas with id " + id + " no longer exists.");
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
            Descargas descargas;
            try {
                descargas = em.getReference(Descargas.class, id);
                descargas.getIdDescargas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descargas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tambor> tamborCollectionOrphanCheck = descargas.getTamborCollection();
            for (Tambor tamborCollectionOrphanCheckTambor : tamborCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Descargas (" + descargas + ") cannot be destroyed since the Tambor " + tamborCollectionOrphanCheckTambor + " in its tamborCollection field has a non-nullable idDescarga field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(descargas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Descargas> findDescargasEntities() {
        return findDescargasEntities(true, -1, -1);
    }

    public List<Descargas> findDescargasEntities(int maxResults, int firstResult) {
        return findDescargasEntities(false, maxResults, firstResult);
    }

    private List<Descargas> findDescargasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Descargas.class));
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

    public Descargas findDescargas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Descargas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescargasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Descargas> rt = cq.from(Descargas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
