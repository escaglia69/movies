package it.csi.movies.session;

import it.csi.movies.model.*;
import it.csi.movies.model.enums.Country;
import it.csi.movies.model.enums.Sex;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("artistList")
public class ArtistList extends EntityQuery<Artist> {

	private static final String EJBQL = "select artist from Artist artist";

	private static final String[] RESTRICTIONS = {
			"artist.country=#{artistList.artist.country}",
			"lower(artist.name) like concat(lower(#{artistList.artist.name}),'%')",
			"artist.sex=#{artistList.artist.sex}",
			"lower(artist.surname) like concat(lower(#{artistList.artist.surname}),'%')",};

    @Factory("sexes")
    public Sex[] getSexes() {
       return Sex.values();
    }
    @Factory("countries")
    public Country[] getCountries() {
       return Country.values();
    }

    private Artist artist = new Artist();

	public ArtistList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Artist getArtist() {
		return artist;
	}
}
