package org.rf.test.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import org.rf.test.service.BankService;
import org.rf.test.ws.bank.CreateBankRQ;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BankController {
	public BankController(BankService bankService, ObjectMapper objectMapper) {
		get("/bank", (req, res) -> bankService.getBankList(), objectMapper::writeValueAsString);
		get("/bank/:id", (req, res) -> bankService.getBank(req.params(":id")),
				objectMapper::writeValueAsString);
		post("/bank", (req, res) -> bankService.create(objectMapper.readValue(req.body(), CreateBankRQ.class)), objectMapper::writeValueAsString);
		put("/bank/:id", (req, res) -> bankService.update(req.params(":id"), objectMapper.readValue(req.body(), CreateBankRQ.class)), objectMapper::writeValueAsString);
		delete("/bank/:id", (req, res) -> bankService.delete(req.params(":id")), objectMapper::writeValueAsString);
		
	}
}
