package com.example.objectmapperdemo.resttemplate;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class templateController {

    @GetMapping("/getObject")
    public String getObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                Student.class);


        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println(("Phone: " + student.getContactPhone()));

        return "Send HTTP request to getObject success";
    }

    @GetMapping("/getObjectWithParams")
    public String getObjectWithParams() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                Student.class,
                queryParamMap);

        return "Send HTTP request to getObjectWithParams success";
    }

    @GetMapping("/getEntity")
    public String getEntity() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Student> entity = restTemplate.getForEntity(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                Student.class);

        System.out.println("Status Code: " + entity.getStatusCode());

        Student student = entity.getBody();
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println(("Phone: " + student.getContactPhone()));

        return "Send HTTP request to getObject success";
    }

    @GetMapping("/postObject")
    public String postObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        Student result = restTemplate.postForObject(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                studentRequestBody,
                Student.class
        );
        return "Send HTTP request to postObject success";
    }

    @GetMapping("/postEntity")
    public String postEntity() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        ResponseEntity<Student> result = restTemplate.postForEntity(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                studentRequestBody,
                Student.class
        );
        return "Send HTTP request to postEntity success";
    }

    @GetMapping("exchange")
    public String exchange() {
        // 1. Http GET request
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("header1", "1234");

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        ResponseEntity<Student> responseEntity = restTemplate.exchange(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                HttpMethod.GET,
                httpEntity,
                Student.class,
                queryParamMap
        );

        // 2. Http POST request
        HttpHeaders httpHeaders2 = new HttpHeaders();
        httpHeaders2.set("header2", "5678");
        httpHeaders2.setContentType(MediaType.APPLICATION_JSON);    // POST must have

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");
        HttpEntity httpEntity2 = new HttpEntity(studentRequestBody, httpHeaders2);

        ResponseEntity<Student> responseEntity2 = restTemplate.exchange(
                "https://mocki.io/v1/0de47ecc-1e67-4eab-81c1-c141d51c9c97",
                HttpMethod.POST,
                httpEntity2,
                Student.class
        );
        return "Exchange success";
    }
}
