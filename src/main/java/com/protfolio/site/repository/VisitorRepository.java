package com.protfolio.site.repository;

import com.protfolio.site.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {
    // Add custom query methods if needed in the future
}