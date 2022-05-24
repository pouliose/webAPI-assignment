package org.cite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @Type(type = "uuid-char")
    private UUID employeeId = UUID.randomUUID();
    @NotBlank(message = "Name is mandatory field.")
    private String employeeName;
    private LocalDate hireDate;

    private UUID supervisorId;

    @ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JsonIgnore
    private List<Attribute> attributes;

    private LocalDate birthday;
    private boolean carOwnership;
    private String address;


    public Employee(String employeeName, LocalDate hireDate, UUID supervisorId) {
        this.employeeName = employeeName;
        this.hireDate = hireDate;
        this.supervisorId = supervisorId;
    }
}
