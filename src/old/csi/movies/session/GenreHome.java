package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("genreHome")
public class GenreHome extends EntityHome<Genre> {

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
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Genre getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
