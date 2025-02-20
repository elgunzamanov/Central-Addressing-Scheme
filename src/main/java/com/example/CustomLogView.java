package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogView implements CommandLineRunner {
	@Override
	public void run(String... args) {
		log.info("INFO : Info log");
		log.warn("WARN : Warning log");
		log.error("ERROR : Error log");
		log.debug("DEBUG : Debug log");
		log.trace("TRACE : Trace log");
	}
}