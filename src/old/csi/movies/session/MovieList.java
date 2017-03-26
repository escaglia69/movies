package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("movieList")
public class MovieList extends EntityQuery<Movie> {

	private static final String EJBQL = "select movie from Movie movie";

	private static final String[] RESTRICTIONS = {
			"lower(movie.country) like concat(lower(#{movieList.movie.country}),'%')",
			"lower(movie.name) like concat(lower(#{movieList.movie.name}),'%')",
			"lower(movie.originalName) like concat(lower(#{movieList.movie.originalName}),'%')",
			"lower(movie.plot) like concat(lower(#{movieList.movie.plot}),'%')",};

	private Movie movie = new Movie();

	public MovieList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Movie getMovie() {
		return movie;
	}
}
