package it.csi.movies.model;
// Generated 14-gen-2009 11.36.57 by Hibernate Tools 3.2.2.GA

import it.csi.movies.model.enums.AspectRatio;
import it.csi.movies.model.enums.Country;
import it.csi.movies.model.enums.Language;
import it.csi.movies.model.enums.VideoCoding;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.Length;

/*import org.hibernate.validator.Digits;
import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.Past;*/

/**
 * Movie generated by hbm2java
 */
@Entity
@Table(name = "movie", catalog = "movies")
public class Movie implements java.io.Serializable {

	private Integer id;
	private Support support;
	private String name;
	private String originalName;
	private Date movieYear;
	private Country country;
	private Integer movieLength;
	private Double score;
	private String plot;
	private byte[] playbill;
	private String picContentType;
	private Language subt1, subt2, subt3;
	private AspectRatio aspectRatio;
	private VideoCoding videoCoding;
	private Boolean fromTV;
	private Set<Genre> genres = new HashSet<Genre>(0);
	private Set<Artist> directors = new HashSet<Artist>(0);
	private Set<Artist> actors = new HashSet<Artist>(0);
	private Set<AudioTrack> audioTracks = new HashSet<AudioTrack>(0);

	public Movie() {
	}

	public Movie(Support support, String name, String originalName,
			Date movieYear, Country country, Integer movieLength,
			Double score, String plot, byte[] playbill, String picContentType,
			Language subt1, Language subt2, Language subt3,
			AspectRatio aspectRatio, VideoCoding videoCoding, Boolean fromTV,
			Set<Genre> genres, Set<Artist> directors, Set<Artist> actors, Set<AudioTrack> audioTracks) {
		this.support = support;
		this.name = name;
		this.originalName = originalName;
		this.movieYear = movieYear;
		this.country = country;
		this.movieLength = movieLength;
		this.score = score;
		this.plot = plot;
		this.playbill = playbill;
		this.picContentType = picContentType;
		this.subt1 = subt1;
		this.subt2 = subt2;
		this.subt3 = subt3;
		this.aspectRatio = aspectRatio;
		this.videoCoding = videoCoding;
		this.fromTV =fromTV;
		this.genres = genres;
		this.directors = directors;
		this.actors = actors;
		this.audioTracks = audioTracks;
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
	@ManyToOne
	@JoinColumn(name = "Support_id")
	public Support getSupport() {
		return this.support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "original_name")
	public String getOriginalName() {
		return this.originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@javax.validation.constraints.Past
	@Temporal(TemporalType.DATE)
	@Column(name = "movie_year")
	public Date getMovieYear() {
		return this.movieYear;
	}

	public void setMovieYear(Date movieYear) {
		this.movieYear = movieYear;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "country")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Min(0)
	@Column(name = "movie_length")
	public Integer getMovieLength() {
		return this.movieLength;
	}

	public void setMovieLength(Integer movieLength) {
		this.movieLength = movieLength;
	}

	@Min(0) @Max(10)
	@Column(name = "score", precision=2, scale=1)
	@Digits(integer=2, fraction=1)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "plot", length=65535)
	@Size(max = 65535)
	public String getPlot() {
		return this.plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "playbill", length=16777215)
	public byte[] getPlaybill() {
		return this.playbill;
	}

	public void setPlaybill(byte[] playbill) {
		this.playbill = playbill;
	}

	@Column(name = "pic_content_type")
	public String getPicContentType() { return picContentType; }
	public void setPicContentType(String picContentType) {
	this.picContentType = picContentType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "subt1")
	public Language getSubt1() {
		return this.subt1;
	}

	public void setSubt1(Language subt1) {
		this.subt1 = subt1;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "subt2")
	public Language getSubt2() {
		return this.subt2;
	}

	public void setSubt2(Language subt2) {
		this.subt2 = subt2;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "subt3")
	public Language getSubt3() {
		return this.subt3;
	}

	public void setSubt3(Language subt3) {
		this.subt3 = subt3;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "aspect_ratio")
	public AspectRatio getAspectRatio() {
		return this.aspectRatio;
	}

	public void setAspectRatio(AspectRatio aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "video_coding")
	public VideoCoding getVideoCoding() {
		return this.videoCoding;
	}

	public void setVideoCoding(VideoCoding videoCoding) {
		this.videoCoding = videoCoding;
	}

	@Column(name = "from_tv")
	public Boolean getFromTV() {
		return this.fromTV;
	}

	public void setFromTV(Boolean fromTV) {
		this.fromTV = fromTV;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "movie_genre", catalog = "movies", joinColumns = {@JoinColumn(name = "Movie_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "Genre_id", nullable = false, updatable = false)})
	public Set<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "movie_director", catalog = "movies", joinColumns = {@JoinColumn(name = "Movie_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "Artist_id", nullable = false, updatable = false)})
	public Set<Artist> getDirectors() {
		return this.directors;
	}

	public void setDirectors(Set<Artist> directors) {
		this.directors = directors;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "movie_actor", catalog = "movies", joinColumns = {@JoinColumn(name = "Movie_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "Artist_id", nullable = false, updatable = false)})
	public Set<Artist> getActors() {
		return this.actors;
	}

	public void setActors(Set<Artist> actors) {
		this.actors = actors;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="movie_id")
	public Set<AudioTrack> getAudioTracks() {
		return this.audioTracks;
	}
	
	public void setAudioTracks(Set<AudioTrack> audioTracks) {
		this.audioTracks = audioTracks;
	}
}
