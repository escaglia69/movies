package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("audioTrackList")
public class AudioTrackList extends EntityQuery<AudioTrack> {

	private static final String EJBQL = "select audioTrack from AudioTrack audioTrack";

	private static final String[] RESTRICTIONS = {};
			//"lower(audioTrack.description) like concat(lower(#{audioTrackList.audioTrack.description}),'%')",
			//"lower(audioTrack.name) like concat(lower(#{audioTrackList.audioTrack.name}),'%')",};

	private AudioTrack audioTrack = new AudioTrack();

	public AudioTrackList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AudioTrack getAudioTrack() {
		return audioTrack;
	}
}
