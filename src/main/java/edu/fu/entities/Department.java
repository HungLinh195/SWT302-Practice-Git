package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
@ToString(exclude = {"users", "jobs"})
@Builder
@org.hibernate.annotations.NamedQueries({@org.hibernate.annotations.NamedQuery(name = "findDepartmentByName",
    query = "SELECT d FROM Department d JOIN FETCH d.jobs j WHERE d.departmentName LIKE :name")
        ,@org.hibernate.annotations.NamedQuery(name = "findAll", query = "SELECT d FROM Department d")

//        List<Object[]> --> List<JobDto>
//        @org.hibernate.annotations.NamedQuery(name = "findDepartmentByName",
//                query = "SELECT d FROM Department d JOIN d.jobs j WHERE d.departmentName LIKE :name")
})
public class Department extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", unique = true, columnDefinition = "VARCHAR(255)")
    private String departmentName;

    private String description;

    // List of Job
    // Map vs List vs Set --> cho nợ
    @OneToMany(mappedBy = "department", fetch =  FetchType.LAZY)
    private Set<Job> jobs = new HashSet<>();

    @OneToMany(mappedBy = "department", fetch =   FetchType.LAZY)
    private Set<User> users = new HashSet<>();

}
