package ma.emsi.gestionnairedestaches.controller;

import ma.emsi.gestionnairedestaches.controller.*;
import ma.emsi.gestionnairedestaches.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class TaskController {
    @GetMapping(path="/task")
    public String task(Model model, RedirectAttributes redirectAttributes, @SessionAttribute("connectedUser" ) User user1){
        System.out.println("# task User : "+user1);
        model.addAttribute("user", user1);
        return "Main/page-task";
    }
}
