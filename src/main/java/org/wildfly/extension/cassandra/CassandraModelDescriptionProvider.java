package org.wildfly.extension.cassandra;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIBE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OPERATION_NAME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REPLY_PROPERTIES;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.TYPE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.VALUE_TYPE;

import java.util.Locale;
import java.util.ResourceBundle;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.as.controller.descriptions.common.CommonDescriptions;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 * A generic handler recursively creating add operations for a managed resource using it's
 * attributes as the request-parameters.
 *
 * @author Emanuel Muckenhuber
 */
public class CassandraModelDescriptionProvider implements DescriptionProvider {

    public static final CassandraModelDescriptionProvider INSTANCE = new CassandraModelDescriptionProvider();

    protected CassandraModelDescriptionProvider() {
        //
    }
    
    @Override
    public ModelNode getModelDescription(Locale locale) {
    	final ResourceBundle bundle = getResourceBundle(locale);
        final ModelNode root = new ModelNode();
        root.get(OPERATION_NAME).set(DESCRIBE);
        root.get(DESCRIPTION).set(bundle.getString("subsystem.describe"));
        root.get(REPLY_PROPERTIES, TYPE).set(ModelType.LIST);
        root.get(REPLY_PROPERTIES, VALUE_TYPE).set(ModelType.OBJECT);
        return root;
    }
    
    private static final String RESOURCE_NAME = CommonDescriptions.class.getPackage().getName() + ".LocalDescriptions";

    private static ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }    
    
}
