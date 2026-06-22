package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(columnDefinition = "VARCHAR(30)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50)")
    private String role;

    @Column(name = "sso_provider_id")
    private String ssoProviderId;

    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

}
