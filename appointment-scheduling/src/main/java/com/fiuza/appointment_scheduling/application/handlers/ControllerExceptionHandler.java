package com.fiuza.appointment_scheduling.application.handlers;

import com.fiuza.appointment_scheduling.core.dto.errors.AlreadyExistExceptionDto;
import com.fiuza.appointment_scheduling.core.dto.errors.InternalServerErrorDto;
import com.fiuza.appointment_scheduling.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.appointment_scheduling.core.dto.errors.NullDataNotNullExceptionDto;
import com.fiuza.appointment_scheduling.core.exceptions.AlreadyExistException;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.exceptions.NullDataNotNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NullDataNotNullException.class)
    public ResponseEntity<NullDataNotNullExceptionDto> handlerNullDatException(
            NullDataNotNullException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value())
                .body(new NullDataNotNullExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<AlreadyExistExceptionDto> handlerAlreadyExistException(
            AlreadyExistException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value())
                .body(new AlreadyExistExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDto> handlerNotFoundException(
            NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value())
                .body(new NotFoundExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<InternalServerErrorDto> handlerInternalServerErrorException(
            InternalServerError exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value())
                .body(new InternalServerErrorDto(exception.getMessage(), status.value()));
    }
}
