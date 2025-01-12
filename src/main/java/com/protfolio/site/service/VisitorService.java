package com.protfolio.site.service;


import com.protfolio.site.entity.Visitor;

import java.util.List;

public interface VisitorService {
    /**
     * Save a visitor record to the database.
     *
     * @param visitor Visitor object to save
     * @return Saved Visitor object
     */
    Visitor saveVisitor(Visitor visitor);

    /**
     * Count unique visitors and add a new one if it doesn't exist.
     *
     * @return Total count of unique visitors
     */
    long countVisitors();

    /**
     * Retrieve all visitor records from the database.
     *
     * @return List of all Visitor objects
     */
    List<Visitor> getAllVisitors();
}
