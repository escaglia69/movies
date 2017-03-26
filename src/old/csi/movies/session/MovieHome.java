package it.csi.movies.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

@Name("movieHome")
public class MovieHome extends EntityHome<Movie> {

	@In(create = true)
	SupportHome supportHome;
	@In(create = true)
	ActorHome actorHome;
	
	//@RequestParameter
	//Long actorId;
	public void setMovieId(Integer id) {
		setId(id);
	}

	public Integer getMovieId() {
		return (Integer) getId();
	}

	@Override
	protected Movie createInstance() {
		Movie movie = new Movie();
		return movie;
	}

	public void wire() {
		Movie movie = getInstance();
		Support support = supportHome.getDefinedInstance();
		if (support != null) {
			getInstance().setSupport(support);
		}
        Actor actor = actorHome.getDefinedInstance();
		//Actor actor = this.getEntityManager().find(Actor.class,actorId);
        if (actor!=null)
        {
        	actor.getMovies().add(movie);
        	movie.getActors().add(actor);
	    }	
    }

	public boolean isWired() {
		return true;
	}

	public Movie getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	public String removeSupport() {
			getInstance().setSupport(null);
			return null;
	}
	public List<Actor> getActors() {
		return getInstance() == null ? null : new ArrayList<Actor>(
				getInstance().getActors());
	}
}
