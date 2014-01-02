package com.timgroup.jgravatar;

import javax.annotation.concurrent.Immutable;

/**
 * Specifies what rating is appropriate for a given request.
 * 
 * @see https://en.gravatar.com/site/implement/images/#rating
 */
@Immutable
public final class GravatarRating {

	public static final GravatarRating GENERAL_AUDIENCES = new GravatarRating("g");

	public static final GravatarRating PARENTAL_GUIDANCE_SUGGESTED = new GravatarRating("pg");

	public static final GravatarRating RESTRICTED = new GravatarRating("r");

	public static final GravatarRating XPLICIT = new GravatarRating("x");

	private final String code;

	/**
	 * Create a new {@link GravatarRating} with the specific code. The code is used as the
	 * <code>rating</code> URL parameter.
	 * <br>
	 * Should only need to be used when a new rating is added to the Gravatar API, and 
	 * this library has not yet been updated to include it.
	 * 
	 * @see GravatarRating#GENERAL_AUDIENCES
	 * @see GravatarRating#PARENTAL_GUIDANCE_SUGGESTED
	 * @see GravatarRating#RESTRICTED
	 * @see GravatarRating#XPLICIT
	 * 
	 */
	public GravatarRating(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}