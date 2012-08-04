/*
 * Copyright (C) 2012 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.osgi;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

import com.example.moat.MyChildModelPluginDao;
import com.example.moat.MyModelPluginDao;
import com.yourinventit.dmc.api.moat.ContextFactory;

/**
 * A {@link ContextFactory} providing a context including {@link BundleContext}.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
class SimpleContextFactory implements ContextFactory {

    /**
     * {@link BundleContext}
     */
    private BundleContext bundleContext;

    /**
     * {@link MyModelPluginDao}
     */
    private MyModelPluginDao myModelPluginDao;

    /**
     * {@link MyChildModelPluginDao}
     */
    private MyChildModelPluginDao myChildModelPluginDao;

    /**
     * @param bundleContext
     *            the bundleContext to set
     */
    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    /**
     * @param myModelPluginDao
     *            the myModelPluginDao to set
     */
    public void setMyModelPluginDao(MyModelPluginDao myModelPluginDao) {
        this.myModelPluginDao = myModelPluginDao;
    }

    /**
     * @param myChildModelPluginDao
     *            the myChildModelPluginDao to set
     */
    public void setMyChildModelPluginDao(
            MyChildModelPluginDao myChildModelPluginDao) {
        this.myChildModelPluginDao = myChildModelPluginDao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.ContextFactory#createExecutionContext(java.lang.Object,
     *      java.lang.reflect.Method)
     */
    public Map createExecutionContext(Object model, Method method) {
        final Map context = new HashMap();
        context.put(BundleContext.class, bundleContext);
        context.put(MyModelPluginDao.class, myModelPluginDao);
        context.put(MyChildModelPluginDao.class, myChildModelPluginDao);
        return context;
    }
}
