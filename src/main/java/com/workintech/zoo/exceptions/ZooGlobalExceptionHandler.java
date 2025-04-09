package com.workintech.zoo.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//tek görevi sistemde yaşanan hataları bulup yakalamak
//interceptor olarak adlandırılır(interrupt)
//controllerAdvice anotasyonu interceptor olduğunu belirtmek için

//yakalanan hataları insanların anlayabileceği hale getirmek için kullanılıyor

//Slf4j anotasyonu ile log geliyor, consola hatayı loglayabiliyoruz, sout yerine bunu da hata loglarken kullanabiliriz

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(), System.currentTimeMillis());
        log.error("Exception occured: ", zooException);  //Slf4j anotasyonu ile log geliyor
        return new ResponseEntity<>(zooErrorResponse,zooException.getHttpStatus());
    }

    //hiçbir handler'ın handle edemediği hatalar burada yakalanır
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        log.error("Exception occured: ", exception);  //Slf4j anotasyonu ile log geliyor
        return new ResponseEntity<>(zooErrorResponse,HttpStatus.BAD_REQUEST);

        //HttpStatus.INTERNAL_SERVER_ERROR çözüm videosunda kullanıldı, serverda oluşan başka bir hata olduğu için bu kod tercih edildi
    }
}
