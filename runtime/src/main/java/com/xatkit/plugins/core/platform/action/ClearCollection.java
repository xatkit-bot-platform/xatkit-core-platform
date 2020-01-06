package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import java.util.Map;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * Clears the given collection.
 * <p>
 * This action does not remove the collection from the internal store, but removes all its entries, such as {@code
 * corePlatform.getCollection(collectionName).isEmpty() == true}.
 */
public class ClearCollection extends RuntimeAction<CorePlatform> {

    /**
     * The name of the collection to clear.
     */
    private String collectionName;

    /**
     * Constructs a {@link ClearCollection} instance with the provided {@code runtimePlatform}, {@code session},
     * and {@code collectionName}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection to clear
     * @throws NullPointerException if the provided {@code collectionName} is {@code null}
     */
    public ClearCollection(CorePlatform runtimePlatform, XatkitSession session, @Nonnull String collectionName) {
        super(runtimePlatform, session);
        checkNotNull(collectionName, "Cannot construct a %s from the provided collection name %s",
                this.getClass().getSimpleName(), collectionName);
        this.collectionName = collectionName;
    }

    /**
     * Clears the given collection.
     * <p>
     * This method does not remove the collection from the internal store, but removes all its entries, such as {@code
     * corePlatform.getCollection(collectionName).isEmpty() == true}.
     *
     * @return {@code null}
     */
    @Override
    protected Object compute() {
        Map<String, Object> collection = this.runtimePlatform.getCollection(collectionName);
        if (nonNull(collection)) {
            collection.clear();
        }
        return null;
    }
}
