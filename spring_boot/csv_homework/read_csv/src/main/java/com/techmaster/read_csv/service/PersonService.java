package com.techmaster.read_csv.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.techmaster.read_csv.model.PersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class PersonService implements IPersonService{
    private List<PersonDTO> collections = new ArrayList<>();

    public PersonService(String csvFile){
        this.readCSV(csvFile);
    }
    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(','); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(PersonDTO.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<PersonDTO> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                PersonDTO person = mi.next();
                this.add(person);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void add( PersonDTO personDTO) {
        int id;
        if (collections.isEmpty()) {
            id = 1;
        } else {
            PersonDTO lastPerson = collections.get(collections.size() - 1);
            id = lastPerson.getId() + 1;
        }
        personDTO.setId(id);
        collections.add(personDTO);
    }

    @Override
    public List getAll() {
        return collections;
    }

    @Override
    public List sortPeopleByFullName() {
        Collections.sort(collections, new Comparator<PersonDTO>() {
            @Override
            public int compare(PersonDTO o1, PersonDTO o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        return collections;
    }

    @Override
    public List getSortedJobs() {
        Collections.sort(collections, new Comparator<PersonDTO>() {
            @Override
            public int compare(PersonDTO o1, PersonDTO o2) {
                return o1.getJob().compareTo(o2.getJob());
            }
        });
        return collections;
    }

    @Override
    public List getSortedCities() {
        Collections.sort(collections, new Comparator<PersonDTO>() {
            @Override
            public int compare(PersonDTO o1, PersonDTO o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        return collections;
    }

    @Override
    public Map<String, Integer> findTop5Jobs() {
        Map<String, Integer> newMap = groupJobByCount(collections);

        List<Map.Entry<String, Integer>> subList = new ArrayList<>(newMap.entrySet());
        Collections.sort(subList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue() > 0 ? -1 : 1;
            }
        });

        newMap.clear();
        for(int i = 0; i < 5; i++){
            newMap.put(subList.get(i).getKey(), subList.get(i).getValue());
        }
        return newMap;
    }

    @Override
    public Map<String, Integer> findTop5Cities() {

        Map<String, Integer> newMap = new HashMap<>();
        for(PersonDTO person : collections){
            newMap.put(person.getCity(), newMap.getOrDefault(person.getCity(), 0) + 1);
        }

        List<Map.Entry<String, Integer>> subList = new ArrayList<>(newMap.entrySet());
        Collections.sort(subList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue() > 0 ? -1 : 1;
            }
        });

        newMap.clear();
        for(int i = 0; i < 5; i++){
            newMap.put(subList.get(i).getKey(), subList.get(i).getValue());
        }
        return newMap;
    }

    @Override
    public HashMap<String, List> groupPeopleByCity() {
        HashMap<String, List> newMap = new HashMap<>();
        for(PersonDTO person : collections){
            if(!newMap.containsKey(person.getCity())){
                List<PersonDTO> subList = new ArrayList<>();
                subList.add(person);
                newMap.put(person.getCity(), subList);
            } else {
                List<PersonDTO> subList = newMap.get(person.getCity());
                subList.add(person);
                newMap.put(person.getCity(), subList);

            }
        }
        return newMap;
    }

    @Override
    public HashMap<String, Integer> groupJobByCount(List<PersonDTO> list) {
        HashMap<String, Integer> newMap = new HashMap<>();
        for(PersonDTO person : list){
            newMap.put(person.getJob(), newMap.getOrDefault(person.getJob(), 0) + 1);
        }
        return newMap;
    }

    @Override
    public HashMap<String, String> findTopJobInCity() {
        HashMap<String, String> newMap = new HashMap<>();
        HashMap<String, List> subMap = groupPeopleByCity();
        for(String str : subMap.keySet()){
            List<PersonDTO> list = subMap.get(str);
            HashMap<String, Integer> miniMap = groupJobByCount(list);
            int max = Integer.MIN_VALUE;
            for(String str2 : miniMap.keySet()){
                if(miniMap.get(str2) > max){
                    newMap.put(str, str2);
                    max = miniMap.get(str2);
                }
            }
        }
        return newMap;
    }

    @Override
    public List find5CitiesHaveMostSpecificJob(String job) {
        HashMap<String, Integer> newMap = new HashMap<>();
        collections.stream().filter(person -> job.equals(person.getJob())).forEach(fillPerson -> {
            newMap.put(fillPerson.getCity(), newMap.getOrDefault(fillPerson.getCity(), 0) + 1);
        });
        List<Map.Entry<String, Integer>> subList = new ArrayList<>(newMap.entrySet());
        Collections.sort(subList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue() > 0 ? -1 : 1;
            }
        });
        List<String> newList = new ArrayList<>();
        int i = 0;
        while (i < 5){
            newList.add(subList.get(i).getKey());
            i++;
        }
        return newList;
    }


}
