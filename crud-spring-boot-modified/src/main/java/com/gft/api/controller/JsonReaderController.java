package com.gft.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.business.ProductBusinessObject;

@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class JsonReaderController {
	
	@Autowired
	private ProductBusinessObject productBusinessObject;
	
	@GetMapping("initreader")
    public String initreader() {
		return productBusinessObject.readFiles();
    }
	
	@GetMapping("clean/import")
    public String cleanImport() {
		productBusinessObject.cleanImport();
		return productBusinessObject.readFiles();
    }

}
