package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.ISwift;

@RestController
@RequestMapping("/sift")
public class SwiftController {
	@Autowired
	private ISwift swiftService;

}
