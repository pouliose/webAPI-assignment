package org.cite.controller;


import lombok.AllArgsConstructor;
import org.cite.utils.ResponseResult;
import org.cite.model.Attribute;
import org.cite.service.AttributeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/attributes")

public class AttributeController {
    private AttributeService attributeService;

    @PostMapping(value= "")
    public ResponseResult<Attribute> create(@RequestBody Attribute attribute) {
        return attributeService.createAttribute(attribute);
    }

    @GetMapping(value = "")
    public ResponseResult<List<Attribute>> get() {
        return attributeService.readAttribute() ;
    }

    @GetMapping("/{attributeId}")
    public ResponseResult<Attribute> get(@PathVariable("attributeId") UUID attributeId) {
        return attributeService.readAttribute(attributeId);
    }

    @PutMapping(value = "/{attributeId}")
    public ResponseResult<Attribute> update(@PathVariable("attributeId") UUID attributeId, @RequestBody Attribute attribute) {
        return attributeService.updateAttribute(attributeId, attribute);
    }

    @DeleteMapping("/{attributeId}")
    public ResponseResult<Boolean> delete(@PathVariable("attributeId") UUID attributeId) {
        return attributeService.deleteAttribute(attributeId);
    }

}
