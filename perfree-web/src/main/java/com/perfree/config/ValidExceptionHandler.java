package com.perfree.config;

import com.perfree.commons.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Parameter verification exception handling
 *
 * @author Perfree
 */
@ControllerAdvice
public class ValidExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ValidExceptionHandler.class);

    /**
     * Check exception interception processing
     *
     * @param exception exception
     * @return exception message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseBean validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        Objects.requireNonNull(result.getFieldError());
        LOGGER.error(result.getFieldError().getDefaultMessage());
        return ResponseBean.fail(result.getFieldError().getDefaultMessage(), null);
    }

    /**
     * ClassCastException handle
     *
     * @param exception exception
     * @return exception message
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseBean parameterTypeException(HttpMessageConversionException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseBean.fail("ClassCastException", exception.getMessage());

    }
}
