package com.xatkit.plugins.core.platform.action;

import com.xatkit.AbstractActionTest;
import com.xatkit.plugins.core.platform.CorePlatform;
import org.apache.commons.configuration2.BaseConfiguration;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetValueTest extends AbstractActionTest<GetValue, CorePlatform> {

    @Test(expected = NullPointerException.class)
    public void constructNullCollection() {
        action = new GetValue(platform, session, null, "key");
    }


    @Test(expected = NullPointerException.class)
    public void constructNullKey() {
        action = new GetValue(platform, session, "collection", null);
    }

    @Test
    public void constructValidCollectionAndKey() {
        action = new GetValue(platform, session, "collection", "key");
        assertThatFieldsAreSet(action, "collection", "key", null);
    }

    @Test
    public void constructValidCollectionAndKeyNullDefaultValue() {
        action = new GetValue(platform, session, "collection", "key", null);
        assertThatFieldsAreSet(action, "collection", "key", null);
    }

    @Test
    public void constructValidCollectionAndKeyNotNullDefaultValue() {
        action = new GetValue(platform, session, "collection", "key", "default");
        assertThatFieldsAreSet(action, "collection", "key", "default");
    }

    @Test
    public void computeCollectionDoesNotExistNoDefaultValue() {
        action = new GetValue(platform, session, "collection", "key");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    @Test
    public void computeCollectionDoesNotExistDefaultValue() {
        action = new GetValue(platform, session, "collection", "key", "default");
        Object result = action.compute();
        assertThat(result).as("Result is default value").isEqualTo("default");
    }

    @Test
    public void computeValueNotInCollectionNoDefaultValue() {
        /*
         * Create an empty collection.
         */
        platform.getOrCreateCollection("collection");
        action = new GetValue(platform, session, "collection", "key");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    @Test
    public void computeValueNotInCollectionDefaultValue() {
        platform.getOrCreateCollection("collection");
        action = new GetValue(platform, session, "collection", "key", "default");
        Object result = action.compute();
        assertThat(result).as("Result is default value").isEqualTo("default");
    }

    @Test
    public void computeValueInCollectionNoDefaultValue() {
        platform.getOrCreateCollection("collection").put("key", "value");
        action = new GetValue(platform, session, "collection", "key");
        Object result = action.compute();
        assertThat(result).as("Valid result").isEqualTo("value");
    }

    @Test
    public void computeValueInCollectionDefaultValue() {
        platform.getOrCreateCollection("collection").put("key", "value");
        action = new GetValue(platform, session, "collection", "key", "default");
        Object result = action.compute();
        assertThat(result).as("Valid result").isEqualTo("value");
    }

    @Test
    public void computeNullValueInCollectionDefaultValue() {
        platform.getOrCreateCollection("collection").put("key", null);
        action = new GetValue(platform, session, "collection", "key", "default");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    @Override
    protected CorePlatform getPlatform() {
        return new CorePlatform(mockedXatkitCore, new BaseConfiguration());
    }

    private void assertThatFieldsAreSet(GetValue action, String collection, String key, Object defaultValue) {
        assertThat(action.getCollectionName()).as("Collection field correctly set").isEqualTo(collection);
        assertThat(action.getKey()).as("Key field correctly set").isEqualTo(key);
        assertThat(action.getDefaultValue()).as("Default value correctly set").isEqualTo(defaultValue);
    }

}
