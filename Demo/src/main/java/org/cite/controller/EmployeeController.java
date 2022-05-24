package org.cite.controller;

import lombok.AllArgsConstructor;
import org.cite.dto.ResponseResult;
import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.cite.service.AttributeService;
import org.cite.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private AttributeService attributeService;

    @GetMapping("")
    public ResponseResult<List<Employee>> getAll() {
        return employeeService.readEmployee() ;
    }

    @GetMapping("/{employeeId}")
    public ResponseResult<Employee> get(@PathVariable("employeeId") int employeeId) {
        return employeeService.readEmployee(employeeId);
    }

    @PostMapping(value = "")
    public ResponseResult<Employee> create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }



    @PutMapping("/{employeeId}")
    public ResponseResult<Employee> update(@PathVariable("employeeId") int employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseResult<Boolean> delete(@PathVariable("employeeId") int employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employeeId}/attributes")
    public ResponseResult<List<Attribute>> getAttributesOfEmployee(@PathVariable("employeeId") int employeeId) {
        return employeeService.getAttributesOfEmployee(employeeId);
    }

    @PostMapping(value = "/{employeeId}/attributes/{attributeId}")
    public ResponseResult<Boolean> addAttributeToEmployee(@PathVariable("employeeId") int employeeId, @PathVariable("attributeId") int attributeId) {
        return attributeService.addAttributeToEmployee(employeeId, attributeId);
    }
}
