package org.cite.repository;

import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerPropertyRepository extends JpaRepository<Employee, Attribute> {

}
