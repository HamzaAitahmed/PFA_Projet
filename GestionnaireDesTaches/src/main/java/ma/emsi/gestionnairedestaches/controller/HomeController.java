package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.gestionnairedestaches.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response, @SessionAttribute("connectedUser" ) User user){
        return "redirect:/project";
    }

    @GetMapping(path="/error500")
    public String error500(Model model){ return "Error/pages-error-500"; }

    @GetMapping(path="/error404")
    public String error404(Model model){ return "Error/pages-error"; }

    @GetMapping(path="/index")
    public String index(Model model,RedirectAttributes redirectAttributes, @ModelAttribute("connectedUser" ) User user2){
        System.out.println("# index User : "+user2);
        model.addAttribute("user", user2);
        return "Main/index";
    }




}
