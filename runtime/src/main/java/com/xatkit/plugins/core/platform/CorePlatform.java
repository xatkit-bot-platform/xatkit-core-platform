package com.xatkit.plugins.core.platform;

import com.xatkit.core.XatkitCore;
import com.xatkit.core.platform.RuntimePlatform;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.plugins.core.platform.action.GetDate;
import com.xatkit.plugins.core.platform.action.GetTime;
import org.apache.commons.configuration2.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * A {@link RuntimePlatform} concrete implementation providing core functionality that can be used in execution models.
 * <p>
 * This runtimePlatform defines a set of high level {@link RuntimeAction}s:
 * <ul>
 * <li>{@link GetTime}: return the current time</li>
 * <li>{@link GetDate}: return the current date</li>
 * </ul>
 * <p>
 * This class is part of xatkit's core platforms, and can be used in an execution model by importing the
 * <i>CorePlatform</i> package.
 *
 * @see GetTime
 * @see GetDate
 */
public class CorePlatform extends RuntimePlatform {

    /**
     * The internal store used to persist bot collections.
     */
    private Map<String, Map<String, Object>> store;

    /**
     * Constructs a new {@link CorePlatform} from the provided {@link XatkitCore}.
     *
     * @param xatkitCore    the {@link XatkitCore} instance associated to this runtimePlatform
     * @param configuration the {@link Configuration} used to setup this platform
     * @throws NullPointerException if the provided {@code xatkitCore} is {@code null}
     */
    public CorePlatform(XatkitCore xatkitCore, Configuration configuration) {
        super(xatkitCore, configuration);
        this.store = new HashMap<>();
    }

    /**
     * Returns the internal store used to persist bot collections.
     *
     * @return the internal store used to persist bot collections.
     */
    protected Map<String, Map<String, Object>> getStore() {
        return this.store;
    }

    /**
     * Returns the {@link Map} containing the {@code key/value} bindings associated to the given {@code collectionName}.
     *
     * @param collectionName the name of the collection to get the bindings from
     * @return the {@link Map} containing the {@code key/value} bindings associated to the given {@code collectionName}
     */
    public @Nullable
    Map<String, Object> getCollection(@Nonnull String collectionName) {
        return this.store.get(collectionName);
    }

    /**
     * Gets or creates the {@link Map} containing the {@code key/value} bindings associated to the given {@code
     * collectionName}.
     * <p>
     * This method creates an empty {@link Map} if it does not exist.
     *
     * @param collectionName the name of the collection to get the bindings from
     * @return the {@link Map} containing the {@code key/value} bindings associated to the given {@code collectionName}
     */
    public @Nonnull
    Map<String, Object> getOrCreateCollection(@Nonnull String collectionName) {
        Map<String, Object> result = this.getCollection(collectionName);
        if (isNull(result)) {
            result = new HashMap<>();
            this.store.put(collectionName, result);
        }
        return result;
    }
}
