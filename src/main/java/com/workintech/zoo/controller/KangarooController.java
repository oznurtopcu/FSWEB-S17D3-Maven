package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
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

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getId() <= 0) {
            throw new ZooException("ID must be greater then zero.", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
        //TODO: created http status dönecek
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable long id, @RequestBody Kangaroo newKangaroo) {
        kangaroos.replace(id,newKangaroo);
        return kangaroos.get(id);
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }

}
