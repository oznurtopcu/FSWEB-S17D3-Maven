package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Kangaroo {
    private long id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private Boolean isAggressive;

    //Çözüm videosunda değişkenler referans tipinde veriliyor(Integer, Double vs.)
    //Boolean değerleri primitive type olarak yazınca(yani boolean şeklinde), setter ve getter methodlarının isimlendirmeleri standarda uygun olmuyor.
    //Getter isAggressive, setter ise setAggressive şeklinde dönüyor. Bu farka dikkat etmek gerekiyor.
}
