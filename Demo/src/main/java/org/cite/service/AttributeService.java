package org.cite.service;

import org.cite.dto.ResponseResult;
import org.cite.model.Attribute;

import java.util.List;

public interface AttributeService {
    ResponseResult<Attribute> createAttribute(Attribute attribute);
    ResponseResult<List<Attribute>> readAttribute();
    ResponseResult<Attribute> readAttribute(int propertyId);
    ResponseResult<Attribute> updateAttribute(int attributeId, Attribute attribute);
    ResponseResult<Boolean> deleteAttribute(int attributeId);
    ResponseResult<Boolean> addAttributeToEmployee( int employeeId, int attributeId);
    ResponseResult<Boolean> deleteAttributeFromEmployee( int employeeId, int attributeId);

}

