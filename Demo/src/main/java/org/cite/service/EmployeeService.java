package org.cite.service;

import org.cite.utils.ResponseResult;
import org.cite.model.Attribute;
import org.cite.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    ResponseResult<Employee> createEmployee(Employee employee);
    ResponseResult<List<Employee>> readEmployee();
    ResponseResult<Employee> readEmployee(UUID employeeId);
    ResponseResult<Employee> updateEmployee(UUID employeeId, Employee employee);
    ResponseResult<Boolean> deleteEmployee(UUID employeeId);
    ResponseResult<List<Attribute>> getAttributesOfEmployee(UUID employeeId);
}
