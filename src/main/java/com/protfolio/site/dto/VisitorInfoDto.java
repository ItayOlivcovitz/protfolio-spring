package com.protfolio.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorInfoDto {
    private String ip;
    private LocalDateTime date;
    private String platform;
}
