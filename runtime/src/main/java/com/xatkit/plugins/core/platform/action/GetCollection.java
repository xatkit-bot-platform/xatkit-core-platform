package com.xatkit.plugins.core.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.core.platform.CorePlatform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * Retrieves a given collection from its name.
 * <p>
 * This action returns an unmodifiable {@link Map} representing the retrieved collection. To modify the content of a
 * collection use {@link StoreValue}.
 * <p>
 * This action returns {@code null} if the requested collection does not exist.
 */
public class GetCollection extends RuntimeAction<CorePlatform> {

    /**
     * The name of the collection to retrieve.
     */
    private String collectionName;

    /**
     * Constructs a {@link GetCollection} instance with the provided {@code runtimePlatform}, {@code session}, and
     * {@code collectionName}.
     *
     * @param runtimePlatform the {@link CorePlatform} containing this action
     * @param session         the {@link XatkitSession} associated to this action
     * @param collectionName  the name of the collection to retrieve
     * @throws NullPointerException if the provided {@code collectionName} is {@code null}
     */
    public GetCollection(CorePlatform runtimePlatform, XatkitSession session, @Nonnull String collectionName) {
        super(runtimePlatform, session);
        checkNotNull(collectionName, "Cannot construct a %s from the provided collection name %s",
                this.getClass().getSimpleName(), collectionName);
        this.collectionName = collectionName;
    }

    /**
     * Returns the name of the collection to retrieve.
     *
     * @return the name of the collection to retrieve
     */
    public String getCollectionName() {
        return this.collectionName;
    }

    /**
     * Retrieves the collection matching the provided {@code collectionName}.
     * <p>
     * This method returns an unmodifiable {@link Map} representing the retrieved collection. To modify a collection
     * use {@link StoreValue}.
     * <p>
     * This method returns {@code null} if the provided {@code collectionName} does not match any collection.
     *
     * @return an unmodifiable {@link Map} representing the retrieved collection, or {@code null} if such collection
     * does not exist
     */
    @Override
    protected @Nullable
    Object compute() {
        Map<String, Object> collection = this.runtimePlatform.getCollection(collectionName);
        return isNull(collection) ? null : Collections.unmodifiableMap(collection);
    }
}
