package com.harvest.api.controllers;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comm.harvest.core.entities.Client;

@RestController
public class ClientController {

	private static final String template = "Client_%s!";
	// private final AtomicLong counter = new AtomicLong();

	@GetMapping("/byId")
	public Client getById(@RequestParam(value = "id", defaultValue = "1") int id) {
		return new Client(id, String.format(template, id));
	}
}

