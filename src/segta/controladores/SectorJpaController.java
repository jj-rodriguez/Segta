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
import segta.clases.Sector;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class SectorJpaController implements Serializable {

    public SectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sector sector) {
        if (sector.getTamborCollection() == null) {
            sector.setTamborCollection(new ArrayList<Tambor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tambor> attachedTamborCollection = new ArrayList<Tambor>();
            for (Tambor tamborCollectionTamborToAttach : sector.getTamborCollection()) {
                tamborCollectionTamborToAttach = em.getReference(tamborCollectionTamborToAttach.getClass(), tamborCollectionTamborToAttach.getIdTambor());
                attachedTamborCollection.add(tamborCollectionTamborToAttach);
            }
            sector.setTamborCollection(attachedTamborCollection);
            em.persist(sector);
            for (Tambor tamborCollectionTambor : sector.getTamborCollection()) {
                Sector oldIdSectorOfTamborCollectionTambor = tamborCollectionTambor.getIdSector();
                tamborCollectionTambor.setIdSector(sector);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
                if (oldIdSectorOfTamborCollectionTambor != null) {
                    oldIdSectorOfTamborCollectionTambor.getTamborCollection().remove(tamborCollectionTambor);
                    oldIdSectorOfTamborCollectionTambor = em.merge(oldIdSectorOfTamborCollectionTambor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sector sector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sector persistentSector = em.find(Sector.class, sector.getIdSector());
            Collection<Tambor> tamborCollectionOld = persistentSector.getTamborCollection();
            Collection<Tambor> tamborCollectionNew = sector.getTamborCollection();
            Collection<Tambor> attachedTamborCollectionNew = new ArrayList<Tambor>();
            for (Tambor tamborCollectionNewTamborToAttach : tamborCollectionNew) {
                tamborCollectionNewTamborToAttach = em.getReference(tamborCollectionNewTamborToAttach.getClass(), tamborCollectionNewTamborToAttach.getIdTambor());
                attachedTamborCollectionNew.add(tamborCollectionNewTamborToAttach);
            }
            tamborCollectionNew = attachedTamborCollectionNew;
            sector.setTamborCollection(tamborCollectionNew);
            sector = em.merge(sector);
            for (Tambor tamborCollectionOldTambor : tamborCollectionOld) {
                if (!tamborCollectionNew.contains(tamborCollectionOldTambor)) {
                    tamborCollectionOldTambor.setIdSector(null);
                    tamborCollectionOldTambor = em.merge(tamborCollectionOldTambor);
                }
            }
            for (Tambor tamborCollectionNewTambor : tamborCollectionNew) {
                if (!tamborCollectionOld.contains(tamborCollectionNewTambor)) {
                    Sector oldIdSectorOfTamborCollectionNewTambor = tamborCollectionNewTambor.getIdSector();
                    tamborCollectionNewTambor.setIdSector(sector);
                    tamborCollectionNewTambor = em.merge(tamborCollectionNewTambor);
                    if (oldIdSectorOfTamborCollectionNewTambor != null && !oldIdSectorOfTamborCollectionNewTambor.equals(sector)) {
                        oldIdSectorOfTamborCollectionNewTambor.getTamborCollection().remove(tamborCollectionNewTambor);
                        oldIdSectorOfTamborCollectionNewTambor = em.merge(oldIdSectorOfTamborCollectionNewTambor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sector.getIdSector();
                if (findSector(id) == null) {
                    throw new NonexistentEntityException("The sector with id " + id + " no longer exists.");
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
            Sector sector;
            try {
                sector = em.getReference(Sector.class, id);
                sector.getIdSector();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sector with id " + id + " no longer exists.", enfe);
            }
            Collection<Tambor> tamborCollection = sector.getTamborCollection();
            for (Tambor tamborCollectionTambor : tamborCollection) {
                tamborCollectionTambor.setIdSector(null);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
            }
            em.remove(sector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sector> findSectorEntities() {
        return findSectorEntities(true, -1, -1);
    }

    public List<Sector> findSectorEntities(int maxResults, int firstResult) {
        return findSectorEntities(false, maxResults, firstResult);
    }

    private List<Sector> findSectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sector.class));
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

    public Sector findSector(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sector.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sector> rt = cq.from(Sector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
