package com.protfolio.site.controller;

import com.protfolio.site.entity.Visitor;
import com.protfolio.site.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/visitor")
@CrossOrigin(origins = {"http://localhost:8080", "https://itay-olivcovitz-portfolio.up.railway.app"}) // Allowed origins
public class VisitorController {

    private final VisitorService visitorService;
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/info")
    public ResponseEntity<List<Visitor>> getVisitorInfo() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @PostMapping("/save")
    public ResponseEntity<Visitor> saveVisitorInfo(@RequestBody Visitor visitor) {
        System.out.println("Received Visitor: " + visitor);

        // Set default IP if not provided
        if (visitor.getIp() == null || visitor.getIp().isEmpty()) {
            visitor.setIp("AUTO");
        }

        // Parse the date if it's provided as a string
        if (visitor.getDate() == null) {
            visitor.setDate(LocalDateTime.now());
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy /HH:mm");
                visitor.setDate(LocalDateTime.parse(visitor.getDate().toString(), formatter));
            } catch (Exception e) {
                System.out.println("Error parsing date: " + e.getMessage());
                visitor.setDate(LocalDateTime.now());
            }
        }

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
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("iphone")) return "iPhone";
        if (userAgent.contains("android")) return "Android";
        if (userAgent.contains("windows")) return "Windows PC";
        if (userAgent.contains("mac")) return "Mac";
        return "Other";
    }
}
