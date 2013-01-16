/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.yourinventit.dmc.api.moat.Constants;

/**
 * An example model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyModel implements Entity {

	private static final Logger LOGGER = Logger.getLogger("MyModel");

	private String uid;

	private int intValue;

	private long longValue;

	private boolean booleanValue;

	private String stringValue;

	private final List childList = new ArrayList();

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
	 * @return the longValue
	 */
	public long getLongValue() {
		return longValue;
	}

	/**
	 * @param longValue
	 *            the longValue to set
	 */
	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	/**
	 * @return the booleanValue
	 */
	public boolean isBooleanValue() {
		return booleanValue;
	}

	/**
	 * @param booleanValue
	 *            the booleanValue to set
	 */
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
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
	 * @return the childList
	 */
	public List getChildList() {
		return childList;
	}

	/**
	 * Clears the list of {@link MyChildModel} instance.
	 * 
	 * @param context
	 * @return true if updating this object is required after completing this
	 *         method invocation or false otherwise.
	 */
	public int clearChildModelsCommand(Map context) {
		LOGGER.finest("[START] clearChildModelsCommand()");
		childList.clear();
		LOGGER.finest("[END] clearChildModelsCommand()");
		return Constants.SYNC;
	}
}
