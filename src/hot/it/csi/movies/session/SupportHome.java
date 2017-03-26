package it.csi.movies.session;

import it.csi.movies.model.*;

import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityHome;

@Name("supportHome")
public class SupportHome extends EntityHome<Support> {

	@In(create=true)
	MovieHome	movieHome;

	public void setSupportId(Integer id) {
		setId(id);
	}

	public Integer getSupportId() {
		return (Integer) getId();
	}

	@Override
	protected Support createInstance() {
		Support support = new Support();
		return support;
	}

	public void wire() {
		Support support = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null)
        {
        	movie.setSupport(support);
        	support.getMovies().add(movie);
	    } 
        //this.update();
	}

	public boolean isWired() {
		return true;
	}

	public Support getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Movie> getMovies() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMovies());
	}
	public String removeFromMovies() {
		Support support = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
			if (support.getMovies().contains(movie)) {
				movie.getGenres().remove(support);
				support.getMovies().remove(movie);
				movieHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	@Transactional
	public String revert() {
		getEntityManager().refresh(getInstance());
		movieHome.clearInstance();
		return "reverted";
	}
}
