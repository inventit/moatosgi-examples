/*
 * Copyright (C) 2012 InventIt Inc.
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
import com.yourinventit.dmc.api.moat.PluginDao;

/**
 * A {@link PluginDao} for {@link MyModel} model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyModelPluginDao implements PluginDao {

    /**
     * A {@link Map} containing {@link Entity} instances.
     */
    private final Map myModelStore = new HashMap();

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.PluginDao#update(java.lang.Object)
     */
    public void update(Object object) {
        final Entity entity = (Entity) object;
        myModelStore.put(entity.getUid(), myModelStore);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.PluginDao#remove(java.lang.String)
     */
    public void remove(String uid) {
        myModelStore.remove(uid);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.PluginDao#findByUid(java.lang.String)
     */
    public Object findByUid(String uid) {
        return myModelStore.get(uid);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.PluginDao#findByUidOrCreate(java.lang.String)
     */
    public Object findByUidOrCreate(String uid) {
        final Object object = findByUid(uid);
        if (object != null) {
            return object;
        }
        final Entity entity = create();
        entity.setUid(uid);
        myModelStore.put(uid, entity);
        return entity;
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
     * @see com.yourinventit.dmc.api.moat.PluginDao#findAllUids()
     */
    public List findAllUids() {
        return new ArrayList(myModelStore.keySet());
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.yourinventit.dmc.api.moat.PluginDao#getCount()
     */
    public long getCount() {
        return myModelStore.size();
    }

}
