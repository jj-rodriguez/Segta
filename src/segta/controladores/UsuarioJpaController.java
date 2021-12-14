/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.controladores;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import segta.clases.Usuarioperfil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import segta.clases.Usuario;
import segta.controladores.exceptions.IllegalOrphanException;
import segta.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author MODERNIZACION3
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getUsuarioperfilCollection() == null) {
            usuario.setUsuarioperfilCollection(new ArrayList<Usuarioperfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuarioperfil> attachedUsuarioperfilCollection = new ArrayList<Usuarioperfil>();
            for (Usuarioperfil usuarioperfilCollectionUsuarioperfilToAttach : usuario.getUsuarioperfilCollection()) {
                usuarioperfilCollectionUsuarioperfilToAttach = em.getReference(usuarioperfilCollectionUsuarioperfilToAttach.getClass(), usuarioperfilCollectionUsuarioperfilToAttach.getIdUsuarioPerfil());
                attachedUsuarioperfilCollection.add(usuarioperfilCollectionUsuarioperfilToAttach);
            }
            usuario.setUsuarioperfilCollection(attachedUsuarioperfilCollection);
            em.persist(usuario);
            for (Usuarioperfil usuarioperfilCollectionUsuarioperfil : usuario.getUsuarioperfilCollection()) {
                Usuario oldIdUsuarioOfUsuarioperfilCollectionUsuarioperfil = usuarioperfilCollectionUsuarioperfil.getIdUsuario();
                usuarioperfilCollectionUsuarioperfil.setIdUsuario(usuario);
                usuarioperfilCollectionUsuarioperfil = em.merge(usuarioperfilCollectionUsuarioperfil);
                if (oldIdUsuarioOfUsuarioperfilCollectionUsuarioperfil != null) {
                    oldIdUsuarioOfUsuarioperfilCollectionUsuarioperfil.getUsuarioperfilCollection().remove(usuarioperfilCollectionUsuarioperfil);
                    oldIdUsuarioOfUsuarioperfilCollectionUsuarioperfil = em.merge(oldIdUsuarioOfUsuarioperfilCollectionUsuarioperfil);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Collection<Usuarioperfil> usuarioperfilCollectionOld = persistentUsuario.getUsuarioperfilCollection();
            Collection<Usuarioperfil> usuarioperfilCollectionNew = usuario.getUsuarioperfilCollection();
            List<String> illegalOrphanMessages = null;
            for (Usuarioperfil usuarioperfilCollectionOldUsuarioperfil : usuarioperfilCollectionOld) {
                if (!usuarioperfilCollectionNew.contains(usuarioperfilCollectionOldUsuarioperfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioperfil " + usuarioperfilCollectionOldUsuarioperfil + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usuarioperfil> attachedUsuarioperfilCollectionNew = new ArrayList<Usuarioperfil>();
            for (Usuarioperfil usuarioperfilCollectionNewUsuarioperfilToAttach : usuarioperfilCollectionNew) {
                usuarioperfilCollectionNewUsuarioperfilToAttach = em.getReference(usuarioperfilCollectionNewUsuarioperfilToAttach.getClass(), usuarioperfilCollectionNewUsuarioperfilToAttach.getIdUsuarioPerfil());
                attachedUsuarioperfilCollectionNew.add(usuarioperfilCollectionNewUsuarioperfilToAttach);
            }
            usuarioperfilCollectionNew = attachedUsuarioperfilCollectionNew;
            usuario.setUsuarioperfilCollection(usuarioperfilCollectionNew);
            usuario = em.merge(usuario);
            for (Usuarioperfil usuarioperfilCollectionNewUsuarioperfil : usuarioperfilCollectionNew) {
                if (!usuarioperfilCollectionOld.contains(usuarioperfilCollectionNewUsuarioperfil)) {
                    Usuario oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil = usuarioperfilCollectionNewUsuarioperfil.getIdUsuario();
                    usuarioperfilCollectionNewUsuarioperfil.setIdUsuario(usuario);
                    usuarioperfilCollectionNewUsuarioperfil = em.merge(usuarioperfilCollectionNewUsuarioperfil);
                    if (oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil != null && !oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil.equals(usuario)) {
                        oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil.getUsuarioperfilCollection().remove(usuarioperfilCollectionNewUsuarioperfil);
                        oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil = em.merge(oldIdUsuarioOfUsuarioperfilCollectionNewUsuarioperfil);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usuarioperfil> usuarioperfilCollectionOrphanCheck = usuario.getUsuarioperfilCollection();
            for (Usuarioperfil usuarioperfilCollectionOrphanCheckUsuarioperfil : usuarioperfilCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuarioperfil " + usuarioperfilCollectionOrphanCheckUsuarioperfil + " in its usuarioperfilCollection field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

   
    public Usuario validaUsuario(String user, String pass) throws NoSuchAlgorithmException{
        Usuario usuario=null;
        String claveMD5 = this.claveMD5(pass);
       
       
        EntityManager em = getEntityManager();

        try {
            List<Usuario> listausuarios = em.createNamedQuery("Usuario.findByNombre", Usuario.class).setParameter("nombre",user).getResultList();
            for (Usuario u:listausuarios){
                if (u.getClave().equals(claveMD5)) usuario=u;
            }
          
 
            //return em.findByNombre(Usuario.class, user);
        } finally {
            em.close();
        }
       return usuario;
    }
    
    public boolean tienePerfil(Usuario  idUsuario, String perfil) throws NoSuchAlgorithmException{
          
        boolean tiene=false;
        EntityManager em = getEntityManager();
        
        try {
            List<Usuarioperfil> listaperfiles = em.createNamedQuery("Usuarioperfil.findByPerfil", Usuarioperfil.class).setParameter("idUsuario",idUsuario).setParameter("perfil",perfil).getResultList();
            for (Usuarioperfil up: listaperfiles){
                tiene=true;
            }
          
 
            //return em.findByNombre(Usuario.class, user);
        } finally {
            em.close();
        }
       return tiene;
    }
    
     public boolean usuarioExiste(String nombre) throws NoSuchAlgorithmException{
        boolean existe= false;      
        EntityManager em = getEntityManager();

        try {
            List<Usuario> listausuarios = em.createNamedQuery("Usuario.findByNombre", Usuario.class).setParameter("nombre",nombre).getResultList();
            for (Usuario u:listausuarios){
                existe=true;
            }
          
 
            //return em.findByNombre(Usuario.class, user);
        } finally {
            em.close();
        }
       return existe;
    }
     
    public String claveMD5(String pass) throws NoSuchAlgorithmException{
       MessageDigest md;
       md = MessageDigest.getInstance("MD5");
       byte[] messageDigest = md.digest(pass.getBytes());
       BigInteger number = new BigInteger(1, messageDigest);
       String claveMD5 = number.toString(16);

        while (claveMD5.length() < 32) {
            claveMD5 = "0" + claveMD5;
        }  
        
        return claveMD5;
    }
    
}
