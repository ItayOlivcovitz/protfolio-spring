package com.protfolio.site.controller;


import com.protfolio.site.entity.Visitor;
import com.protfolio.site.service.VisitorService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Builder
@RequestMapping("/api/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/info")
    public ResponseEntity<List<Visitor>> getVisitorInfo() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @PostMapping("/save")
    public ResponseEntity<Visitor> saveVisitorInfo(@RequestBody Visitor visitor) {
        System.out.println("Received Visitor: " + visitor);
        if (visitor.getIp() == null || visitor.getIp().isEmpty()) {
            visitor.setIp("AUTO");
        }
        visitor.setDate(visitor.getDate() == null ? LocalDateTime.now() : visitor.getDate());
        Visitor savedVisitor = visitorService.saveVisitor(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisitor);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getVisitorCount() {
        long count = visitorService.countVisitors();
        return ResponseEntity.ok(count);
    }

    private String parseUserAgent(String userAgent) {
        if (userAgent == null) return "Unknown";
        if (userAgent.toLowerCase().contains("iphone")) return "iPhone";
        if (userAgent.toLowerCase().contains("android")) return "Android";
        if (userAgent.toLowerCase().contains("windows")) return "Windows PC";
        if (userAgent.toLowerCase().contains("mac")) return "Mac";
        return "Other";
    }
}
