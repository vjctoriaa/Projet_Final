/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.service;

import entites.Listesdelecture;
import entites.ListesdelectureMusiques;
import entites.ListesdelectureMusiquesPK;
import entites.Musiques;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jodel
 */
@Stateless
@Path("listesdelecture")
public class ListesdelectureFacadeREST extends AbstractFacade<Listesdelecture> {

    @PersistenceContext(unitName = "ProjetPLDLPU")
    private EntityManager em;

    public ListesdelectureFacadeREST() {
        super(Listesdelecture.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Listesdelecture entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Listesdelecture entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Listesdelecture find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Listesdelecture> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Listesdelecture> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("voirListe/{idListe}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
    public List<ListesdelectureMusiquesPK> findRange(@PathParam("idListe") Integer idListe) {
        //List<Musiques> musiquesInPlaylist = new ArrayList<Musiques>();
        
        List<ListesdelectureMusiques> listMusique = new ArrayList<ListesdelectureMusiques>();
        Query q = em.createNamedQuery("ListesdelectureMusiques.findMusiquesByListeDeLecture");
        q.setParameter("listeDeLecture", idListe);
        
        try{
            listMusique = (List<ListesdelectureMusiques>) q.getResultList();
        }
        catch(Exception ex){
            
        }
        
        List<ListesdelectureMusiquesPK> lmPKList = new ArrayList<ListesdelectureMusiquesPK>();
        
        //@NamedQuery(name = "Musiques.findById", query = "SELECT m FROM Musiques m WHERE m.id = :id")
        
        for(int i = 0; i < listMusique.size(); i++){
            ListesdelectureMusiquesPK lmPK = listMusique.get(i).getListesdelectureMusiquesPK();
            lmPKList.add(lmPK);
            
            /*int musiqueID = lmPK.getMusique();
            Musiques musique = new Musiques();
            Query query = em.createNamedQuery("Musiques.findById");
            q.setParameter("id", musiqueID);

            try{
                musique = (Musiques) query.getSingleResult();
            }
            catch(Exception ex){

            }
            
            musiquesInPlaylist.add(musique);*/
        }
        
        return lmPKList;
    }
    
}
