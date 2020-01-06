package com.xatkit.plugins.core.platform.action;

import com.xatkit.AbstractActionTest;
import com.xatkit.plugins.core.platform.CorePlatform;
import org.apache.commons.configuration2.BaseConfiguration;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GetCollectionTest extends AbstractActionTest<GetCollection, CorePlatform> {

    @Override
    protected CorePlatform getPlatform() {
        return new CorePlatform(XATKIT_CORE, new BaseConfiguration());
    }

    @Test(expected = NullPointerException.class)
    public void constructNullCollectionName() {
        action = new GetCollection(platform, emptySession, null);
    }

    @Test
    public void constructValidCollectionName() {
        action = new GetCollection(platform, emptySession, "collection");
        assertThat(action.getCollectionName()).as("Valid collection name").isEqualTo("collection");
    }

    @Test
    public void computeCollectionDoesNotExist() {
        action = new GetCollection(platform, emptySession, "collection");
        Object result = action.compute();
        assertThat(result).as("Result is null").isNull();
    }

    @Test
    public void computeCollectionExistsAndIsEmpty() {
        platform.getOrCreateCollection("collection");
        action = new GetCollection(platform, emptySession, "collection");
        Object result = action.compute();
        assertThatCollectionIsValid(result);
        Map<String, Object> mapResult = (Map<String, Object>) result;
        assertThat(mapResult).as("Result Map is empty").isEmpty();
    }

    @Test
    public void computeCollectionExistsNotEmpty() {
        platform.getOrCreateCollection("collection").put("key", "value");
        action = new GetCollection(platform, emptySession, "collection");
        Object result = action.compute();
        assertThatCollectionIsValid(result);
        Map<String, Object> mapResult = (Map<String, Object>) result;
        assertThat(mapResult).as("Result Map contains a single element").hasSize(1);
        assertThat(mapResult).as("Result Map contains the expected key").containsKey("key");
        assertThat(mapResult.get("key")).as("Result Map contains the expected value").isEqualTo("value");
    }

    private void assertThatCollectionIsValid(Object collection) {
        assertThat(collection).as("Result is a Map").isInstanceOf(Map.class);
        Map<String, Object> mapResult = (Map<String, Object>) collection;
        assertThatExceptionOfType(UnsupportedOperationException.class).as("Map is unmodifiable").
                isThrownBy(() -> mapResult.put("key", "value"));
    }
}
