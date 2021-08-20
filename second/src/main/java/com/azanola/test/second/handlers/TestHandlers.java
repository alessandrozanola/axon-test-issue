package com.azanola.test.second.handlers;

import com.azanola.test.shared.EventHappened;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestHandlers {
	@EventHandler
	public void on(EventHappened event) {
		log.info("Event Handler. Id: " + event.getId());
	}
}
