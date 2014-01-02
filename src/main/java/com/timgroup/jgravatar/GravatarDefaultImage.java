package com.timgroup.jgravatar;

import javax.annotation.concurrent.Immutable;

/**
 * Specifies the default image to use when an email address has no matching Gravatar image.
 * 
 * @see https://en.gravatar.com/site/implement/images/#default-image
 */
@Immutable
public final class GravatarDefaultImage {

	public static final GravatarDefaultImage GRAVATAR_ICON = new GravatarDefaultImage("");

	public static final GravatarDefaultImage HTTP_404 = new GravatarDefaultImage("404");

	public static final GravatarDefaultImage MYSTERY_MAN = new GravatarDefaultImage("mm");
	
	public static final GravatarDefaultImage IDENTICON = new GravatarDefaultImage("identicon");
	
	public static final GravatarDefaultImage MONSTERID = new GravatarDefaultImage("monsterid");

	public static final GravatarDefaultImage WAVATAR = new GravatarDefaultImage("wavatar");
	
	public static final GravatarDefaultImage RETRO = new GravatarDefaultImage("retro");

	public static final GravatarDefaultImage BLANK = new GravatarDefaultImage("blank");

	private final String code;

	/**
	 * Create a new {@link GravatarDefaultImage} with the specific code. The code is used as the
	 * <code>default</code> URL parameter.
	 * <br>
	 * The Gravatar API allows specifying your own image as the default. To do this, pass an 
	 * encoded URL as the <code>code</code> parameter. For further details, see 
	 * <a href="https://en.gravatar.com/site/implement/images/#default-image">here</a>.
	 * <br>
	 * This should be used when a new default image is added to the Gravatar API, and 
	 * this library has not yet been updated to include it. Otherwise, the acceptable default
	 * images are available via the public constants of this class.
	 * <br>
	 * 
	 * @see GravatarDefaultImage#GRAVATAR_ICON
	 * @see GravatarDefaultImage#HTTP_404
	 * @see GravatarDefaultImage#MYSTERY_MAN
	 * @see GravatarDefaultImage#IDENTICON
	 * @see GravatarDefaultImage#MONSTERID
	 * @see GravatarDefaultImage#WAVATAR
	 * @see GravatarDefaultImage#RETRO
	 * @see GravatarDefaultImage#BLANK
	 */
	public GravatarDefaultImage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
