package com.azanola.test.second.domain;

import com.azanola.test.shared.EventHappened;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

// TODO: Here the ProcessingGroup annotation
//@ProcessingGroup("SagaProcessing")
@Saga
@Slf4j
public class TestSaga {

	@StartSaga
	@EndSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(EventHappened event) {
		log.info("Saga handler. Id: " + event.getId());
	}

}
