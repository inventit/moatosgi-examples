/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.osgi;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.example.moat.MyChildModelMapper;
import com.example.moat.MyModelMapper;
import com.example.model.MyChildModel;
import com.example.model.MyModel;
import com.yourinventit.dmc.api.moat.Moat;

import edu.emory.mathcs.backport.java.util.concurrent.ScheduledExecutorService;
import edu.emory.mathcs.backport.java.util.concurrent.ScheduledFuture;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

/**
 * A {@link BundleActivator} for this example bundle.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class SimpleActivator implements BundleActivator {

	/**
	 * {@link Logger}
	 */
	private static final Logger LOGGER = Logger.getLogger("SimpleActivator");

	/**
	 * {@link SimpleContextFactory}
	 */
	private SimpleContextFactory contextFactory = null;

	/**
	 * {@link ServiceReference}
	 */
	private ServiceReference moatReference = null;

	/**
	 * {@link ServiceReference}
	 */
	private ServiceReference scheduledExecutorServiceReference = null;

	/**
	 * {@link ScheduledFuture}
	 */
	private ScheduledFuture scheduledFuture = null;

	/**
	 * {@link Moat}
	 */
	private Moat moat = null;

	/**
	 * {@link ScheduledExecutorService}
	 */
	private ScheduledExecutorService scheduledExecutorService;

	/**
	 * @return the contextFactory
	 */
	protected SimpleContextFactory getContextFactory() {
		return contextFactory;
	}

	/**
	 * @return the moatReference
	 */
	protected ServiceReference getMoatReference() {
		return moatReference;
	}

	/**
	 * @return the scheduledExecutorServiceReference
	 */
	protected ServiceReference getScheduledExecutorServiceReference() {
		return scheduledExecutorServiceReference;
	}

	/**
	 * @return the scheduledFuture
	 */
	protected ScheduledFuture getScheduledFuture() {
		return scheduledFuture;
	}

	/**
	 * @return the moat
	 */
	protected Moat getMoat() {
		return moat;
	}

	/**
	 * @return the scheduledExecutorService
	 */
	protected ScheduledExecutorService getScheduledExecutorService() {
		return scheduledExecutorService;
	}

	/**
	 * Initializes {@link SimpleContextFactory}.
	 * 
	 * @param bundleContext
	 * @param myModelMapper
	 * @param myChildModelMapper
	 */
	private void initContextFactory(BundleContext bundleContext,
			MyModelMapper myModelMapper,
			MyChildModelMapper myChildModelMapper) {
		final SimpleContextFactory contextFactory = new SimpleContextFactory();
		contextFactory.setBundleContext(bundleContext);
		contextFactory.setMyModelMapper(myModelMapper);
		contextFactory.setMyChildModelMapper(myChildModelMapper);
		this.contextFactory = contextFactory;
	}

	/**
	 * Retrieves a {@link ServiceReference} of {@link Moat}.
	 * 
	 * @param bundleContext
	 */
	private void initMoatReference(BundleContext bundleContext) {
		final ServiceReference reference = bundleContext
				.getServiceReference(Moat.class.getName());
		this.moatReference = reference;
		this.moat = (Moat) bundleContext.getService(reference);
	}

	/**
	 * Initializes {@link MyModelMapper}.
	 * 
	 * @param bundleContext
	 * @return
	 */
	private MyModelMapper initMyModelModelMapper(BundleContext bundleContext) {
		final MyModelMapper modelMapper = new MyModelMapper();
		return modelMapper;
	}

	/**
	 * Initializes {@link MyChildModelMapper}.
	 * 
	 * @param bundleContext
	 * @return
	 */
	private MyChildModelMapper initMyChildModelModelMapper(
			BundleContext bundleContext) {
		final MyChildModelMapper modelMapper = new MyChildModelMapper();
		return modelMapper;
	}

	/**
	 * Initializes {@link ScheduledExecutorService}.
	 * 
	 * @param bundleContext
	 * @return
	 */
	private ScheduledExecutorService initScheduledExecutorService(
			BundleContext bundleContext) {
		final ServiceReference reference = bundleContext
				.getServiceReference(ScheduledExecutorService.class.getName());
		this.scheduledExecutorServiceReference = reference;
		this.scheduledExecutorService = (ScheduledExecutorService) bundleContext
				.getService(reference);
		return scheduledExecutorService;
	}

	/**
	 * Initializes {@link MyServiceTask}.
	 * 
	 * @param bundleContext
	 * @return
	 */
	private MyServiceTask initMyServiceTask(BundleContext bundleContext) {
		final MyServiceTask myServiceTask = new MyServiceTask();
		myServiceTask.setMoat(getMoat());
		return myServiceTask;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		LOGGER.fine("[START] start()");
		final MyModelMapper myModelPlugnDao = initMyModelModelMapper(context);
		final MyChildModelMapper myChildModelPlugnDao = initMyChildModelModelMapper(context);
		initMoatReference(context);
		initContextFactory(context, myModelPlugnDao, myChildModelPlugnDao);
		initScheduledExecutorService(context);

		final Moat moat = getMoat();
		moat.registerModel("urn:moat:123456", MyModel.class, myModelPlugnDao,
				getContextFactory());
		moat.registerModel("urn:moat:123456", MyChildModel.class,
				myChildModelPlugnDao, getContextFactory());

		this.scheduledFuture = getScheduledExecutorService()
				.scheduleWithFixedDelay(initMyServiceTask(context), 10, 60,
						TimeUnit.SECONDS);
		LOGGER.fine("[END] start()");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		LOGGER.fine("[START] stop()");
		if (getMoatReference() != null) {
			context.ungetService(getMoatReference());
		}
		if (getScheduledFuture() != null) {
			getScheduledFuture().cancel(true);
		}
		if (getScheduledExecutorServiceReference() != null) {
			context.ungetService(getScheduledExecutorServiceReference());
		}
		LOGGER.fine("[END] stop()");
	}
}
