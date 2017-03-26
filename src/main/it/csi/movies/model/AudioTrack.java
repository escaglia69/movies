package it.csi.movies.model;
// Generated 14-gen-2009 11.36.57 by Hibernate Tools 3.2.2.GA

import it.csi.movies.model.enums.AudioChannel;
import it.csi.movies.model.enums.AudioCoding;
import it.csi.movies.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Genre generated by hbm2java
 */
@Entity
@Table(name = "audio_track", catalog = "movies")
public class AudioTrack implements java.io.Serializable {

	private Integer id;
	private Integer trackNumber;
	private Language language;
	private AudioCoding audioCoding;
	private AudioChannel audioChannel;
	
	public AudioTrack() {
	}

	public AudioTrack(Integer trackNumber, Language language, AudioCoding audioCoding, AudioChannel audioChannel) {
		this.trackNumber = trackNumber;
		this.language = language;
		this.audioCoding = audioCoding;
		this.audioChannel = audioChannel;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "track_number")
	public Integer getTrackNumber() {
		return this.trackNumber;
	}

	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "language")
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "audio_coding")
	public AudioCoding getAudioCoding() {
		return this.audioCoding;
	}

	public void setAudioCoding(AudioCoding audioCoding) {
		this.audioCoding = audioCoding;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "audio_channel")
	public AudioChannel getAudioChannel() {
		return this.audioChannel;
	}

	public void setAudioChannel(AudioChannel audioChannel) {
		this.audioChannel = audioChannel;
	}
	
}
