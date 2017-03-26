//$Id: HotelSearching.java 5579 2007-06-27 00:06:49Z gavin $
package it.csi.movies.session;

import it.csi.movies.model.Artist;
import it.csi.movies.model.AudioTrack;
import it.csi.movies.model.Genre;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MovieSearching
{
   public int getPageSize();
   public void setPageSize(int pageSize);
   
   public String getSearchString();
   public void setSearchString(String searchString);
   
   public String getSearchPattern();
   
   public void find();
   public void nextPage();
   public boolean isNextPageAvailable();
   public List<Artist> getActors();
   public void setActors(List<Artist> actors);
   public List<Artist> getDirectors();
   public void setDirectors(List<Artist> directors);
   public List<Genre> getGenres();
   public void setGenres(List<Genre> genres);
   public List<AudioTrack> getAudioTracks();
   public void setAudioTracks(List<AudioTrack> audioTracks);
   public void fill(Integer id);
   public Boolean getShow();
   public void setShow(Boolean show);
   public void destroy();
   
}