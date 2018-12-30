package com.tirthal.learning.controller;

import com.tirthal.learning.model.Person;
import com.tirthal.learning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("{id}")
    public @ResponseBody Mono<Person> findById(@PathVariable Long id) {

        Optional<Person> person = repository.findById(id);

        return Mono.just(person.orElse(Person.empty()));
    }

    @GetMapping
    public Flux<Person> list() {

        List<Person> persons = repository.findAll();

        return Flux.fromIterable(persons);
    }
}
