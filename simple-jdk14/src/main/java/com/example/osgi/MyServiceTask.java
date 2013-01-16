/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.osgi;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.model.MyModel;
import com.yourinventit.dmc.api.moat.Moat;

/**
 * A sample application service. Not work by default. You need more
 * implementation for this to work.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyServiceTask implements Runnable {

    /**
     * {@link Logger}
     */
    private static final Logger LOGGER = Logger.getLogger("MyServiceTask");

    /**
     * {@link Moat}
     */
    private Moat moat;

    /**
     * @return the moat
     */
    public Moat getMoat() {
        return moat;
    }

    /**
     * @param moat
     *            the moat to set
     */
    public void setMoat(Moat moat) {
        this.moat = moat;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        if (isTimeToNotify()) {
            final MyModel model = new MyModel();
            model.setUid(String.valueOf(System.currentTimeMillis()));
            model.setStringValue("stringValue");
            model.setIntValue((int) (Math.random() * 500));
            try {
                getMoat()
                        .sendNotification(
                                // The job service id follow the URN format.
                                "urn:moat:485b593b-45ce-40e1-88e1-658fbf20e5fa:MyApp:alert-notificaton-request:1.0",
                                null, new Object[] { model });
            } catch (RuntimeException exception) {
                LOGGER.log(Level.SEVERE, "error!", exception);
            }
        }
    }

    /**
     * Whether or not to send a notification.
     * 
     * @return
     */
    protected boolean isTimeToNotify() {
        // TODO Implements when to notify.
        return true;
    }

}
