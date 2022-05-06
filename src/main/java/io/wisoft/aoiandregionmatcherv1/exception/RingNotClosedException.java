package io.wisoft.aoiandregionmatcherv1.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class RingNotClosedException extends RuntimeException {
}
