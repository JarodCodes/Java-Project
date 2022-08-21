package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("fakeDao")
public class FakePerson implements PersonDao{
    private List<Person> persons = new ArrayList<Person>();
    @Override
    public int insertPerson(UUID id, Person person) {
        persons.add(new Person(id, person.getEmail(), person.getPassword()));
        return 1;
    }
    @Override
    public List<Person> selectAllPeople() {
        return persons;
    }
    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return persons.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()){
            return 0;
        }
        persons.remove(person.get());
        return 1;
    }
    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id).map(p -> {
            int indexOfPersonToDelete = persons.indexOf(person);
            if (indexOfPersonToDelete >= 0) {
                persons.set(indexOfPersonToDelete, person);
                return 1;
            }
            return 0;
        }).orElse(0);
    }
    
}
