package com.azanola.test.first.controllers;

import com.azanola.test.shared.FireCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("test")
public class TestController {
	private final CommandGateway commandGateway;

	public TestController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@GetMapping
	public void test() {
		commandGateway.sendAndWait(new FireCommand(UUID.randomUUID()));
	}
}
