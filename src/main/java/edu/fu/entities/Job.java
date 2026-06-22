package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "jobs", uniqueConstraints = {@UniqueConstraint(name = "UXI_TITLE", columnNames = {"title"})})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "department")
public class Job extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(500)")
    private String location;

    // 9 999 999 999 999.99
    @Column(name = "min_salary", columnDefinition = "NUMERIC(15,2)")
    private Double minSalary;

    @Column(name = "max_salary", columnDefinition = "NUMERIC(15,2)")
    private Double maxSalary;

    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @Column(columnDefinition = "VARCHAR(150)")
    private String utmSource;

    @Column(columnDefinition = "VARCHAR(150)")
    private String utmMedium;

    private Instant deadline;

    @Column(name = "publish_at")
    private Instant publishAt;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
