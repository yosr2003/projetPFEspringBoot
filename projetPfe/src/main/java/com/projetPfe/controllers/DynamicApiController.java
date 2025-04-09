package com.projetPfe.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.implService.DynamicApiService;

@RestController
@RequestMapping("/api")
public class DynamicApiController {
	 @Autowired
	 private DynamicApiService dynamicApiService;

	 @PostMapping("/run")
	 public CompletableFuture<Object> runSelectedApis(@RequestBody List<String> apiNames) {
	        List<CompletableFuture<Object>> futures = apiNames.stream()
	            .filter(dynamicApiService::exists)
	            .map(name -> dynamicApiService.getApiByName(name).get())
	            .collect(Collectors.toList());

	        return CompletableFuture
	            .allOf(futures.toArray(new CompletableFuture[0]))
	            .thenApply(v -> futures.stream()
	                .map(CompletableFuture::join)
	                .collect(Collectors.toList()));
	    }
	

}
