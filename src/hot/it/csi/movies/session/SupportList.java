package it.csi.movies.session;

import it.csi.movies.model.*;
import it.csi.movies.model.enums.Language;
import it.csi.movies.model.enums.Storage;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("supportList")
public class SupportList extends EntityQuery<Support> {

	private static final String EJBQL = "select support from Support support";

	private static final String[] RESTRICTIONS = {
			"support.storage=#{supportList.support.storage}",
			"lower(support.borrowedTo) like concat(lower(#{supportList.support.borrowedTo}),'%')",};

    @Factory("storages")
    public Storage[] getStorages() {
    	return Storage.values();
    }

    private Support support = new Support();

	public SupportList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Support getSupport() {
		return support;
	}
}
