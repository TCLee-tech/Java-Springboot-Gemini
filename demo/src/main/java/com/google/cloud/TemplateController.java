
package com.google.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
// don't name the class below "Controller" to avoid confusion with the @Controller annotation. Custom annovations are Java classes.
public class TemplateController {

    /*
    a reference to a Service interface -> class that will do actual work of calling GenAI model
    private final ServiceInterface service;

    public UseController (ServiceInterface service) {
        this.service = service;
    }
     */

    @GetMapping("/")
    public String showUserEntryForm (Model model) {

        //adding the attribute(key-value pair)
        model.addAttribute("pojo", new Pojo());
        //returning the view name
        return "index";
    }

    //when user submits form, this method is called
    @PostMapping("/")
    public String submitForm(@ModelAttribute("pojo") Pojo pojo, Model model) {
        System.out.println(pojo);    
        //call to service interface/class for work with GenAI model
        //service.usePojo(pojo.toParameters())
        return "redirect:index";
    }
}    


//a good clear explanation
// https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial