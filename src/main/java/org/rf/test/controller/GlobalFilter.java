package org.rf.test.controller;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.options;

import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.rf.test.ws.GenericRS;
import org.rf.test.ws.MessageError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import spark.Response;

public class GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
    private ObjectMapper mapper;

    public GlobalFilter(ObjectMapper mapper) {
        this.mapper = mapper;

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            String correlationId = request.headers("X-CORRELATION-ID");
            if(StringUtils.isBlank(correlationId)){
                correlationId = UUID.randomUUID().toString().replace("-", "");
            }
            MDC.put("correlationId", correlationId);
            if(!"/ping".equalsIgnoreCase(request.uri())) {
                logger.info(String.format("%s | %s | %s", getUri(request), getHeader(request), getBody(request)));
            }
        });

        after((request, response) -> setHeader(response));

        exception(Exception.class, (exception, request, response) -> {
            logger.error("Unhandled exception", exception);
            setHeader(response);
            response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            MessageError error = MessageError.buildGenericError();
            error.setMessage(exception.getMessage());
            GenericRS res = new GenericRS().setError(error);
            response.body(serialize(res));
        });


        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
    }

    private String getUri(Request request) {
        StringBuilder builder = new StringBuilder(request.uri());
        if(StringUtils.isNotBlank(request.queryString())){
            builder.append("?" + request.queryString());
        }
        return  builder.toString();
    }

    private String getHeader(Request request) {
        return request.headers().stream()
                .map(item -> item + "=" + request.headers(item))
                .collect(Collectors.joining(";"));
    }

    private Object getBody(Request request) {
        if(request.pathInfo().endsWith("/kyc/document")){
            return StringUtils.EMPTY;
        }
        if(request.contentType() != null && request.contentType().contains("multipart/form-data")) {
            return "uploading an image";
        }
        return StringUtils.remove(request.body(), '\n');
    }

    private String serialize(GenericRS res) {
        try {
            return this.mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            logger.error("Can not serialize object.", e);
        }
        return "{}";
    }

    private void setHeader(Response response) {
        response.header("Content-Type", "application/json");
    }
}
