/*
 * Copyright (C) 2013 InventIt Inc.
 * 
 * See https://github.com/inventit/moatosgi-examples
 */
package com.example.model;

/**
 * An interface to define the common accessors for model objects.
 * 
 * @author dbaba@yourinventit.com
 * 
 */
public interface Entity {

    /**
     * Getter for UID.
     * 
     * @return
     */
    String getUid();

    /**
     * Setter for UID.
     * 
     * @param uid
     */
    void setUid(String uid);
}
