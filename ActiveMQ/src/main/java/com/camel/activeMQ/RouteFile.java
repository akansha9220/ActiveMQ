package com.camel.activeMQ;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteFile extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		fromTimerToQueue();
		fromFileToAMQ();
		callFileLanguage();
		

	}

	private void fromTimerToQueue() {
		from("timer:active-mq-timer?period=1000").transform().constant("My message for active MQ").log("${body}")
				.to("activemq:my-activemq-queue");
	}


	private void fromFileToAMQ() {
		from("file:D:\\CamelDocs\\inp").to("activemq:queue:activeMQ2");
	}

	private void callFileLanguage() {
		from("file:D:\\CamelDocs\\inp")
				.to("file:D:\\CamelDocs\\output?fileName=${file:onlyname.noext}-${date:now:yyyyMMdd}.${file:ext}");
	}

}
