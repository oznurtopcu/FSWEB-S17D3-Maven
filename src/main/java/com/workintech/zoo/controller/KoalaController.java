package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    public Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAllKoalas() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable long id) {
        if(id<=0) {
            throw new ZooException("ID must be greater then zero.", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala) {
        if(koala.getId() <= 0) {
            throw new ZooException("ID must be greater then zero.", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        //TODO: created http status dönecek
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable long id, @RequestBody Koala newKoala) {
        //eğer key varsa update et, key yoksa yeni kayıt olarak aç
        if(koalas.containsKey(id)) {
            koalas.replace(id,newKoala);
            return koalas.get(id);
        }else {
            return addKoala(newKoala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable long id) {
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }
}
