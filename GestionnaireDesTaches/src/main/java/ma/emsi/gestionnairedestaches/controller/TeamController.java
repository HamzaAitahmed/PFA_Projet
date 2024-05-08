package ma.emsi.gestionnairedestaches.controller;

import ma.emsi.gestionnairedestaches.controller.*;
import ma.emsi.gestionnairedestaches.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class TeamController {

    @GetMapping(path="/team")
    public String team(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/page-team";
    }

}
