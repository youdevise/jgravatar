package com.timgroup.jgravatar;

import org.junit.Test;

import static org.mutabilitydetector.unittesting.AllowedReason.provided;
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

public class ImmutabilityTests {

    @Test
    public void gravatar() {
        assertInstancesOf(Gravatar.class, areImmutable(), 
                provided(GravatarDefaultImage.class).isAlsoImmutable(),
                provided(GravatarRating.class).isAlsoImmutable());
    }
    @Test
    public void gravatarDefaultImage() {
        assertInstancesOf(GravatarDefaultImage.class, areImmutable(), provided(String.class).isAlsoImmutable());
    }
    @Test
    public void gravatarRating() {
        assertInstancesOf(GravatarRating.class, areImmutable(), provided(String.class).isAlsoImmutable());
    }
    
}
