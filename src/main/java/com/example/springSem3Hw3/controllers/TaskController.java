package com.example.springSem3Hw3.controllers;

import com.example.springSem3Hw3.domain.User;
import com.example.springSem3Hw3.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки задач
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private DataProcessingService dataProcessingService;

    /**
     * Получение списка поддерживаемых задач.
     * @return список доступных задач
     */
    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    /**
     * Сортировка пользователей по возрасту
     * @return JSON ответ с отсортированным списком пользователей
     */
    @GetMapping("/sort")
    public List<User> sortUsersByAge() {
        return dataProcessingService.sortUserByAge(dataProcessingService.getRepository().getUsers());
    }

    /**
     * Получение списка пользователей старше заданного возраста.
     * @param age возраст пользователей.
     * @return JSON ответ со списком пользователей
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable("age") int age){
        List<User> users = dataProcessingService.getRepository().getUsers();
        return dataProcessingService.filterUserByAge(users, age);
    }

    /**
     * Получение среднего возраста пользователей.
     * @return JSON ответ со средним возрастом.
     */
    @GetMapping("/calc")
    public double calculateAverageAge(){
        List<User> users = dataProcessingService.getRepository().getUsers();
        return dataProcessingService.calculateAverageAge(users);
    }


}
