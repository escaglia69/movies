package it.csi.movies.model.enums;

public enum Storage {
	BD("BlueRay"),
	CD("CD"),
	DVD("DVD"),
	PCDISK("PC Disk"),
	VHS("VHS");
    private String label;
	Storage(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
