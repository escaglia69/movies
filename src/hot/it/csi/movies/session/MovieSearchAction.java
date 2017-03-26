package it.csi.movies.session;

import it.csi.movies.model.Artist;
import it.csi.movies.model.AudioTrack;
import it.csi.movies.model.Genre;
import it.csi.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

@Stateful
@Name("movieSearch")
@Scope(ScopeType.SESSION)
@JndiName(value="java:app/movies.jar/MovieSearchAction")
public class MovieSearchAction implements MovieSearching {
	@PersistenceContext
	private EntityManager em;  
    private String searchString;
    private int pageSize = 5;
    private int page;
    private boolean nextPageAvailable;
    private List<Artist> actors;
    private List<Artist> directors;
    private List<Genre> genres;
    private List<AudioTrack> audioTracks;
    private Boolean show;

    @DataModel
    private List<Movie> movies;

    //@DataModelSelection
    @Out(required=false)
    private Movie movie;

    public void find() 
    {
        page = 0;
        queryMovies();
    }

    public void nextPage() 
    {
        page++;
        queryMovies();
    }
    
    private void queryMovies() {
        List<Movie> results = em.createQuery("select m from Movie m where lower(m.name) like #{pattern} or lower(m.originalName) like #{pattern}")
                                .setMaxResults(pageSize+1)
                                .setFirstResult(page * pageSize)
                                .getResultList();
        
        nextPageAvailable = results.size() > pageSize;
        if (nextPageAvailable) 
        {
            movies = new ArrayList<Movie>(results.subList(0,pageSize));
        } else {
            movies = results;
        }
    }

    public boolean isNextPageAvailable()
    {
        return nextPageAvailable;
    }
   
   public int getPageSize() {
      return pageSize;
   }
   
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
   
   @Factory(value="pattern", scope=ScopeType.EVENT)
   public String getSearchPattern()
   {
      return searchString==null ? 
            "%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
   }
   
   public String getSearchString()
   {
      return searchString;
   }
   
   public void setSearchString(String searchString)
   {
      this.searchString = searchString;
   }
   public List<Artist> getActors() {
	   actors = (movie == null ? null : new ArrayList<Artist>(movie.getActors()));
	   return actors;
   }
   public void setActors(List<Artist> actors) {
	   this.actors = actors;
   }
   public List<Artist> getDirectors() {
	   directors = (movie == null ? null : new ArrayList<Artist>(movie.getDirectors()));
	   return directors;
   }
   public void setDirectors(List<Artist> directors) {
	   this.directors = directors;
   }
   public List<Genre> getGenres() {
	   genres = (movie == null ? null : new ArrayList<Genre>(movie.getGenres()));
	   return genres;
   }
   public void setGenres(List<Genre> genres) {
	   this.genres = genres;
   }
   public List<AudioTrack> getAudioTracks() {
	   audioTracks = (movie == null ? null : new ArrayList<AudioTrack>(movie.getAudioTracks()));
	   return audioTracks;
   }
   public void setAudioTracks(List<AudioTrack> audioTracks) {
	   this.audioTracks = audioTracks;
   }
   public void fill(Integer id){
       movie = (Movie)em.createQuery("select m from Movie m left join fetch m.actors actors left join fetch m.directors dirs left join fetch m.genres genres left join fetch m.audioTracks audioTracks where m.id = :id")
       .setParameter("id", id)
       .getSingleResult();  
   }
   public Boolean getShow() { 
	      return show;
   }
   public void setShow(Boolean show) {
		this.show = show;
   }
   @Remove
   public void destroy() {}
}
