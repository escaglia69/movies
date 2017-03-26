package it.csi.movies.model.enums;

public enum Container {
	THREEGP("3GP"),
	AVI("AVI"),
	DIVX("DIVX-DivX"),
	FLV("FLV-Flash"),
	MKV("MKV-Matroska"),
	MOV("MOV-Quicktime"),
	MPEG("MPEG"),
	OGG("OGG"),
	MPEG2_PS("PS-Program Stream"),
	MPEG2_TS("TS-Transport Stream"),
	WMV("WMV-Windows");
    private String label;
	Container(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
