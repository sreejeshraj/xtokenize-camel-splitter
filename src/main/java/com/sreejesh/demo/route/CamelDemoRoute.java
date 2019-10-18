package com.sreejesh.demo.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@ConfigurationProperties(prefix="camel-demo-route")
@Data
@EqualsAndHashCode(callSuper=true)

public class CamelDemoRoute extends RouteBuilder {

	
	
	@Override
	public void configure() throws Exception {

		// @formatter:off
		
		//errorHandler(deadLetterChannel("seda:errorQueue").maximumRedeliveries(5).redeliveryDelay(1000));
		
		Namespaces namespaces = new Namespaces("tns", "http://www.ust-global.com/schema/authorization");
		namespaces.add("auth", "http://www.healthedge.com/connector/schema/authorization");
		namespaces.add("header", "http://www.ust-global.com/schema/common");

		from("file://{{inputFolder}}?noop=true")
		.routeId("InputFolderToTestSedaRoute")
		//.split()
		///.xtokenize("//tns:authorizationIBRequestRecord",'i', namespaces)
		//.setHeader(RECORD_ID).xpath("tns:authorizationIBRequestRecord/tns:recordId", String.class, namespaces)
		.log("step 90 is :${body}")
		.split()
		.xtokenize("//tns:authorizationIBRequestRecord",'w', namespaces)
		//.xtokenize("//tns:authorizationIBRequestRecord",'w', namespaces)
		.log("step 100 is :${body}")
		.to("file://{{outputFolder}}?fileName=${exchangeProperty.CamelSplitIndex}-${header.CamelFileName}")
		.end();
		/*.setHeader("myHeader", constant("MY_HEADER_CONSTANT_VALUE"))
		.to("seda://testSeda")
		.log(LoggingLevel.DEBUG, "**** Input File Pushed To Output Folder ***** :"+injectedName);

		from("seda://testSeda")
		.routeId("TestSedaToOutputFolderRoute")
		.to("file://{{outputFolder}}")
		.log("***** myHeader: ${header.myHeader} ***** :"+injectedName);
		
		//Error Handling route!
		
		from("seda:errorQueue")
		.routeId("ErrorHandlingRoute")
		.log("***** error body: ${body} *****")
		.log("***** Exception Caught: ${exception} *****");
		*/
		
		// @formatter:on

	}

}
