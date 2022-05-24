package org.cite.service;

import org.cite.utils.ResponseResult;
import org.cite.model.Attribute;

import java.util.List;
import java.util.UUID;

public interface AttributeService {
    ResponseResult<Attribute> createAttribute(Attribute attribute);
    ResponseResult<List<Attribute>> readAttribute();
    ResponseResult<Attribute> readAttribute(UUID propertyId);
    ResponseResult<Attribute> updateAttribute(UUID attributeId, Attribute attribute);
    ResponseResult<Boolean> deleteAttribute(UUID attributeId);
    ResponseResult<Boolean> addAttributeToEmployee(UUID employeeId, UUID attributeId);
    ResponseResult<Boolean> deleteAttributeFromEmployee(UUID employeeId, UUID attributeId);

}

