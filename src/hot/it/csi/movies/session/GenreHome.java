package it.csi.movies.session;

import java.util.ArrayList;
import java.util.List;

import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityHome;

@Name("genreHome")
public class GenreHome extends EntityHome<Genre> {

	@In(create = true)
	MovieHome movieHome;

	public void setGenreId(Integer id) {
		setId(id);
	}

	public Integer getGenreId() {
		return (Integer) getId();
	}

	@Override
	protected Genre createInstance() {
		Genre genre = new Genre();
		return genre;
	}

	public void wire() {
		Genre genre = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
	        	movie.getGenres().add(genre);
	        	genre.getMovies().add(movie);
        }
        //this.update();
	}

	public boolean isWired() {
		return true;
	}

	public Genre getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Movie> getMovies() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMovies());
	}
	public String removeFromMovies() {
		Genre genre = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
			if (genre.getMovies().contains(movie)) {
				movie.getGenres().remove(genre);
				genre.getMovies().remove(movie);
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
