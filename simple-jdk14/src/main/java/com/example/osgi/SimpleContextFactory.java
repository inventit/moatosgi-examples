/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.osgi;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

import com.example.moat.MyChildModelMapper;
import com.example.moat.MyModelMapper;
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
	 * {@link MyModelMapper}
	 */
	private MyModelMapper myModelMapper;

	/**
	 * {@link MyChildModelMapper}
	 */
	private MyChildModelMapper myChildModelMapper;

	/**
	 * @param bundleContext
	 *            the bundleContext to set
	 */
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/**
	 * @param myModelMapper
	 *            the myModelMapper to set
	 */
	public void setMyModelMapper(MyModelMapper myModelMapper) {
		this.myModelMapper = myModelMapper;
	}

	/**
	 * @param myChildModelMapper
	 *            the myChildModelMapper to set
	 */
	public void setMyChildModelMapper(MyChildModelMapper myChildModelMapper) {
		this.myChildModelMapper = myChildModelMapper;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ContextFactory#createExecutionContext(java.lang.Object,
	 *      java.lang.String)
	 */
	public Map createExecutionContext(Object model, String methodName) {
		final Map context = new HashMap();
		context.put(BundleContext.class, bundleContext);
		context.put(MyModelMapper.class, myModelMapper);
		context.put(MyChildModelMapper.class, myChildModelMapper);
		return context;
	}
}
