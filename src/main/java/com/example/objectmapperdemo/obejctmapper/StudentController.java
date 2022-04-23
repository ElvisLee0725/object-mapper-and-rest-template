package com.example.objectmapperdemo.obejctmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {
        Student student = new Student();
        student.setId(1);
        student.setName("Jordan");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(student);
        System.out.println(result);

        String myJson = "{\"id\":3,\"name\":\"Mike\"}";
        Student student1 = objectMapper.readValue(myJson, Student.class);
        System.out.println(student1.getId());
        System.out.println(student1.getName());

        return "success";
    }
}
