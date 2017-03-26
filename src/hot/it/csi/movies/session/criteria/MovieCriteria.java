package it.csi.movies.session.criteria;

import it.csi.movies.model.enums.Language;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("movieCriteria")
@Scope(ScopeType.CONVERSATION)
public class MovieCriteria implements Serializable {
	private Language language;
	private String borrowedTo;
	private String actSurname;
	private String dirSurname;
	
	public Language getLanguage() {
		return this.language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getBorrowedTo() {
		return this.borrowedTo;
	}
	public void setBorrowedTo(String borrowedTo) {
		this.borrowedTo = borrowedTo;
	}

	public String getActSurname() {
		return this.actSurname;
	}
	public void setActSurname(String actSurname) {
		this.actSurname = actSurname;
	}

	public String getDirSurname() {
		return this.dirSurname;
	}
	public void setDirSurname(String dirSurname) {
		this.dirSurname = dirSurname;
	}
}
