package com.techmaster.read_csv.controller;

import com.techmaster.read_csv.model.PersonDTO;
import com.techmaster.read_csv.service.IPersonService;
import com.techmaster.read_csv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PersonController {
    @Autowired
    IPersonService personService;

    @GetMapping("/all")
    public List<PersonDTO> getAllPerson(){
        return personService.getAll();
    }

    @GetMapping("/sort/jobs")
    public List<PersonDTO> getListSortedJobs(){
        return personService.getSortedJobs();
    }

    @GetMapping("/sort/cities")
    public List<PersonDTO> getListSortedByCities(){
        return personService.getSortedCities();
    }

    @GetMapping("/sort/name")
    public List<PersonDTO> getListSortedByFullName(){
        return personService.sortPeopleByFullName();
    }

    @GetMapping("/find/jobs")
    public Map<String, Integer> findTop5Jobs(){
        return personService.findTop5Jobs();
    }

    @GetMapping("/find/cities")
    public Map<String, Integer> findTop5Cities(){
        return personService.findTop5Cities();
    }

    @GetMapping("/find/jobs/cities")
    public HashMap<String,String> findTopJobInCity(){
        return personService.findTopJobInCity();
    }

    @GetMapping("/cities")
    List find5CitiesHaveMostSpecificJob(@RequestParam("job") String job){
        return personService.find5CitiesHaveMostSpecificJob(job);
    }
}
