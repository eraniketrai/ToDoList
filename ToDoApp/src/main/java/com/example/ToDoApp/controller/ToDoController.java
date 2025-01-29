package com.example.ToDoApp.controller;


import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService services;

    @GetMapping({"/viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message")String message){

        model.addAttribute("list",services.getAllToDoItems());
        model.addAttribute("message",message);
        return "viewToDoList";
    }

    @GetMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (services.updateStatus(id)){
            redirectAttributes.addFlashAttribute("message","Update Successful");
            return "redirect:/viewToDoList";

        }
        redirectAttributes.addFlashAttribute("message","Update Failed");
        return "redirect:/viewToDoList";

    }

    @GetMapping("/addToDoItems")
    public String addToDoItems(Model model){
        model.addAttribute("todo",new ToDo());
        return "addToDoItems";
    }

    @PostMapping("/saveToDoItems")
    public String saveToDoItems(ToDo toDo,RedirectAttributes redirectAttributes){
        if(services.saveOrUpdateToDoItem(toDo)){
            redirectAttributes.addFlashAttribute("message","Saved Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message","Failed To Save");
        return "redirect:/addToDoItems";
    }
    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id,Model model){
        model.addAttribute("todo",services.getToDoItemsByID(id));
        return "editToDoItems";
    }

    @PostMapping("/editSaveToDoItem/{id}")
    public String editSaveToDoItem(ToDo toDo,RedirectAttributes redirectAttributes){
        if (services.saveOrUpdateToDoItem(toDo)){
            redirectAttributes.addFlashAttribute("message","Edited Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message","Edit Unsuccessful");
        return "redirect:/editToDoItem/" + toDo.getId();
    }
    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id,RedirectAttributes redirectAttributes){
        if (services.deleteToDoItem(id)){
            redirectAttributes.addFlashAttribute("message","Deleted Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message","Deletion Unsuccessful");
        return "redirect:/viewToDoList";
    }
}
