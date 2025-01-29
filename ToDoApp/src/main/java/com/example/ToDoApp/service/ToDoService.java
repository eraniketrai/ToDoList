package com.example.ToDoApp.service;

import com.example.ToDoApp.repo.IToDoRepo;
import com.example.ToDoApp.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    IToDoRepo repo;

    // Get all ToDo items from the repository
    public List<ToDo> getAllToDoItems(){
        List<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(todo -> todoList.add(todo));
        return todoList;
    }

    // Get ToDo item by ID, throws exception if not found
    public ToDo getToDoItemsByID(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo item not found for ID " + id));
    }

    // Update the status of the ToDo item with a given ID
    public Boolean updateStatus(Long id){
        ToDo todo = getToDoItemsByID(id);
        todo.setStatus("Completed");
        return saveOrUpdateToDoItem(todo);
    }

    // Save or update a ToDo item in the repository
    public boolean saveOrUpdateToDoItem(ToDo todo){
        ToDo updatedObj = repo.save(todo);
        return updatedObj != null;
    }

    // Delete a ToDo item by ID
    public boolean deleteToDoItem(Long id){
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
