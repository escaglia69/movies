package it.csi.movies.model.enums;

public enum Sex {
	MALE("Male"),
	FEMALE("Female");
    private String label;
	Sex(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
