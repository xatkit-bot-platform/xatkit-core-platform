package com.xatkit.plugins.core.platform;

import com.xatkit.core.XatkitCore;
import com.xatkit.core.platform.RuntimePlatform;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.plugins.core.platform.action.GetDate;
import com.xatkit.plugins.core.platform.action.GetTime;
import org.apache.commons.configuration2.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String, Object> store;

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
     * Returns the {@link Map} used to store key/value bindings in the global bot scope.
     *
     * @return the {@link Map} used to store key/value bindings in the global bot scope
     */
    public Map<String, Object> getStore() {
        return this.store;
    }
}
