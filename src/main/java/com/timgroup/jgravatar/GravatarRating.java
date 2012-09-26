package com.timgroup.jgravatar;

public enum GravatarRating {

	GENERAL_AUDIENCES("g"),

	PARENTAL_GUIDANCE_SUGGESTED("pg"),

	RESTRICTED("r"),

	XPLICIT("x");

	private final String code;

	private GravatarRating(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}