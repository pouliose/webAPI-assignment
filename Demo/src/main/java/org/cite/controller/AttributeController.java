package org.cite.controller;


import lombok.AllArgsConstructor;
import org.cite.dto.ResponseResult;
import org.cite.model.Attribute;
import org.cite.service.AttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/attributes")

public class AttributeController {
    private AttributeService attributeService;

    @GetMapping(value = "")
    public ResponseResult<List<Attribute>> get() {
        return attributeService.readAttribute() ;
    }

    @GetMapping("/{attributeId}")
    public ResponseResult<Attribute> get(@PathVariable("attributeId") int attributeId) {
        return attributeService.readAttribute(attributeId);
    }

    @PutMapping(value = "/{attributeId}")
    public ResponseResult<Attribute> update(@PathVariable("attributeId") int attributeId, @RequestBody Attribute attribute) {
        return attributeService.updateAttribute(attributeId, attribute);
    }

    @DeleteMapping("/{attributeId}")
    public ResponseResult<Boolean> delete(@PathVariable("attributeId") int attributeId) {
        return attributeService.deleteAttribute(attributeId);
    }

}
