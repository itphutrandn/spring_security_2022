package com.eazybytes.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
	
	@GetMapping("/myLoans")
	//@PreAuthorize("hasAuthority('ADMIN')")
	@Secured("ROLE_ADMIN")
	public String getLoanDetails(String input) {
		return "Here are the loan details from the DB";
	}

}
