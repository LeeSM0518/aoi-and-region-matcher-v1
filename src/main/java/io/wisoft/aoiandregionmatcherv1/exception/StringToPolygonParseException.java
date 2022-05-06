package io.wisoft.aoiandregionmatcherv1.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(code = INTERNAL_SERVER_ERROR)
public class StringToPolygonParseException extends RuntimeException {
}
