package it.csi.movies.session;

import it.csi.movies.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("supportHome")
public class SupportHome extends EntityHome<Support> {

	@In(create = true)
	PositionHome positionHome;

	public void setSupportId(Integer id) {
		setId(id);
	}

	public Integer getSupportId() {
		return (Integer) getId();
	}

	@Override
	protected Support createInstance() {
		Support support = new Support();
		return support;
	}

	public void wire() {
		getInstance();
		Position position = positionHome.getDefinedInstance();
		if (position != null) {
			getInstance().setPosition(position);
		}
	}

	public boolean isWired() {
		return true;
	}

	public Support getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Movie> getMovies() {
		return getInstance() == null ? null : new ArrayList<Movie>(
				getInstance().getMovies());
	}

}
