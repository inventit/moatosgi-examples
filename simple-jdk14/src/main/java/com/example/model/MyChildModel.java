/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.model;

import java.util.Map;
import java.util.logging.Logger;

import com.example.moat.MyModelMapper;
import com.yourinventit.dmc.api.moat.CommandException;
import com.yourinventit.dmc.api.moat.Constants;

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
	public int associateMyModelCommand(Map context) {
		LOGGER.finest("[START] associateMyModelSync()");
		final String uid = (String) context.get("data");
		final MyModelMapper myModelModelMapper = (MyModelMapper) context
				.get(MyModelMapper.class);
		final Object object = myModelModelMapper.findByUid(uid);
		if (object != null) {
			if (getMyModel() != null) {
				getMyModel().getChildList().remove(this);
			}
			final MyModel model = (MyModel) object;
			setMyModel(model);
			LOGGER.finest("[END] associateMyModelCommand() --> ASYNC");
			return Constants.ASYNC;
		} else {
			throw new CommandException(-1234, "local message");
		}
	}

}
