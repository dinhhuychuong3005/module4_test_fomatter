package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.service.EmployeeServiceImpl;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/add-employee")
    public ModelAndView inputEmployee() {
        ModelAndView modelAndView = new ModelAndView("/employee/form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @GetMapping(value = "/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors());
            return "/employee/form";
        }

        // save employee here...
        employeeService.save(employee);
        model.addAttribute("employee", employee);
        return "/employee/view";
    }
}
