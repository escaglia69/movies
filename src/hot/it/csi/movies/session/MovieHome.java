package it.csi.movies.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.ui.graphicImage.Image;

@Name("movieHome")
public class MovieHome extends EntityHome<Movie> {

	@In(create = true)
	SupportHome supportHome;
	@In(create = true)
	ArtistHome artistHome;
	@In(create = true)
	GenreHome genreHome;
	@In(create = true)
	AudioTrackHome audioTrackHome;

	private String actorOrDir = "";
	
	public void setMovieId(Integer id) {
		setId(id);
	}

	public Integer getMovieId() {
		return (Integer) getId();
	}

	public void setActorOrDir(String actorOrDir) {
		this.actorOrDir=actorOrDir;
	}

	public String getActorOrDir() {
		return this.actorOrDir;
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
        Artist artist = artistHome.getDefinedInstance();
        if (artist!=null) {
        	if (actorOrDir.equals("actor")) {
            	artist.getMoviesAsActor().add(movie);
            	movie.getActors().add(artist);
		    }
			if (actorOrDir.equals("director")) {
	        	artist.getMoviesAsDirector().add(movie);
	        	movie.getDirectors().add(artist);
		    }
		}
        Genre genre = genreHome.getDefinedInstance();
        if (genre!=null) {
           	genre.getMovies().add(movie);
           	movie.getGenres().add(genre);
		}
        /*AudioTrack audioTrack = audioTrackHome.getDefinedInstance();
        if (audioTrack!=null) {
        	movie.getAudioTracks().add(audioTrack);
        }*/
        //this.update();
	}

	public boolean isWired() {
		return true;
	}

	public Movie getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Artist> getActors() {
		return getInstance() == null ? null : new ArrayList<Artist>(
				getInstance().getActors());
	}
	
	public List<Genre> getGenres() {
		return getInstance() == null ? null : new ArrayList<Genre>(
				getInstance().getGenres());
	}
	
	public List<Artist> getDirectors() {
		return getInstance() == null ? null : new ArrayList<Artist>(
				getInstance().getDirectors());
	}
	public List<AudioTrack> getAudioTracks() {
		return getInstance() == null ? null : new ArrayList<AudioTrack>(
				getInstance().getAudioTracks());
	}
	public String removeFromActors() {
		Movie movie = getInstance();
        Artist actor = artistHome.getDefinedInstance();
        if (actor!=null) {
			if (movie.getActors().contains(actor)) {
				actor.getMoviesAsActor().remove(movie);
				movie.getActors().remove(actor);
				artistHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	public String removeFromDirectors() {
		Movie movie = getInstance();
        Artist director = artistHome.getDefinedInstance();
        if (director!=null) {
			if (movie.getDirectors().contains(director)) {
				director.getMoviesAsDirector().remove(movie);
				movie.getDirectors().remove(director);
				artistHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	public String removeFromGenres() {
		Movie movie = getInstance();
        Genre genre = genreHome.getDefinedInstance();
        if (genre!=null) {
			if (movie.getGenres().contains(genre)) {
				genre.getMovies().remove(movie);
				movie.getGenres().remove(genre);
				genreHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	public String removeSupport() {
		Movie movie = getInstance();
        Support support = movie.getSupport();
        if (support!=null) {
			support.getMovies().remove(movie);
			movie.setSupport(null);
			supportHome.setId(null);
			return null;
        }
		return "failure";
	}
	public String removeFromAudioTracks() {
		Movie movie = getInstance();
        AudioTrack audioTrack = audioTrackHome.getDefinedInstance();
        if (audioTrack!=null) {
			if ( movie.getAudioTracks().contains(audioTrack)) {
				movie.getAudioTracks().remove(audioTrack);
				audioTrackHome.remove();
				audioTrackHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	@Transactional
	public String revert() {
		getEntityManager().refresh(getInstance());
		artistHome.clearInstance();
		genreHome.clearInstance();
		supportHome.clearInstance();
		audioTrackHome.clearInstance();
		return "reverted";
	}
	@Override
	public String update() {
		Movie movie = getInstance();
		if (movie.getPlaybill() == null) {
			byte[] pic = (byte[])getEntityManager().createQuery("select movie.playbill from Movie movie where movie.id = :id").setParameter("id", movie.getId()).getSingleResult();
			movie.setPlaybill(pic);
			movie.setPicContentType(getEntityManager().find(Movie.class, getId()).getPicContentType());
		} else {
			try {
				Image image = new Image();
				image.setInput(movie.getPlaybill());
				if (image.getBufferedImage() == null) {
					throw new IOException("The playbill image data is empty.");
				}
				if (!image.getContentType().getMimeType().matches("image/(png|gif|jpeg)")) {
					getFacesMessages().addToControl("image",
						"Invalid image type: " + image.getContentType());
				}
			} catch (IOException e) {
				getFacesMessages().addToControl("image", FacesMessage.SEVERITY_ERROR,
					"An error occurred reading the profile image.");
				return null;
			}
		}
		return super.update();
	}
}
