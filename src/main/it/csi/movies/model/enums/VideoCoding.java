package it.csi.movies.model.enums;

public enum VideoCoding {
	NONE("NONE(VHS)"),
	H264("H.264"),
	MPEG2("MPEG-2"),
	MPEG4("MPEG-4");
    private String label;
	VideoCoding(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
