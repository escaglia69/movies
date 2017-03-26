package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("supportList")
public class SupportList extends EntityQuery<Support> {

	private static final String EJBQL = "select support from Support support";

	private static final String[] RESTRICTIONS = {
			"lower(support.storage) like concat(lower(#{supportList.support.storage}),'%')",
			"lower(support.videoCoding) like concat(lower(#{supportList.support.videoCoding}),'%')",};

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
