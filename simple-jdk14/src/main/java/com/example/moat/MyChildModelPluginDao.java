/*
 * Copyright (C) 2012 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.moat;

import com.example.model.Entity;
import com.example.model.MyChildModel;
import com.example.model.MyModel;
import com.yourinventit.dmc.api.moat.PluginDao;

/**
 * A {@link PluginDao} for {@link MyChildModel} model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyChildModelPluginDao extends MyModelPluginDao {

    /**
     * {@inheritDoc}
     * 
     * @see com.example.moat.MyModelPluginDao#create()
     */
    protected Entity create() {
        return new MyChildModel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.example.moat.MyModelPluginDao#update(java.lang.Object)
     */
    public void update(Object object) {
        final MyChildModel myChildModel = (MyChildModel) object;
        final MyModel myModel = myChildModel.getMyModel();
        if (myModel != null
                && myModel.getChildList().contains(myChildModel) == false) {
            myModel.getChildList().add(myChildModel);
        }
        super.update(object);
    }
}
