package it.csi.movies.session;

import it.csi.movies.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("actorList")
public class ActorList extends EntityQuery<Actor> {

	private static final String EJBQL = "select actor from Actor actor";

	private static final String[] RESTRICTIONS = {
			"lower(actor.country) like concat(lower(#{actorList.actor.country}),'%')",
			"lower(actor.name) like concat(lower(#{actorList.actor.name}),'%')",
			"lower(actor.sex) like concat(lower(#{actorList.actor.sex}),'%')",
			"lower(actor.surname) like concat(lower(#{actorList.actor.surname}),'%')",};

	private Actor actor = new Actor();

	public ActorList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Actor getActor() {
		return actor;
	}
}
