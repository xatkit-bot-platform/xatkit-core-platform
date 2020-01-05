package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * Retrieves the value with the provided {@code key} in the given {@code collectionName}.
 */
public class GetValue extends RuntimeAction<CorePlatform> {

    /**
     * The name of the collection to retrieve the associated value from.
     */
    private String collectionName;

    /**
     * The key associated to the value to retrieve.
     */
    private String key;

    /**
     * The value to return if the {@link CorePlatform}'s {@code store} does not contain the provided {@code key}.
     */
    private Object defaultValue;

    /**
     * Constructs a {@link GetValue} instance with the provided {@code runtimePlatform}, {@code session}, and {@code
     * key}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection to retrieve the associated value from
     * @param key             the key to retrieve the associated value from the store
     * @throws NullPointerException if the provided {@code key} is {@code null}
     */
    public GetValue(CorePlatform runtimePlatform, XatkitSession session,
                    @Nonnull String collectionName, @Nonnull String key) {
        this(runtimePlatform, session, collectionName, key, null);
    }

    /**
     * Constructs a {@link GetValue} instance with the provided {@code runtimePlatform}, {@code session}, {@code key}
     * , and {@code defaultValue}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection to retrieve the associated value from
     * @param key             the key to retrieve the associated value from the store
     * @param defaultValue    the value to return if the {@link CorePlatform}'s {@code store} does not contain the
     *                        provided {@code key}
     * @throws NullPointerException if the provided {@code key} is {@code null}
     */
    public GetValue(CorePlatform runtimePlatform, XatkitSession session, @Nonnull String collectionName,
                    @Nonnull String key, @Nullable Object defaultValue) {
        super(runtimePlatform, session);
        checkNotNull(key, "Cannot construct a %s from the provided key %s", this.getClass().getSimpleName(), key);
        checkNotNull(collectionName, "Cannot construct a %s from the provided collection name %s",
                this.getClass().getSimpleName(), collectionName);
        this.collectionName = collectionName;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the name of the collection to retrieve the associated value from.
     *
     * @return the name of the collection to retrieve the associated value from
     */
    public String getCollectionName() {
        return this.collectionName;
    }

    /**
     * Returns the key to retrieve the associated value from.
     *
     * @return the key to retrieve the associated value from
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the default value returned if the store does not contain the provided {@code key}.
     *
     * @return the default value returned if the store does not contain the provided {@code key}
     */
    public Object getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Retrieves the value associated to the provided {@code key} in the given {@code collection} if it exists, or the
     * {@code defaultValue} otherwise.
     * <p>
     * This method returns the {@code defaultValue} if the provided {@code collection} does not exist or if the
     * provided {@code key} is not contained in the corresponding {@code collection}.
     * <p>
     * <b>Note</b>: this method returns {@code null} (not the {@code defaultValue}) if {@code key=null} has been
     * explicitly put in the store.
     *
     * @return the value associated to the provided {@code key} if it exists, or the {@code defaultValue}.
     */
    @Override
    protected Object compute() {
        Map<String, Object> collection = this.runtimePlatform.getCollection(collectionName);
        if (nonNull(collection)) {
            if (collection.containsKey(key)) {
                return collection.get(key);
            } else {
                return this.defaultValue;
            }
        } else {
            return this.defaultValue;
        }
    }
}
