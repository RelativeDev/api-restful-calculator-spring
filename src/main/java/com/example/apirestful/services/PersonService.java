package com.example.apirestful.services;

import com.example.apirestful.exception.ResourceNotFoundException;
import com.example.apirestful.model.Person;
import com.example.apirestful.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all People!");
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one Person!");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person){

        logger.info("Creating a person!");
        return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating a person!");
        var entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());
        return personRepository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }
}
