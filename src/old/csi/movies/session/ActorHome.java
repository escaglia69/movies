package it.csi.movies.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("actorHome")
public class ActorHome extends EntityHome<Actor> {

	@In(create = true)
	MovieHome movieHome;

	public void setActorId(Integer id) {
		setId(id);
	}

	public Integer getActorId() {
		return (Integer) getId();
	}

	@Override
	protected Actor createInstance() {
		Actor actor = new Actor();
		return actor;
	}

	public void wire() {
		Actor actor = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null)
        {
        	movie.getActors().add(actor);
        	actor.getMovies().add(movie);
	    } 
	}

	public boolean isWired() {
		return true;
	}

	public Actor getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	public List<Movie> getMovies() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMovies());
	}
}
