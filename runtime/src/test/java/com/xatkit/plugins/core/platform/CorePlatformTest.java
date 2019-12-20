package com.xatkit.plugins.core.platform;

import com.xatkit.AbstractXatkitTest;
import com.xatkit.core.XatkitCore;
import com.xatkit.stubs.StubXatkitCore;
import org.apache.commons.configuration2.BaseConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class CorePlatformTest extends AbstractXatkitTest {

    private static XatkitCore XATKIT_CORE;

    @BeforeClass
    public static void setUpBeforeClass() {
        XATKIT_CORE = new StubXatkitCore();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (nonNull(XATKIT_CORE) && !XATKIT_CORE.isShutdown()) {
            XATKIT_CORE.shutdown();
        }
    }

    private CorePlatform corePlatform;

    @Test(expected = NullPointerException.class)
    public void constructNullXatkitCore() {
        corePlatform = new CorePlatform(null, new BaseConfiguration());
    }

    @Test
    public void constructValidXatkitCore() {
        corePlatform = new CorePlatform(XATKIT_CORE, new BaseConfiguration());
        assertThat(corePlatform.getStore()).as("Empty store").isEmpty();
    }
}
