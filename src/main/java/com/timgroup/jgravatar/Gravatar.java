package com.timgroup.jgravatar;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Joiner;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * A gravatar is a dynamic image resource that is requested from the
 * gravatar.com server. This class calculates the gravatar url and fetches
 * gravatar images. See http://en.gravatar.com/site/implement/url .
 * </p>
 * This class is immutable and thread-safe, Gravatar objects can be shared.
 *
 * <p>
 * Usage example:<br>
 *
 * <pre>
 * <code>
 * Gravatar gravatar = new Gravatar().setSize(50)
 *                                   .setRating(GravatarRating.GENERAL_AUDIENCES)
 *                                   .setDefaultImage(GravatarDefaultImage.IDENTICON);
 *
 * String url = gravatar.getUrl("iHaveAn@email.com");
 * byte[] jpg = gravatar.download("info@ralfebert.de");
 * </code>
 * </pre>
 *
 * Note, each <code>setX</code> method returns a new instance of
 * <code>Gravatar</code>. The default constructor ({@link #Gravatar()} is
 * equivalent to
 * <code>new Gravatar(DEFAULT_SIZE, DEFAULT_RATING, DEFAULT_DEFAULT_IMAGE);</code>
 * <p>
 *
 * @see Gravatar#DEFAULT_SIZE
 * @see Gravatar#DEFAULT_RATING
 * @see Gravatar#DEFAULT_DEFAULT_IMAGE
 *
 * @author Ralf Ebert
 * @author Graham Allan
 *
 */
@Immutable
public final class Gravatar {

    private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    private final static String GRAVATAR_HTTPS_URL = "https://www.gravatar.com/avatar/";

    /**
     * Default choice for size of image.
     */
    public final static int DEFAULT_SIZE = 80;

    /**
     * @see GravatarRating#GENERAL_AUDIENCES
     */
    public static final GravatarRating DEFAULT_RATING = GravatarRating.GENERAL_AUDIENCES;

    /**
     * @see GravatarDefaultImage#HTTP_404
     */
    public static final GravatarDefaultImage DEFAULT_DEFAULT_IMAGE = GravatarDefaultImage.HTTP_404;

    /**
     * Default choice for size of image.
     */
    public final static Boolean DEFAULT_HTTPS = Boolean.FALSE;

    private final int size;
    private final GravatarRating rating;
    private final GravatarDefaultImage defaultImage;
    private final Boolean https;

    /**
     *
     * @param size size in pixels
     * @param rating rating
     * @param defaultImage default image
     * @param https is https enabled
     */
    public Gravatar(int size, GravatarRating rating, GravatarDefaultImage defaultImage, Boolean https) {
        this.size = size;
        this.rating = rating;
        this.defaultImage = defaultImage;
        this.https = https;
    }

    /**
     * Creates a new Gravatar instance using the default settings.
     */
    public Gravatar() {
        this(DEFAULT_SIZE, DEFAULT_RATING, DEFAULT_DEFAULT_IMAGE, DEFAULT_HTTPS);
    }


    /**
     * Creates a new Gravatar instance using the default settings but specifying if https is enabled.
     */
    public Gravatar(Boolean https) {
        this(DEFAULT_SIZE, DEFAULT_RATING, DEFAULT_DEFAULT_IMAGE, https);
    }

    /**
     * Specify a gravatar size between 1 and 2048 pixels. If you omit this, a
     * default size of 80 pixels is used.
     *
     * @param sizeInPixels size the new Gravatar instance will use
     * @return new instance of Gravatar with given size
     */
    public Gravatar setSize(int sizeInPixels) {
        checkArgument(sizeInPixels >= 1 && sizeInPixels <= 2048,
                "sizeInPixels needs to be between 1 and 2048");
        return new Gravatar(sizeInPixels, rating, defaultImage,https);
    }

    /**
     * Specify a rating to ban gravatar images with explicit content.
     *
     * @param rating rating the new Gravatar instance will use
     * @return new instance of Gravatar with given rating
     */
    public Gravatar setRating(GravatarRating rating) {
        checkNotNull(rating, "rating");
        return new Gravatar(size, rating, defaultImage,https);
    }

    /**
     * Specify the default image to be produced if no gravatar image was found.
     *
     * @param defaultImage the default image the new Gravatar instance will use
     * @return new instance of Gravatar with given default image
     */
    public Gravatar setDefaultImage(GravatarDefaultImage defaultImage) {
        checkNotNull(defaultImage, "defaultImage");
        return new Gravatar(size, rating, defaultImage,https);
    }

    /**
     * Returns the Gravatar URL for the given email address.
     *
     * @param email the email address to query for
     * @return url for the given email address
     */
    public String getUrl(String email) {
        checkNotNull(email, "email");

        // hexadecimal MD5 hash of the requested user's lowercased email address
        // with all whitespace trimmed
        String emailHash = Hashing.md5()
                                  .hashString(email.toLowerCase().trim(), Charset.forName("UTF-8"))
                                  .toString();
        
        String params = formatUrlParameters();

        String urlParams = emailHash + ".jpg" + params;

        return https ? (GRAVATAR_HTTPS_URL + urlParams) : (GRAVATAR_URL + urlParams);
    }

    /**
     * Downloads the gravatar for the given URL using Java {@link URL} and
     * returns a byte array containing the gravatar jpg, returns null if no
     * gravatar was found.
     *
     * @param email the email address to query for
     * @return bytes of the jpg image
     */
    public byte[] download(String email) throws GravatarDownloadException {
        InputStream stream = null;
        try {
            URL url = new URL(getUrl(email));
            stream = url.openStream();
            return ByteStreams.toByteArray(stream);
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            throw new GravatarDownloadException(e);
        } finally {
            Closeables.closeQuietly(stream);
        }
    }

    private String formatUrlParameters() {
        List<String> params = new ArrayList<String>();

        if (size != DEFAULT_SIZE)
            params.add("s=" + size);
        if (rating != DEFAULT_RATING)
            params.add("r=" + rating.getCode());
        if (defaultImage != GravatarDefaultImage.GRAVATAR_ICON)
            params.add("d=" + defaultImage.getCode());

        if (params.isEmpty())
            return "";
        else
            return "?" + Joiner.on("&").join(params.iterator());
    }
}
