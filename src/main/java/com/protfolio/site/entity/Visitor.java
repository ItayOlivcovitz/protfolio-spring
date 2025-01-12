package com.protfolio.site.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Visitor {

    @Id
    private String ip; // IP as Primary Key

    @JsonFormat(pattern = "dd/MM/yy /HH:mm") // Match the frontend format
    private LocalDateTime date; // Date and time of the visit
    private String platform; // Platform information

    // Default constructor
    public Visitor() {
    }

    // Constructor with all fields
    public Visitor(String ip, LocalDateTime date, String platform) {
        this.ip = ip;
        this.date = date;
        this.platform = platform;
    }

    // Getters and Setters
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Visitor{" +
                "ip='" + ip + '\'' +
                ", date=" + date +
                ", platform='" + platform + '\'' +
                '}';
    }

    // Equals and hashCode methods for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        return ip != null ? ip.equals(visitor.ip) : visitor.ip == null;
    }

    @Override
    public int hashCode() {
        return ip != null ? ip.hashCode() : 0;
    }
}
