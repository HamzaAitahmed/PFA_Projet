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
public class UserController {

    @GetMapping(path="/userList")
    public String userList(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/user-list";
    }

    @GetMapping(path="/userProfil")
    public String userProfil(Model model){ return "Main/user-profile"; }

    @GetMapping(path="/userProfileEdit")
    public String userProfileEdit(Model model){ return "Main/user-profile-edit"; }

    @GetMapping(path="/userAdd")
    public String userAdd(Model model){ return "Main/user-add"; }

}
