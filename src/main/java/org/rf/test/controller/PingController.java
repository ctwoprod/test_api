package org.rf.test.controller;

import static spark.Spark.get;

public class PingController {
    public PingController() {
        get("/ping", (req, res) -> "pong");
    }
}
