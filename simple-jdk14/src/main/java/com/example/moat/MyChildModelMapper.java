/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.moat;

import com.example.model.Entity;
import com.example.model.MyChildModel;
import com.example.model.MyModel;
import com.yourinventit.dmc.api.moat.ModelMapper;

/**
 * A {@link ModelMapper} for {@link MyChildModel} model object.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public class MyChildModelMapper extends MyModelMapper {

    /**
     * {@inheritDoc}
     * 
     * @see com.example.moat.MyModelMapper#create()
     */
    protected Entity create() {
        return new MyChildModel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.example.moat.MyModelMapper#update(java.lang.Object)
     */
    public Object update(Object object) {
        final MyChildModel myChildModel = (MyChildModel) object;
        final MyModel myModel = myChildModel.getMyModel();
        if (myModel != null
                && myModel.getChildList().contains(myChildModel) == false) {
            myModel.getChildList().add(myChildModel);
        }
        return super.update(object);
    }
}
