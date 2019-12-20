package com.xatkit.plugins.core.platform.action;

import com.xatkit.AbstractActionTest;
import com.xatkit.plugins.core.platform.CorePlatform;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreValueTest extends AbstractActionTest<StoreValue, CorePlatform> {

    @Override
    protected CorePlatform getPlatform() {
        return new CorePlatform(XATKIT_CORE);
    }

    @Test(expected = NullPointerException.class)
    public void constructNullKey() {
        action = new StoreValue(platform, emptySession, null, "value");
    }

    @Test
    public void constructValidKeyNullValue() {
        action = new StoreValue(platform, emptySession, "key", null);
        assertThatFieldsAreSet(action, "key", null);
    }

    @Test
    public void constructValidKeyValidValue() {
        action = new StoreValue(platform, emptySession, "key", "value");
        assertThatFieldsAreSet(action, "key", "value");
    }

    @Test
    public void computeValueNotInStore() {
        action = new StoreValue(platform, emptySession, "key", "value");
        action.compute();
        assertThat(platform.getStore().get("key")).as("Store contains the key/value pair").isEqualTo("value");
    }

    @Test
    public void computeValueInStore() {
        platform.getStore().put("key", "oldValue");
        action = new StoreValue(platform, emptySession, "key", "value");
        action.compute();
        assertThat(platform.getStore().get("key")).as("Store contains the new key/value pair").isEqualTo("value");
    }

    @Test
    public void computeValidKeyNullValue() {
        action = new StoreValue(platform, emptySession, "key", null);
        action.compute();
        assertThat(platform.getStore()).as("Store contains key").containsKey("key");
        assertThat(platform.getStore().get("key")).as("Key value is null").isNull();
    }

    private void assertThatFieldsAreSet(StoreValue action, String key, Object value) {
        assertThat(action.getKey()).as("Key field correctly set").isEqualTo(key);
        assertThat(action.getValue()).as("Value field correctly set").isEqualTo(value);
    }
}
