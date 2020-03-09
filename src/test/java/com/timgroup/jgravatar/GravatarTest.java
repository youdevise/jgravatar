package com.timgroup.jgravatar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GravatarTest {

	@Test
	public void testGetImageUrlDefaults() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=404", new Gravatar().getUrl("iHaveAn@email.com"));
		assertEquals("http://www.gravatar.com/avatar/fa8771dec9da9299afed9ffce70c2c18.jpg?d=404", new Gravatar().getUrl("info@ralfebert.de"));
	}

	@Test
	public void testGetHttpsImageUrlDefaults() {
		assertEquals("https://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=404", new Gravatar(true).getUrl("iHaveAn@email.com"));
		assertEquals("https://www.gravatar.com/avatar/fa8771dec9da9299afed9ffce70c2c18.jpg?d=404", new Gravatar(true).getUrl("info@ralfebert.de"));
	}

	@Test
	public void testGetImageUrlSize() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=100&d=404",
				new Gravatar().setSize(100).getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlRating() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?r=pg&d=404", 
		        new Gravatar().setRating(GravatarRating.PARENTAL_GUIDANCE_SUGGESTED).getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlDefaultImage() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?d=identicon",
				new Gravatar().setDefaultImage(GravatarDefaultImage.IDENTICON).getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testGetImageUrlCombined() {
		assertEquals("http://www.gravatar.com/avatar/3b3be63a4c2a439b013787725dfce802.jpg?s=123&d=identicon",
				new Gravatar().setSize(123).setDefaultImage(GravatarDefaultImage.IDENTICON).getUrl("iHaveAn@email.com"));
	}

	@Test
	public void testDownload() {
		Gravatar g = new Gravatar();
		byte[] bytes = g.download("info@ralfebert.de");
		assertTrue("content present", bytes.length>100);

		assertNull("null for no gravatar by default", g.download("doesntexist@example.com"));

		bytes = g.setDefaultImage(GravatarDefaultImage.IDENTICON).download("info@ralfebert.de");
		assertTrue("content present", bytes.length>100);
	}

}