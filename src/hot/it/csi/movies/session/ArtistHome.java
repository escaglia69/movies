package it.csi.movies.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;


import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.ui.graphicImage.Image;

@Name("artistHome")
public class ArtistHome extends EntityHome<Artist> {

	@In(create = true)
	MovieHome movieHome;

	private String actorOrDir = "";

	public void setArtistId(Integer id) {
		setId(id);
	}

	public Integer getArtistId() {
		return (Integer) getId();
	}

	public void setActorOrDir(String actorOrDir) {
		this.actorOrDir=actorOrDir;
	}

	public String getActorOrDir() {
		return this.actorOrDir;
	}
	
	@Override
	protected Artist createInstance() {
		Artist artist = new Artist();
		return artist;
	}

	public void wire() {
		Artist artist = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
        	if (actorOrDir.equals("actor")) {
	        	movie.getActors().add(artist);
	        	artist.getMoviesAsActor().add(movie);
        	}
        	if (actorOrDir.equals("director")) {
	        	movie.getDirectors().add(artist);
	        	artist.getMoviesAsDirector().add(movie);
		    }
		}
        //this.update();
	}

	public boolean isWired() {
		return true;
	}

	public Artist getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	public List<Movie> getMoviesAsActor() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMoviesAsActor());
	}

	public List<Movie> getMoviesAsDirector() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMoviesAsDirector());
	}
	public String removeFromMoviesAsActor() {
		Artist actor = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
			if (actor.getMoviesAsActor().contains(movie)) {
				movie.getActors().remove(actor);
				actor.getMoviesAsActor().remove(movie);
				movieHome.setId(null);
				return null;
			}
			return "failure";
        }
		return "failure";
	}
	public String removeFromMoviesAsDirector() {
		Artist director = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
			if (director.getMoviesAsDirector().contains(movie)) {
				movie.getDirectors().remove(director);
				director.getMoviesAsDirector().remove(movie);
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
	@Override
	public String update() {
		Artist artist = getInstance();
		if (artist.getPicture() == null) {
			byte[] pic = (byte[])getEntityManager().createQuery("select artist.picture from Artist artist where artist.id = :id").setParameter("id", artist.getId()).getSingleResult();
			artist.setPicture(pic);
			artist.setPicContentType(getEntityManager().find(Artist.class, getId()).getPicContentType());
		} else {
			try {
				Image image = new Image();
				image.setInput(artist.getPicture());
				if (image.getBufferedImage() == null) {
					throw new IOException("The profile image data is empty.");
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
