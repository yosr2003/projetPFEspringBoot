package com.projetPfe.implService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ISwift;
import com.projetPfe.repositories.SwiftRepository;
@Service
public class SwiftServiceImpl implements ISwift{
	@Autowired
	private SwiftRepository swiftRepo;

}
