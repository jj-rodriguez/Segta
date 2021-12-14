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
import segta.clases.Usuario;
import segta.clases.Usuarioperfil;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class UsuarioperfilJpaController implements Serializable {

    public UsuarioperfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarioperfil usuarioperfil) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = usuarioperfil.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                usuarioperfil.setIdUsuario(idUsuario);
            }
            em.persist(usuarioperfil);
            if (idUsuario != null) {
                idUsuario.getUsuarioperfilCollection().add(usuarioperfil);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarioperfil usuarioperfil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarioperfil persistentUsuarioperfil = em.find(Usuarioperfil.class, usuarioperfil.getIdUsuarioPerfil());
            Usuario idUsuarioOld = persistentUsuarioperfil.getIdUsuario();
            Usuario idUsuarioNew = usuarioperfil.getIdUsuario();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                usuarioperfil.setIdUsuario(idUsuarioNew);
            }
            usuarioperfil = em.merge(usuarioperfil);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getUsuarioperfilCollection().remove(usuarioperfil);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getUsuarioperfilCollection().add(usuarioperfil);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioperfil.getIdUsuarioPerfil();
                if (findUsuarioperfil(id) == null) {
                    throw new NonexistentEntityException("The usuarioperfil with id " + id + " no longer exists.");
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
            Usuarioperfil usuarioperfil;
            try {
                usuarioperfil = em.getReference(Usuarioperfil.class, id);
                usuarioperfil.getIdUsuarioPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioperfil with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = usuarioperfil.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getUsuarioperfilCollection().remove(usuarioperfil);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(usuarioperfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarioperfil> findUsuarioperfilEntities() {
        return findUsuarioperfilEntities(true, -1, -1);
    }

    public List<Usuarioperfil> findUsuarioperfilEntities(int maxResults, int firstResult) {
        return findUsuarioperfilEntities(false, maxResults, firstResult);
    }

    private List<Usuarioperfil> findUsuarioperfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarioperfil.class));
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

    public Usuarioperfil findUsuarioperfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarioperfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioperfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarioperfil> rt = cq.from(Usuarioperfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
