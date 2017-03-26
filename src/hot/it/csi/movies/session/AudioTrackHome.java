package it.csi.movies.session;

import it.csi.movies.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityHome;

@Name("audioTrackHome")
public class AudioTrackHome extends EntityHome<AudioTrack> {

	@In(create = true)
	MovieHome movieHome;

	public void setAudioTrackId(Integer id) {
		setId(id);
	}

	public Integer getAudioTrackId() {
		return (Integer) getId();
	}

	@Override
	protected AudioTrack createInstance() {
		AudioTrack audioTrack = new AudioTrack();
		return audioTrack;
	}

	public void wire() {
		AudioTrack audioTrack = getInstance();
        Movie movie = movieHome.getDefinedInstance();
        if (movie!=null) {
	        	movie.getAudioTracks().add(audioTrack);
        }
        //this.update();
	}

	public boolean isWired() {
		return true;
	}

	public AudioTrack getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	@Transactional
	public String revert() {
		getEntityManager().refresh(getInstance());
		//movieHome.clearInstance();
		return "reverted";
	}
	@Override
	public String persist() {
		String ret = super.persist();
		this.clearInstance();
		return ret;
	}
}
