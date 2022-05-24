package org.cite.service;


import lombok.AllArgsConstructor;
import org.cite.dto.ResponseResult;
import org.cite.dto.ResponseStatus;
import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.cite.repository.AttributeRepository;
import org.cite.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseResult<List<Attribute>> readAttribute() {
        try {
            return new ResponseResult<>(attributeRepository.findAll(), ResponseStatus.SUCCESS, "Οκ");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute not found.");
        }
    }

    @Override
    public ResponseResult<Attribute> readAttribute(int attributeId) {
        try {
            Optional<Attribute> attributeDb = attributeRepository.findById(attributeId);
            return new ResponseResult<>(attributeDb.get(), ResponseStatus.SUCCESS, "Ok");
        } catch (Exception e) {
            return new ResponseResult<>(null, ResponseStatus.ATTRIBUTE_NOT_FOUND, "Attribute not found.");
        }
    }

    @Override
    public ResponseResult<Attribute> updateAttribute(int attributeId, Attribute attribute) {
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
    public ResponseResult<Boolean> deleteAttribute(int attributeId) {
        try {
            Optional<Attribute> attributeDb = attributeRepository.findById(attributeId);
            if (attributeDb.isEmpty())
                return new ResponseResult<Boolean>(false, ResponseStatus.ATTRIBUTE_CAN_NOT_BE_DELETED, "Delete has failed.");
            attributeRepository.delete(attributeDb.get());
            return new ResponseResult<Boolean>(true, ResponseStatus.SUCCESS, "Ok");
        } catch (Exception e) {
            return new ResponseResult<Boolean>(false, ResponseStatus.ATTRIBUTE_CAN_NOT_BE_DELETED, "Delete has failed.");
        }
    }

    @Override
    public ResponseResult<Boolean> addAttributeToEmployee(int employeeId, int attributeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            Optional<Attribute> attributeOptional = attributeRepository.findById(attributeId);
            if (employeeOptional.isPresent()) {
                attributeOptional.get().getEmployees().add(employeeOptional.get());
                return new ResponseResult<>(true, ResponseStatus.SUCCESS, "Ok");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseResult<Boolean>(false, ResponseStatus.EMPLOYEE_NOT_FOUND, "Employee or attribute cannot be found");
    }

}