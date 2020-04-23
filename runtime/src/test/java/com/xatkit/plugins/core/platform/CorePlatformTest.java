package com.xatkit.plugins.core.platform;

import com.xatkit.AbstractPlatformTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorePlatformTest extends AbstractPlatformTest<CorePlatform> {

    @Test(expected = NullPointerException.class)
    public void constructNullXatkitCore() {
        platform = new CorePlatform(null, configuration);
    }

    @Test
    public void constructValidXatkitCore() {
        platform = new CorePlatform(mockedXatkitCore, configuration);
        assertThat(platform.getStore()).as("Empty store").isEmpty();
    }
}
