package org.cite.repository;

import org.cite.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //List<Employee> findOwnersByVatNumberOrEmail(String vatNumber, String email);

}
