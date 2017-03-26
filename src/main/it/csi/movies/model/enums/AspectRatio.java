package it.csi.movies.model.enums;

public enum AspectRatio {
	AR4_3("4:3"),
	AR3_2("3:2"),
	AR16_9("16:9"),
	AR185_1("1.85:1"),
	AR239_1("2.39:1"),
	AROTH("Other");
    private String label;
	AspectRatio(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
