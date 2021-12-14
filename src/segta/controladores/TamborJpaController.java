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
import segta.clases.Color;
import segta.clases.Descargas;
import segta.clases.Lote;
import segta.clases.Sector;
import segta.clases.Tambor;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class TamborJpaController implements Serializable {

    public TamborJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tambor tambor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Color idColor = tambor.getIdColor();
            if (idColor != null) {
                idColor = em.getReference(idColor.getClass(), idColor.getIdColor());
                tambor.setIdColor(idColor);
            }
            Descargas idDescarga = tambor.getIdDescarga();
            if (idDescarga != null) {
                idDescarga = em.getReference(idDescarga.getClass(), idDescarga.getIdDescargas());
                tambor.setIdDescarga(idDescarga);
            }
            Lote idLote = tambor.getIdLote();
            if (idLote != null) {
                idLote = em.getReference(idLote.getClass(), idLote.getIdLote());
                tambor.setIdLote(idLote);
            }
            Sector idSector = tambor.getIdSector();
            if (idSector != null) {
                idSector = em.getReference(idSector.getClass(), idSector.getIdSector());
                tambor.setIdSector(idSector);
            }
            em.persist(tambor);
            if (idColor != null) {
                idColor.getTamborCollection().add(tambor);
                idColor = em.merge(idColor);
            }
            if (idDescarga != null) {
                idDescarga.getTamborCollection().add(tambor);
                idDescarga = em.merge(idDescarga);
            }
            if (idLote != null) {
                idLote.getTamborCollection().add(tambor);
                idLote = em.merge(idLote);
            }
            if (idSector != null) {
                idSector.getTamborCollection().add(tambor);
                idSector = em.merge(idSector);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tambor tambor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tambor persistentTambor = em.find(Tambor.class, tambor.getIdTambor());
            Color idColorOld = persistentTambor.getIdColor();
            Color idColorNew = tambor.getIdColor();
            Descargas idDescargaOld = persistentTambor.getIdDescarga();
            Descargas idDescargaNew = tambor.getIdDescarga();
            Lote idLoteOld = persistentTambor.getIdLote();
            Lote idLoteNew = tambor.getIdLote();
            Sector idSectorOld = persistentTambor.getIdSector();
            Sector idSectorNew = tambor.getIdSector();
            if (idColorNew != null) {
                idColorNew = em.getReference(idColorNew.getClass(), idColorNew.getIdColor());
                tambor.setIdColor(idColorNew);
            }
            if (idDescargaNew != null) {
                idDescargaNew = em.getReference(idDescargaNew.getClass(), idDescargaNew.getIdDescargas());
                tambor.setIdDescarga(idDescargaNew);
            }
            if (idLoteNew != null) {
                idLoteNew = em.getReference(idLoteNew.getClass(), idLoteNew.getIdLote());
                tambor.setIdLote(idLoteNew);
            }
            if (idSectorNew != null) {
                idSectorNew = em.getReference(idSectorNew.getClass(), idSectorNew.getIdSector());
                tambor.setIdSector(idSectorNew);
            }
            tambor = em.merge(tambor);
            if (idColorOld != null && !idColorOld.equals(idColorNew)) {
                idColorOld.getTamborCollection().remove(tambor);
                idColorOld = em.merge(idColorOld);
            }
            if (idColorNew != null && !idColorNew.equals(idColorOld)) {
                idColorNew.getTamborCollection().add(tambor);
                idColorNew = em.merge(idColorNew);
            }
            if (idDescargaOld != null && !idDescargaOld.equals(idDescargaNew)) {
                idDescargaOld.getTamborCollection().remove(tambor);
                idDescargaOld = em.merge(idDescargaOld);
            }
            if (idDescargaNew != null && !idDescargaNew.equals(idDescargaOld)) {
                idDescargaNew.getTamborCollection().add(tambor);
                idDescargaNew = em.merge(idDescargaNew);
            }
            if (idLoteOld != null && !idLoteOld.equals(idLoteNew)) {
                idLoteOld.getTamborCollection().remove(tambor);
                idLoteOld = em.merge(idLoteOld);
            }
            if (idLoteNew != null && !idLoteNew.equals(idLoteOld)) {
                idLoteNew.getTamborCollection().add(tambor);
                idLoteNew = em.merge(idLoteNew);
            }
            if (idSectorOld != null && !idSectorOld.equals(idSectorNew)) {
                idSectorOld.getTamborCollection().remove(tambor);
                idSectorOld = em.merge(idSectorOld);
            }
            if (idSectorNew != null && !idSectorNew.equals(idSectorOld)) {
                idSectorNew.getTamborCollection().add(tambor);
                idSectorNew = em.merge(idSectorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tambor.getIdTambor();
                if (findTambor(id) == null) {
                    throw new NonexistentEntityException("The tambor with id " + id + " no longer exists.");
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
            Tambor tambor;
            try {
                tambor = em.getReference(Tambor.class, id);
                tambor.getIdTambor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tambor with id " + id + " no longer exists.", enfe);
            }
            Color idColor = tambor.getIdColor();
            if (idColor != null) {
                idColor.getTamborCollection().remove(tambor);
                idColor = em.merge(idColor);
            }
            Descargas idDescarga = tambor.getIdDescarga();
            if (idDescarga != null) {
                idDescarga.getTamborCollection().remove(tambor);
                idDescarga = em.merge(idDescarga);
            }
            Lote idLote = tambor.getIdLote();
            if (idLote != null) {
                idLote.getTamborCollection().remove(tambor);
                idLote = em.merge(idLote);
            }
            Sector idSector = tambor.getIdSector();
            if (idSector != null) {
                idSector.getTamborCollection().remove(tambor);
                idSector = em.merge(idSector);
            }
            em.remove(tambor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tambor> findTamborEntities() {
        return findTamborEntities(true, -1, -1);
    }

    public List<Tambor> findTamborEntities(int maxResults, int firstResult) {
        return findTamborEntities(false, maxResults, firstResult);
    }

    private List<Tambor> findTamborEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tambor.class));
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

    public Tambor findTambor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tambor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTamborCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tambor> rt = cq.from(Tambor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
