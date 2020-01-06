package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Puts the given {@code key=value} pair in the provided {@code collection}.
 */
public class StoreValue extends RuntimeAction<CorePlatform> {

    /**
     * The collection associated to the value to store.
     */
    private String collectionName;

    /**
     * The key associated to the value to store.
     */
    private String key;

    /**
     * The value to store.
     */
    private Object value;

    /**
     * Constructs a {@link StoreValue} instance with the provided {@code runtimePlatform}, {@code session}, {@code
     * key}, and {@code value}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection associated to the value to store
     * @param key             the key associated to the value to store
     * @param value           the value to store
     * @throws NullPointerException if the provided {@code collectionName} or {@code key} is {@code null}
     */
    public StoreValue(CorePlatform runtimePlatform, XatkitSession session,
                      @Nonnull String collectionName, @Nonnull String key, @Nullable Object value) {
        super(runtimePlatform, session);
        checkNotNull(collectionName, "Cannot construct a %s from the provided collection name %s",
                this.getClass().getSimpleName(), collectionName);
        checkNotNull(key, "Cannot construct a %s from the provided key %s", this.getClass().getSimpleName(), key);
        this.collectionName = collectionName;
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the name of the collection associated to the value to store.
     *
     * @return the name of the collection associated to the value to store
     */
    public String getCollectionName() {
        return this.collectionName;
    }

    /**
     * Returns the key associated to the value to store.
     *
     * @return the key associated to the value to store
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the value to store.
     *
     * @return the value to store
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Puts the given {@code key=value} pair in the provided {@code collection}.
     * <p>
     * This method overwrites any value already associated to the given {@code key}.
     *
     * @return the stored value
     */
    @Override
    protected Object compute() {
        Map<String, Object> store = this.runtimePlatform.getOrCreateCollection(collectionName);
        store.put(key, value);
        return value;
    }
}
