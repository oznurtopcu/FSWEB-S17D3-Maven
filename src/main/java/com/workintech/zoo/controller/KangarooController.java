package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import com.workintech.zoo.validations.ZooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    public Map<Long, Kangaroo> kangaroos;
    //videoda keyi Integer olarak tuttuk

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable long id) {
        if(id<=0) {
            throw new ZooException("ID must be greater then zero.", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }

        return kangaroos.get(id);
    }

//    @GetMapping("/{id}")
//    public Kangaroo getKangarooByIdNew(@PathVariable long id) {
//        ZooKangarooValidation.isIdValid(id);
//        ZooKangarooValidation.checkKangarooExistence(kangaroos, id, true);
//        return kangaroos.get(id);
//    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getId() <= 0) {
            throw new ZooException("ID must be greater then zero.", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
        //TODO: created http status dönecek
    }

//    @PostMapping
//    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo) {
//        ZooKangarooValidation.checkKangarooExistence(kangaroos, kangaroo.getId(), false);
//        ZooKangarooValidation.checkKangarooWeight(kangaroo.getWeight());
//        kangaroos.put(kangaroo.getId(), kangaroo);
//        return kangaroos.get(kangaroo.getId());
//    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable long id, @RequestBody Kangaroo newKangaroo) {
        kangaroos.replace(id,newKangaroo);
        return kangaroos.get(id);
    }

//    @PutMapping("/{id}")
//    public Kangaroo update(@PathVariable long id, @RequestBody Kangaroo newKangaroo) {
//        ZooKangarooValidation.isIdValid(id);
//        ZooKangarooValidation.checkKangarooWeight(newKangaroo.getWeight());
//        newKangaroo.setId(id);
//        if(kangaroos.containsKey(id)) {
//            kangaroos.put(id, newKangaroo);
//            return kangaroos.get(id);
//        } else {
//            return saveKangaroo(newKangaroo);
//        }
//    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }

//    @DeleteMapping("/{id}")
//    public Kangaroo delete(@PathVariable long id) {
//        ZooKangarooValidation.isIdValid(id);
//        ZooKangarooValidation.checkKangarooExistence(kangaroos, id, true);
//        return kangaroos.remove(id);
//    }

}
