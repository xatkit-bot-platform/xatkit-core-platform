package com.xatkit.plugins.core.platform.action;

import com.xatkit.AbstractActionTest;
import com.xatkit.plugins.core.platform.CorePlatform;
import org.apache.commons.configuration2.BaseConfiguration;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreValueTest extends AbstractActionTest<StoreValue, CorePlatform> {

    @Override
    protected CorePlatform getPlatform() {
        return new CorePlatform(XATKIT_CORE, new BaseConfiguration());
    }

    @Test(expected = NullPointerException.class)
    public void constructNullCollection() {
        action = new StoreValue(platform, emptySession, null, "key", "value");
    }

    @Test(expected = NullPointerException.class)
    public void constructNullKey() {
        action = new StoreValue(platform, emptySession, null, "key", "value");
    }

    @Test
    public void constructValidCollectionAndKeyNullValue() {
        action = new StoreValue(platform, emptySession, "collection", "key", null);
        assertThatFieldsAreSet(action, "collection", "key", null);
    }

    @Test
    public void constructValidCollectionAndKeyValidValue() {
        action = new StoreValue(platform, emptySession, "collection", "key", "value");
        assertThatFieldsAreSet(action, "collection", "key", "value");
    }

    @Test
    public void computeCollectionDoesNotExist() {
        action = new StoreValue(platform, emptySession, "collection", "key", "value");
        action.compute();
        assertThat(platform.getCollection("collection")).as("The store contains the collection").isNotNull();
        assertThat(platform.getCollection("collection").get("key")).as("The collection contains the key/value pair").isEqualTo("value");
    }

    @Test
    public void computeValueNotInCollection() {
        /*
         * Create an empty collection.
         */
        platform.getOrCreateCollection("collection");
        action = new StoreValue(platform, emptySession, "collection", "key", "value");
        action.compute();
        assertThat(platform.getCollection("collection")).as("The store contains the collection").isNotNull();
        assertThat(platform.getCollection("collection").get("key")).as("Store contains the key/value pair").isEqualTo(
                "value");
    }

    @Test
    public void computeValueInCollection() {
        Map<String, Object> collection = platform.getOrCreateCollection("collection");
        collection.put("key", "oldValue");
        action = new StoreValue(platform, emptySession, "collection", "key", "value");
        action.compute();
        assertThat(platform.getCollection("collection")).as("The store contains the collection").isNotNull();
        assertThat(platform.getCollection("collection").get("key")).as("Store contains the key/value pair").isEqualTo(
                "value");
    }

    @Test
    public void computeValidKeyAndCollectionNullValue() {
        action = new StoreValue(platform, emptySession, "collection", "key", null);
        action.compute();
        assertThat(platform.getCollection("collection")).as("The store contains the collection").isNotNull();
        assertThat(platform.getCollection("collection")).as("Collection contains key").containsKey("key");
        assertThat(platform.getCollection("collection").get("key")).as("Key value is null").isNull();
    }

    private void assertThatFieldsAreSet(StoreValue action, String collection, String key, Object value) {
        assertThat(action.getCollectionName()).as("Collection field correctly set").isEqualTo(collection);
        assertThat(action.getKey()).as("Key field correctly set").isEqualTo(key);
        assertThat(action.getValue()).as("Value field correctly set").isEqualTo(value);
    }
}
