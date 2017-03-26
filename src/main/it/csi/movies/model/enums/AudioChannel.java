package it.csi.movies.model.enums;

public enum AudioChannel {
	MONO("1.0 - Mono"),
	STEREO("2.0 - Stereo"),
	THREE("3.0 - Dolby Surround"),
	FOUR("4.0 - Dolby Prologic"),
	FIVE("5.1 - Dolby Prologic II/Digital/DTS"),
	SIX("6.1"),
	SEVEN("7.1 - Dolby Digital Plus/DTS-HD/Dolby TrueHD");
    private String label;
	AudioChannel(String label) {
       this.label = label;
	}
	public String getLabel() {
       return label;
	}
}
