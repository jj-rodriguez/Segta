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
import segta.clases.Localidad;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Provincia;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;
import segta.controladores.exceptions.PreexistingEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) throws PreexistingEntityException, Exception {
        if (provincia.getLocalidadCollection() == null) {
            provincia.setLocalidadCollection(new ArrayList<Localidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Localidad> attachedLocalidadCollection = new ArrayList<Localidad>();
            for (Localidad localidadCollectionLocalidadToAttach : provincia.getLocalidadCollection()) {
                localidadCollectionLocalidadToAttach = em.getReference(localidadCollectionLocalidadToAttach.getClass(), localidadCollectionLocalidadToAttach.getIdLocalidad());
                attachedLocalidadCollection.add(localidadCollectionLocalidadToAttach);
            }
            provincia.setLocalidadCollection(attachedLocalidadCollection);
            em.persist(provincia);
            for (Localidad localidadCollectionLocalidad : provincia.getLocalidadCollection()) {
                Provincia oldIdProvinciaOfLocalidadCollectionLocalidad = localidadCollectionLocalidad.getIdProvincia();
                localidadCollectionLocalidad.setIdProvincia(provincia);
                localidadCollectionLocalidad = em.merge(localidadCollectionLocalidad);
                if (oldIdProvinciaOfLocalidadCollectionLocalidad != null) {
                    oldIdProvinciaOfLocalidadCollectionLocalidad.getLocalidadCollection().remove(localidadCollectionLocalidad);
                    oldIdProvinciaOfLocalidadCollectionLocalidad = em.merge(oldIdProvinciaOfLocalidadCollectionLocalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProvincia(provincia.getIdProvincia()) != null) {
                throw new PreexistingEntityException("Provincia " + provincia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdProvincia());
            Collection<Localidad> localidadCollectionOld = persistentProvincia.getLocalidadCollection();
            Collection<Localidad> localidadCollectionNew = provincia.getLocalidadCollection();
            List<String> illegalOrphanMessages = null;
            for (Localidad localidadCollectionOldLocalidad : localidadCollectionOld) {
                if (!localidadCollectionNew.contains(localidadCollectionOldLocalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Localidad " + localidadCollectionOldLocalidad + " since its idProvincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Localidad> attachedLocalidadCollectionNew = new ArrayList<Localidad>();
            for (Localidad localidadCollectionNewLocalidadToAttach : localidadCollectionNew) {
                localidadCollectionNewLocalidadToAttach = em.getReference(localidadCollectionNewLocalidadToAttach.getClass(), localidadCollectionNewLocalidadToAttach.getIdLocalidad());
                attachedLocalidadCollectionNew.add(localidadCollectionNewLocalidadToAttach);
            }
            localidadCollectionNew = attachedLocalidadCollectionNew;
            provincia.setLocalidadCollection(localidadCollectionNew);
            provincia = em.merge(provincia);
            for (Localidad localidadCollectionNewLocalidad : localidadCollectionNew) {
                if (!localidadCollectionOld.contains(localidadCollectionNewLocalidad)) {
                    Provincia oldIdProvinciaOfLocalidadCollectionNewLocalidad = localidadCollectionNewLocalidad.getIdProvincia();
                    localidadCollectionNewLocalidad.setIdProvincia(provincia);
                    localidadCollectionNewLocalidad = em.merge(localidadCollectionNewLocalidad);
                    if (oldIdProvinciaOfLocalidadCollectionNewLocalidad != null && !oldIdProvinciaOfLocalidadCollectionNewLocalidad.equals(provincia)) {
                        oldIdProvinciaOfLocalidadCollectionNewLocalidad.getLocalidadCollection().remove(localidadCollectionNewLocalidad);
                        oldIdProvinciaOfLocalidadCollectionNewLocalidad = em.merge(oldIdProvinciaOfLocalidadCollectionNewLocalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdProvincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdProvincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Localidad> localidadCollectionOrphanCheck = provincia.getLocalidadCollection();
            for (Localidad localidadCollectionOrphanCheckLocalidad : localidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Localidad " + localidadCollectionOrphanCheckLocalidad + " in its localidadCollection field has a non-nullable idProvincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
