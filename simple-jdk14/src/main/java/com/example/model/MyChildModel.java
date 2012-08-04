/*
 * Copyright (C) 2012 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.model;

import java.util.Map;
import java.util.logging.Logger;

import com.example.moat.MyModelPluginDao;

/**
 * An example model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyChildModel implements Entity {

    private static final Logger LOGGER = Logger.getLogger("MyChildModel");

    private String uid;

    private int intValue;

    private String stringValue;

    private MyModel myModel;

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the intValue
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * @param intValue
     *            the intValue to set
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * @return the stringValue
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * @param stringValue
     *            the stringValue to set
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * @return the myModel
     */
    public MyModel getMyModel() {
        return myModel;
    }

    /**
     * @param myModel
     *            the myModel to set
     */
    public void setMyModel(MyModel myModel) {
        this.myModel = myModel;
    }

    /**
     * An example operation manipulating object relationships.
     * 
     * @param context
     * @return true if updating this object is required after completing this
     *         method invocation or false otherwise.
     */
    public boolean associateMyModelSync(Map context) {
        LOGGER.finest("[START] associateMyModelSync()");
        final String uid = (String) context.get("data");
        final MyModelPluginDao myModelPluginDao = (MyModelPluginDao) context
                .get(MyModelPluginDao.class);
        final Object object = myModelPluginDao.findByUid(uid);
        if (object != null) {
            if (getMyModel() != null) {
                getMyModel().getChildList().remove(this);
            }
            final MyModel model = (MyModel) object;
            setMyModel(model);
            LOGGER.finest("[END] associateMyModelSync() with Update!");
            return true;
        } else {
            LOGGER.finest("[END] associateMyModelSync()");
            return false;
        }
    }

}
