package ma.emsi.gestionnairedestaches.controller;

import ma.emsi.gestionnairedestaches.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class TeamController {

    @GetMapping(path="/team")
    public String team(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/TeamPages/team";
    }

}
