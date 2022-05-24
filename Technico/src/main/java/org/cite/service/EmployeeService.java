package org.cite.service;

import org.cite.dto.ResponseResult;
import org.cite.model.Attribute;
import org.cite.model.Employee;

import java.util.List;

public interface EmployeeService {
    ResponseResult<Employee> createEmployee(Employee employee);
    ResponseResult<List<Employee>> readEmployee();
    ResponseResult<Employee> readEmployee(int employeeId);
    ResponseResult<Employee> updateEmployee(int employeeId, Employee employee);
    ResponseResult<Boolean> deleteEmployee(int employeeId);
    ResponseResult<List<Attribute>> getAttributesOfEmployee(int employeeId);
}
