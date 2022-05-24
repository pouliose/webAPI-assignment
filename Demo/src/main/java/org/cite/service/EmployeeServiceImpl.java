package org.cite.service;

import lombok.AllArgsConstructor;
import org.cite.AppMain;
import org.cite.utils.ResponseResult;
import org.cite.utils.ResponseStatus;
import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.cite.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

    @Override
    public ResponseResult<Employee> createEmployee(Employee employee) {
        try {
            return new ResponseResult<>(employeeRepository.save(employee), ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_CREATED, "Save has failed.");
    }

    @Override
    public ResponseResult<List<Employee>> readEmployee() {
        try {
            return new ResponseResult<>(employeeRepository.findAll(), ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee not found");
        }
    }

    @Override
    public ResponseResult<Employee> readEmployee(UUID employeeId) {
        try {
            return new ResponseResult<>(employeeRepository.findById(employeeId).get(), ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee not found");
        }
    }

    @Override
    public ResponseResult<Employee> updateEmployee(UUID employeeId, Employee employee) {
        try {
            Optional<Employee> ownerDb = employeeRepository.findById(employeeId);
            if (ownerDb.isEmpty())
                return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_CANNOT_BE_UPDATED, "Fail to update");
            Employee updated = ownerDb.
                    map(match -> {
                        try {
                            match.setEmployeeName(employee.getEmployeeName());
                            match.setHireDate(employee.getHireDate());
                            match.setSupervisorId(employee.getSupervisorId());
                            return employeeRepository.save(match);

                        } catch (Exception e) {
                            e.getMessage();
                            return null;
                        }
                    }).get();
            return new ResponseResult<>(updated, ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_CANNOT_BE_UPDATED, "Fail to update");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteEmployee(UUID employeeId) {
        try {
            Optional<Employee> employeeDb = employeeRepository.findById(employeeId);
            if (employeeDb.isEmpty())
                return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Delete has failed.");
            employeeRepository.delete(employeeDb.get());
            return new ResponseResult<>(true, ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Delete has failed.");
        }
    }

    @Override
    public ResponseResult<List<Attribute>> getAttributesOfEmployee(UUID employeeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isEmpty())
                return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee not found");
            return new ResponseResult<>(employeeOptional.get().getAttributes(), ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.EMPLOYEE_NOT_FOUND, "Failed to found properties.");
        }
    }

}
