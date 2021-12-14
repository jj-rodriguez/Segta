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
import segta.clases.Color;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class ColorJpaController implements Serializable {

    public ColorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Color color) {
        if (color.getTamborCollection() == null) {
            color.setTamborCollection(new ArrayList<Tambor>());
        }

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tambor> attachedTamborCollection = new ArrayList<Tambor>();
            for (Tambor tamborCollectionTamborToAttach : color.getTamborCollection()) {
                tamborCollectionTamborToAttach = em.getReference(tamborCollectionTamborToAttach.getClass(), tamborCollectionTamborToAttach.getIdTambor());
                attachedTamborCollection.add(tamborCollectionTamborToAttach);
            }
            color.setTamborCollection(attachedTamborCollection);
            em.persist(color);
            for (Tambor tamborCollectionTambor : color.getTamborCollection()) {
                Color oldIdColorOfTamborCollectionTambor = tamborCollectionTambor.getIdColor();
                tamborCollectionTambor.setIdColor(color);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
                if (oldIdColorOfTamborCollectionTambor != null) {
                    oldIdColorOfTamborCollectionTambor.getTamborCollection().remove(tamborCollectionTambor);
                    oldIdColorOfTamborCollectionTambor = em.merge(oldIdColorOfTamborCollectionTambor);
                }
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Color color) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Color persistentColor = em.find(Color.class, color.getIdColor());
            Collection<Tambor> tamborCollectionOld = persistentColor.getTamborCollection();
            Collection<Tambor> tamborCollectionNew = color.getTamborCollection();
            Collection<Tambor> attachedTamborCollectionNew = new ArrayList<Tambor>();
            for (Tambor tamborCollectionNewTamborToAttach : tamborCollectionNew) {
                tamborCollectionNewTamborToAttach = em.getReference(tamborCollectionNewTamborToAttach.getClass(), tamborCollectionNewTamborToAttach.getIdTambor());
                attachedTamborCollectionNew.add(tamborCollectionNewTamborToAttach);
            }
            tamborCollectionNew = attachedTamborCollectionNew;
            color.setTamborCollection(tamborCollectionNew);

            color = em.merge(color);
            for (Tambor tamborCollectionOldTambor : tamborCollectionOld) {
                if (!tamborCollectionNew.contains(tamborCollectionOldTambor)) {
                    tamborCollectionOldTambor.setIdColor(null);
                    tamborCollectionOldTambor = em.merge(tamborCollectionOldTambor);
                }
            }
            for (Tambor tamborCollectionNewTambor : tamborCollectionNew) {
                if (!tamborCollectionOld.contains(tamborCollectionNewTambor)) {
                    Color oldIdColorOfTamborCollectionNewTambor = tamborCollectionNewTambor.getIdColor();
                    tamborCollectionNewTambor.setIdColor(color);
                    tamborCollectionNewTambor = em.merge(tamborCollectionNewTambor);
                    if (oldIdColorOfTamborCollectionNewTambor != null && !oldIdColorOfTamborCollectionNewTambor.equals(color)) {
                        oldIdColorOfTamborCollectionNewTambor.getTamborCollection().remove(tamborCollectionNewTambor);
                        oldIdColorOfTamborCollectionNewTambor = em.merge(oldIdColorOfTamborCollectionNewTambor);
                    }
                }
            }
           
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = color.getIdColor();
                if (findColor(id) == null) {
                    throw new NonexistentEntityException("The color with id " + id + " no longer exists.");
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
            Color color;
            try {
                color = em.getReference(Color.class, id);
                color.getIdColor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The color with id " + id + " no longer exists.", enfe);
            }
            Collection<Tambor> tamborCollection = color.getTamborCollection();
            for (Tambor tamborCollectionTambor : tamborCollection) {
                tamborCollectionTambor.setIdColor(null);
                tamborCollectionTambor = em.merge(tamborCollectionTambor);
            }
 
            em.remove(color);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Color> findColorEntities() {
        return findColorEntities(true, -1, -1);
    }

    public List<Color> findColorEntities(int maxResults, int firstResult) {
        return findColorEntities(false, maxResults, firstResult);
    }

    private List<Color> findColorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Color.class));
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

    public Color findColor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Color.class, id);
        } finally {
            em.close();
        }
    }

    public int getColorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Color> rt = cq.from(Color.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
