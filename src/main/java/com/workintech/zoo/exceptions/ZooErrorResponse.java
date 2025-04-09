package com.workintech.zoo.exceptions;

//frontenddeki kullanıcıya dönülecek hata sınıfı
//clienta dönecek response bilgilerini taşıyor
//yazımı bu kadar

//HTTP status codes
//200 => OK(200->get, 201 -> created(post))
//400 => bad request / client exceptions
//500 => server errors / nullpointer, classCast, arrayIndexOfBound

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ZooErrorResponse {
    private int status;  //http status code
    private String message;  //hata mesajı
    private long timestamp; //hatanın oluştuğu zaman dilimi
}
