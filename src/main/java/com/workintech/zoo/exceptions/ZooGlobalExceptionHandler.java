package com.workintech.zoo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//tek görevi sistemde yaşanan hataları bulup yakalamak
//interceptor olarak adlandırılır(interrupt)
//controllerAdvice anotasyonu interceptor olduğunu belirtmek için


@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse,zooException.getHttpStatus());
    }

    //hiçbir handler'ın handle edemediği hatalar burada yakalanır
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
