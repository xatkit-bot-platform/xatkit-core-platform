package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Puts the given {@code key=value} pair in the {@link CorePlatform}'s {@code store}.
 */
public class StoreValue extends RuntimeAction<CorePlatform> {

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
     * @param key             the key associated to the value to store
     * @param value           the value to store
     * @throws NullPointerException if the provided {@code key} is {@code null}
     */
    public StoreValue(CorePlatform runtimePlatform, XatkitSession session, @Nonnull String key,
                      @Nullable Object value) {
        super(runtimePlatform, session);
        checkNotNull(key, "Cannot construct a %s from the provided key %s", this.getClass().getSimpleName(), key);
        this.key = key;
        this.value = value;
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
     * Puts the given {@code key=value} pair in the {@link CorePlatform}'s {@code store}.
     * <p>
     * This method overwrites any value already associated to the given {@code key}.
     *
     * @return the stored value
     */
    @Override
    protected Object compute() {
        this.runtimePlatform.getStore().put(key, value);
        return value;
    }
}
