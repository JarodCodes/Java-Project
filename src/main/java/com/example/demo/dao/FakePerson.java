package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.demo.model.Person;

public class FakePerson implements PersonDao{
    private List<Person> persons = new ArrayList<Person>();
    @Override
    public int insertPerson(UUID id, Person person) {
        persons.add(new Person(id, person.getEmail(), person.getPassword()));
        return 1;
    }
    
}
