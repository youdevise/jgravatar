package com.timgroup.jgravatar;

public enum GravatarDefaultImage {

	GRAVATAR_ICON(""),

	IDENTICON("identicon"),

	MONSTERID("monsterid"),

	WAVATAR("wavatar"),

	HTTP_404("404"),

	RETRO("retro");

	private final String code;

	private GravatarDefaultImage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
