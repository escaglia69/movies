package it.csi.movies.session;

import it.csi.movies.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("positionHome")
public class PositionHome extends EntityHome<Position> {

	public void setPositionId(Integer id) {
		setId(id);
	}

	public Integer getPositionId() {
		return (Integer) getId();
	}

	@Override
	protected Position createInstance() {
		Position position = new Position();
		return position;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Position getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Support> getSupports() {
		return getInstance() == null ? null : new ArrayList<Support>(
				getInstance().getSupports());
	}

}
