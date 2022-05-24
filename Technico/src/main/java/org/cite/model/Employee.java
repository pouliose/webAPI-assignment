package org.cite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name= "employeeId")
    private int employeeId;
    @NotBlank(message = "Name is mandatory field.")
    private String employeeName;
    @NotBlank(message = "Date of hire is mandatory field.")
    private LocalDate hireDate;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisorId", referencedColumnName = "employeeId")*/
    private int supervisorId;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Attribute> attributes;

    public Employee(String employeeName, LocalDate hireDate, int supervisorId) {
        this.employeeName = employeeName;
        this.hireDate = hireDate;
        this.supervisorId = supervisorId;
    }
}
