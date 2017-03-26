package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("positionList")
public class PositionList extends EntityQuery<Position> {

	private static final String EJBQL = "select position from Position position";

	private static final String[] RESTRICTIONS = {};

	private Position position = new Position();

	public PositionList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Position getPosition() {
		return position;
	}
}
