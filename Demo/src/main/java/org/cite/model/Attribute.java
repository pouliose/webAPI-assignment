package org.cite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Attribute {
    @Id
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @NotBlank(message = "Attribute name is mandatory field.")
    private String attributeName;
    @NotBlank(message = "Attribute value is mandatory field.")
    private String attributeValue;

    @ManyToMany(mappedBy = "attributes")
    @JsonIgnore
    private List<Employee> employees;

    public Attribute(String attributeName, String attributeValue, List<Employee> employees) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.employees = employees;
    }

    public Attribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
}
