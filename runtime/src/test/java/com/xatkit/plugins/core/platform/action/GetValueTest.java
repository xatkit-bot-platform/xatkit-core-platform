package com.xatkit.plugins.core.platform.action;

import com.xatkit.AbstractActionTest;
import com.xatkit.plugins.core.platform.CorePlatform;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetValueTest extends AbstractActionTest<GetValue, CorePlatform> {

    @Override
    protected CorePlatform getPlatform() {
        return new CorePlatform(XATKIT_CORE);
    }

    @Test(expected = NullPointerException.class)
    public void constructNullKey() {
        action = new GetValue(platform, emptySession, null);
    }

    @Test
    public void constructValidKey() {
        action = new GetValue(platform, emptySession, "key");
        assertThatFieldsAreSet(action, "key", null);
    }

    @Test
    public void constructValidKeyNullDefaultValue() {
        action = new GetValue(platform, emptySession, "key", null);
        assertThatFieldsAreSet(action, "key", null);
    }

    @Test
    public void constructValidKeyNotNullDefaultValue() {
        action = new GetValue(platform, emptySession, "key", "default");
        assertThatFieldsAreSet(action, "key", "default");
    }

    @Test
    public void computeValueNotInStoreNoDefaultValue() {
        action = new GetValue(platform, emptySession, "key");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    @Test
    public void computeValueNotInStoreDefaultValue() {
        action = new GetValue(platform, emptySession, "key", "default");
        Object result = action.compute();
        assertThat(result).as("Result is default value").isEqualTo("default");
    }

    @Test
    public void computeValueInStoreNoDefaultValue() {
        platform.getStore().put("key", "value");
        action = new GetValue(platform, emptySession, "key");
        Object result = action.compute();
        assertThat(result).as("Valid result").isEqualTo("value");
    }

    @Test
    public void computeValueInStoreDefaultValue() {
        platform.getStore().put("key", "value");
        action = new GetValue(platform, emptySession, "key", "default");
        Object result = action.compute();
        assertThat(result).as("Valid result").isEqualTo("value");
    }

    @Test
    public void computeNullValueInStoreDefaultValue() {
        platform.getStore().put("key", null);
        action = new GetValue(platform, emptySession, "key", "default");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    private void assertThatFieldsAreSet(GetValue action, String key, Object defaultValue) {
        assertThat(action.getKey()).as("Key field correctly set").isEqualTo(key);
        assertThat(action.getDefaultValue()).as("Default value correctly set").isEqualTo(defaultValue);
    }

}
