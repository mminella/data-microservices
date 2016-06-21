/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.cloud.stream.transformer.configuration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Michael Minella
 */
@Configuration
@EnableBinding(Processor.class)
public class TransformerConfiguration {

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transform(String input) {
		System.out.println(">> input: " + input);

		return "It's 5:00 somewhere!";
	}
}
