package com.techmaster.read_csv.service;

import com.techmaster.read_csv.model.PersonDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IPersonService {
    void readCSV(String csvFile);
    void add(PersonDTO personDTO);
    List getAll();
    List sortPeopleByFullName();
    List getSortedJobs();
    List getSortedCities();
    Map<String,Integer> findTop5Jobs();
    Map<String,Integer> findTop5Cities();
    HashMap<String,List> groupPeopleByCity();
    HashMap<String,Integer> groupJobByCount(List<PersonDTO> list);
    HashMap<String,String> findTopJobInCity();
    List find5CitiesHaveMostSpecificJob(String job);
}
