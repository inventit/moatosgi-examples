/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.moat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Entity;
import com.example.model.MyModel;
import com.yourinventit.dmc.api.moat.ModelMapper;

/**
 * A {@link ModelMapper} for {@link MyModel} model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyModelMapper implements ModelMapper {

	/**
	 * A {@link Map} containing {@link Entity} instances.
	 */
	private final Map myModelStore = new HashMap();

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#add(java.lang.String)
	 */
	public Object add(String uid) {
		final Entity entity = create();
		entity.setUid(uid);
		myModelStore.put(uid, entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#update(java.lang.Object)
	 */
	public Object update(Object object) {
		final Entity entity = (Entity) object;
		myModelStore.put(entity.getUid(), myModelStore);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#updateFields(java.lang.Object,
	 *      java.lang.String[])
	 */
	public void updateFields(Object object, String[] updateFields) {
		update(object);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#remove(java.lang.String)
	 */
	public void remove(String uid) {
		myModelStore.remove(uid);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#findByUid(java.lang.String)
	 */
	public Object findByUid(String uid) {
		return myModelStore.get(uid);
	}

	/**
	 * 
	 * @return
	 */
	protected Entity create() {
		return new MyModel();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#findAllUids()
	 */
	public List findAllUids() {
		return new ArrayList(myModelStore.keySet());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.yourinventit.dmc.api.moat.ModelMapper#count()
	 */
	public long count() {
		return myModelStore.size();
	}

}
