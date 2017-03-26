package it.csi.movies.session;

import it.csi.movies.model.*;
import it.csi.movies.model.enums.AspectRatio;
import it.csi.movies.model.enums.AudioChannel;
import it.csi.movies.model.enums.AudioCoding;
import it.csi.movies.model.enums.Language;
import it.csi.movies.model.enums.VideoCoding;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import java.util.Arrays;

@Name("movieList")
public class MovieList extends EntityQuery<Movie> {

	private static final String EJBQL = "select movie from Movie movie left join fetch movie.actors actors left join fetch movie.directors directors left join fetch movie.genres genres left join fetch movie.support sup left join fetch movie.audioTracks at";

	private static final String[] RESTRICTIONS = {
			"movie.country=#{movieList.movie.country}",
			//"movie.lang1 like #{movieList.movie.lang1} or movie.lang2 like :el2 or movie.lang3 like :el2",
			"lower(movie.name) like concat(lower(#{movieList.movie.name}),'%')",
			"lower(movie.originalName) like concat(lower(#{movieList.movie.originalName}),'%')",
			"at.language = #{movieCriteria.language}",
			"movie.score >= #{movieList.movie.score}",
			"movie.videoCoding = #{movieList.movie.videoCoding}",
			"movie.aspectRatio = #{movieList.movie.aspectRatio}",
			"movie.fromTV = #{movieList.movie.fromTV}",
			"lower(sup.borrowedTo) like concat(lower(#{movieCriteria.borrowedTo}),'%')",
			"lower(actors.surname) like concat(lower(#{movieCriteria.actSurname}),'%')",
			"lower(directors.surname) like concat(lower(#{movieCriteria.dirSurname}),'%')",
			"movie.movieYear = #{movieList.movie.movieYear}",};

    private Movie movie = new Movie();

    @Factory("langs")
    public Language[] getLanguages() {
    	return Language.values();
    }

    @Factory("aspratios")
    public AspectRatio[] getAspectRatios() {
    	return AspectRatio.values();
    }

    @Factory("videocodings")
    public VideoCoding[] getVideoCodings() {
    	return VideoCoding.values();
    }

    @Factory("audiocodings")
    public AudioCoding[] getAudioCodings() {
    	return AudioCoding.values();
    }

    @Factory("audiochannels")
    public AudioChannel[] getAudioChannels() {
    	return AudioChannel.values();
    }

	public MovieList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Movie getMovie() {
		return movie;
	}
}
