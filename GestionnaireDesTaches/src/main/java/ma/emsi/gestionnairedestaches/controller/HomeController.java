package ma.emsi.gestionnairedestaches.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "redirect:/userList";
    }

    @GetMapping(path="/error500")
    public String error500(Model model){ return "Error/pages-error-500"; }

    @GetMapping(path="/error404")
    public String error404(Model model){ return "Error/pages-error"; }

    @GetMapping(path="/userAdd")
    public String userAdd(Model model){ return "Main/user-add"; }

    @GetMapping(path="/userList")
    public String userList(Model model){ return "Main/user-list"; }

    @GetMapping(path="/userProfileEdit")
    public String userProfileEdit(Model model){ return "Main/user-profile-edit"; }

    @GetMapping(path="/navbar")
    public String navbar(Model model){ return "Section/navbar"; }

}
