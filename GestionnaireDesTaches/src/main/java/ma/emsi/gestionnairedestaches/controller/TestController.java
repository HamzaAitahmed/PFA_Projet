package ma.emsi.gestionnairedestaches.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(path="/SignIn")
    public String SignIn(Model model){ return "login"; }

    @GetMapping(path="/SignUp")
    public String SignUp(Model model){ return "register"; }

    @GetMapping(path="/error500")
    public String error500(Model model){ return "pages-error-500"; }

    @GetMapping(path="/error404")
    public String error404(Model model){ return "pages-error"; }

    @GetMapping(path="/userAdd")
    public String userAdd(Model model){ return "user-add"; }

    @GetMapping(path="/userList")
    public String userList(Model model){ return "user-list"; }

    @GetMapping(path="/userProfileEdit")
    public String userProfileEdit(Model model){ return "user-profile-edit"; }

    @GetMapping(path="/navbar")
    public String navbar(Model model){ return "navbar"; }




}