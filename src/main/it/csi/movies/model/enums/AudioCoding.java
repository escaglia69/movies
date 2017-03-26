package it.csi.movies.model.enums;

public enum AudioCoding {
	AAC("AAC-Advanced Audio Coding"),
	AC3("AC3-Dolby Digital"),
	DTS("DTS-Digital Theater Systems"),
	FLAC("FLAC"),
	LPCM("LPCM-Linear Pulse Code Modulation"),
	MP3("MP3"),
	VORBIS("OGG-Vorbis"),
	WMA("WMA");
    private String label;
	AudioCoding(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
