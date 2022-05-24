package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * Workaround bug in Resteasy JsonBindingProvider
 */
public class ProxyInputStream extends FilterInputStream {

    protected ProxyInputStream(InputStream in) {
        super(InputStream.nullInputStream());
    }
}
