package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * Puts the given {@code value} in the {@link Set} associated to the provided {@code key} in the given {@code
 * collection}.
 * <p>
 * This action creates a {@link Set} filled with the provided {@code value} if there is no {@link Set} associated to
 * the given {@code key}.
 * <p>
 * <b>Note</b>: this action throws an {@link IllegalStateException} if there is already a non-{@link Set} value
 * associated to the given {@code key}.
 *
 * @see StoreValue
 * @see StoreListValue
 */
public class StoreSetValue extends StoreValue {

    /**
     * Constructs a {@link StoreSetValue} instance with the provided {@code runtimePlatform}, {@code session}, {@code
     * collectionName}, {@code key}, and {@code value}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection associated to the value to store
     * @param key             the key associated to the value to store
     * @param value           the value to store
     * @throws NullPointerException if the provided {@code collectionName} or {@code key} is {@code null}
     */
    public StoreSetValue(CorePlatform runtimePlatform, XatkitSession session, @Nonnull String collectionName,
                         @Nonnull String key, @Nullable Object value) {
        super(runtimePlatform, session, collectionName, key, value);
    }

    /**
     * Puts the given {@code value} in the {@link Set} associated to the provided {@code key} in the given {@code
     * collection}.
     * <p>
     * This action creates a {@link Set} filled with the provided {@code value} if there is no {@link Set} associated to
     * the given {@code key}.
     *
     * @return the stored value
     * @throws IllegalStateException if there is already a non-{@link Set} value associated to the given {@code key}
     */
    @Override
    protected Object compute() {
        Map<String, Object> store = this.runtimePlatform.getOrCreateCollection(collectionName);
        if (store.containsKey(key)) {
            Object storedValue = store.get(key);
            if (storedValue instanceof Set) {
                ((Set<Object>) storedValue).add(value);
            } else {
                throw new IllegalStateException(MessageFormat.format("Trying to add a set value to a non-set store " +
                        "(collection={0}, key={1}, value={2})", collectionName, key, isNull(storedValue) ? "null" :
                        storedValue.getClass().getSimpleName()));
            }
        } else {
            Set<Object> storedValue = new HashSet<>();
            storedValue.add(value);
            store.put(key, storedValue);
        }
        return value;
    }


}
