package org.cite.service;


import lombok.AllArgsConstructor;
import org.cite.utils.ResponseResult;
import org.cite.utils.ResponseStatus;
import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.cite.repository.AttributeRepository;
import org.cite.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service

public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseResult<Attribute> createAttribute(Attribute attribute) {
        try {
            return new ResponseResult<>(attributeRepository.save(attribute), ResponseStatus.SUCCESS, "Oκ");
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_CREATED, "Save has failed.");
    }

    @Override
    public ResponseResult<List<Attribute>> readAttribute() {
        try {
            return new ResponseResult<>(attributeRepository.findAll(), ResponseStatus.SUCCESS, "Οκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute not found.");
        }
    }

    @Override
    public ResponseResult<Attribute> readAttribute(UUID attributeId) {
        try {
            Optional<Attribute> attributeDb = attributeRepository.findById(attributeId);
            return new ResponseResult<>(attributeDb.get(), ResponseStatus.SUCCESS, "Ok");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute not found.");
        }
    }

    @Override
    public ResponseResult<Attribute> updateAttribute(UUID attributeId, Attribute attribute) {
        Optional<Attribute> propertyDb = attributeRepository.findById(attributeId);
        if (propertyDb.isEmpty())
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute id not found.");
        try {
            Attribute updated = propertyDb
                    .map(match -> {
                        match.setAttributeName(attribute.getAttributeName());
                        match.setAttributeValue(attribute.getAttributeValue());
                        return attributeRepository.save(match);
                    }).get();
            return new ResponseResult<>(updated, ResponseStatus.SUCCESS, "Οκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Save has failed.");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteAttribute(UUID attributeId) {
        try {
            Optional<Attribute> attributeDb = attributeRepository.findById(attributeId);
            if (attributeDb.isEmpty())
                return new ResponseResult<>(false, ResponseStatus.ATTRIBUTE_CAN_NOT_BE_DELETED, "Delete has failed.");
            List<Employee> employees = employeeRepository.findAll();
            for (Employee employee : employees) {
                if (employee.getAttributes().contains(attributeDb.get())){
                    List<Attribute> tempAttributes = employee.getAttributes();
                    tempAttributes.remove(attributeDb.get());
                    employee.setAttributes(tempAttributes);
                }
            }
            employeeRepository.saveAll(employees);
            attributeDb.get().setEmployees(null);
            attributeRepository.save( attributeDb.get());
            attributeRepository.delete(attributeDb.get());
            return new ResponseResult<>(true, ResponseStatus.SUCCESS, "Ok");
        } catch (Exception e) {
            return new ResponseResult<>(false, ResponseStatus.ATTRIBUTE_CAN_NOT_BE_DELETED, "Delete has failed.");
        }
    }

    @Override
    public ResponseResult<Boolean> addAttributeToEmployee(UUID employeeId, UUID attributeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            Optional<Attribute> attributeOptional = attributeRepository.findById(attributeId);
            if (employeeOptional.isPresent()) {
                if(attributeOptional.isPresent()){
                    List<Attribute> tempAttributes =  employeeOptional.get().getAttributes();
                    if (tempAttributes.contains(attributeOptional.get())){
                        return new ResponseResult<>(true, ResponseStatus.EMPLOYEE_CANNOT_BE_UPDATED, "Attribute already exists.");
                    }
                    tempAttributes.add(attributeOptional.get());
                    employeeOptional.get().setAttributes(tempAttributes);
                    employeeRepository.save(employeeOptional.get());
                    return new ResponseResult<>(true, ResponseStatus.SUCCESS, "Ok");
                }
                return new ResponseResult<>(true, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute cannot be found.");
            }
            return new ResponseResult<>(true, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee cannot be found.");
        } catch (Exception e) {
            e.getMessage();
            return new ResponseResult<>(false, ResponseStatus.EMPLOYEE_CANNOT_BE_UPDATED, "Add process failed.");
        }
    }

    @Override
    public ResponseResult<Boolean> deleteAttributeFromEmployee(UUID employeeId, UUID attributeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isPresent()) {
                List<Attribute> attributes = employeeOptional.get().getAttributes();
                Optional<Attribute> attributeOptional = attributeRepository.findById(attributeId);
                if(attributeOptional.isPresent()){
                    attributes.remove(attributeOptional.get());
                    employeeOptional.get().setAttributes(attributes);
                    employeeRepository.save(employeeOptional.get());
                    return new ResponseResult<>(true, ResponseStatus.SUCCESS, "Ok");
                }
                return new ResponseResult<>(true, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute cannot be found.");
            }
            return new ResponseResult<>(true, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee cannot be found.");
        } catch (Exception e) {
            e.getMessage();
            return new ResponseResult<>(false, ResponseStatus.EMPLOYEE_CANNOT_BE_UPDATED, "Delete process failed.");
        }
    }
}
