package edu.fu.dto;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    private String title;
    private String description;
    private String location;
    // 9 999 999 999 999.99
    private Double minSalary;

    private Double maxSalary;
    private String utmSource;
    private String utmMedium;

    private Instant deadline;

    private Long departmentId;
}
