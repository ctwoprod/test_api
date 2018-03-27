package org.rf.test.controller;

import static spark.Spark.get;

import org.rf.test.service.TestService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestController {

	public TestController(TestService testService, ObjectMapper objectMapper) {
		get("/test", (req, res) -> testService.test(), objectMapper::writeValueAsString);
	}
}
