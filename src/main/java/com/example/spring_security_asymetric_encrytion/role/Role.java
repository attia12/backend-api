package com.example.spring_security_asymetric_encrytion.role;

import com.example.spring_security_asymetric_encrytion.common.BaseEntity;
import com.example.spring_security_asymetric_encrytion.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "ROLES")


public class Role extends BaseEntity {

    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
