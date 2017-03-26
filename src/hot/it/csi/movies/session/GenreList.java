package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("genreList")
public class GenreList extends EntityQuery<Genre> {

	private static final String EJBQL = "select genre from Genre genre";

	private static final String[] RESTRICTIONS = {
			"lower(genre.description) like concat(lower(#{genreList.genre.description}),'%')",
			"lower(genre.name) like concat(lower(#{genreList.genre.name}),'%')",};

	private Genre genre = new Genre();

	public GenreList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Genre getGenre() {
		return genre;
	}
}
