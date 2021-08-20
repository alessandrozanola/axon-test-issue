package com.azanola.test.first.domain;

import com.azanola.test.shared.FireCommand;
import com.azanola.test.shared.EventHappened;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class TestAggregate {
	@AggregateIdentifier
	private UUID id;

	@CommandHandler
	@CreationPolicy(AggregateCreationPolicy.ALWAYS)
	public void handle(FireCommand command) {
		log.info("Handling command... Id: " + command.getId());
		apply(new EventHappened(command.getId()));
	}

	@EventSourcingHandler
	public void on(EventHappened event) {
		id = event.getId();
		log.info("Event sourcing handler. Id: " + id);
	}
}
