package org.dmx.pn.web;

import org.dmx.pn.utils.PolishNotation;

import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
public class EvaluationController {

    private static final Logger LOG = LogManager.getLogger(EvaluationController.class);

    private final Locale locale;
    private final String outNumberFormat;

    public EvaluationController(@Value("${locale}") String locale,
                                @Value("${out.number.format}") String outNumberFormat) {
        this.locale = Locale.forLanguageTag(locale);
        this.outNumberFormat = outNumberFormat;
    }

    @ApiOperation(value = "Evaluate list of expressions in Polish Notation", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Evaluations completed without errors", response = String.class),
            @ApiResponse(code = 400, message = "Evaluations completed with errors", response = String.class)
    })
    @PostMapping(value = "/evaluate", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> evaluate(@RequestBody String requestBody) {

        LOG.info("Evaluating expressions");

        HttpStatus responseStatus = HttpStatus.OK;
        String[] expressions = requestBody.split("\\r?\\n");
        List<String> evaluations = new ArrayList<>(expressions.length);

        for (String expression : expressions) {
            String evaluation;
            String[] tokens = expression.split(" ");
            double d = PolishNotation.evaluate(tokens);
            if (Double.isNaN(d)) {
                evaluation = "error";
                responseStatus = HttpStatus.BAD_REQUEST;
            } else {
                evaluation = String.format(locale, outNumberFormat, d);
            }
            evaluations.add(evaluation);
        }

        String responseBody = String.join("\n", evaluations);
        return new ResponseEntity<>(responseBody, responseStatus);
    }
}
