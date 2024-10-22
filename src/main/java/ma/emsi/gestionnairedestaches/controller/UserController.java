package ma.emsi.gestionnairedestaches.controller;

import ma.emsi.gestionnairedestaches.model.*;
import ma.emsi.gestionnairedestaches.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/userList")
    public String userList(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/UserPages/user-list";
    }

    @GetMapping(path="/userProfil")
    public String userProfil(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/UserPages/user-profile";
    }

    @GetMapping(path="/userProfileEdit")
    public String userProfileEdit(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
//        System.out.println("userProfileEdit user : "+user);
        return "Main/UserPages/user-profile-edit";
    }

    @GetMapping(path="/userAdd")
    public String userAdd(Model model, @ModelAttribute("connectedUser" ) User user){
        model.addAttribute("user", user);
        return "Main/UserPages/user-add";
    }

    @RequestMapping(value = "/changePWD",method = RequestMethod.POST)
    public String changePWD(Model model, @ModelAttribute("connectedUser" ) User user,
                        @RequestParam(name = "currentPWD" ) String currentPWD,
                        @RequestParam(name = "newPWD" ) String newPWD,
                        @RequestParam(name = "ConfirmationPWD" ) String ConfirmationPWD)
    {
//        if (user.getPassword()==null){
//            System.out.println("111111");
//            return "redirect:/userProfileEdit";
//        }
        if(user.getPassword()!=null && user.getPassword().equals(currentPWD))
        {
            System.out.println("22222");
            if (ConfirmationPWD.equals(newPWD)) {
                user.setPassword(newPWD);
                userRepository.save(user);
                model.addAttribute("user", user);
                model.addAttribute("active", "changePWD");
                return "Main/UserPages/user-profile-edit";
            }
        }else {
            System.out.println("33333");
            if (ConfirmationPWD.equals(newPWD)) {
                System.out.println("44444");
                user.setPassword(newPWD);
                userRepository.save(user);
                model.addAttribute("user", user);
                model.addAttribute("active", "changePWD");
                return "Main/UserPages/user-profile-edit";
            }
        }


        model.addAttribute("user", user);
        model.addAttribute("active", "changePWD");
        model.addAttribute("error", true);
        return "redirect:/userProfileEdit";

    }

    @RequestMapping(value = "/PersonnalInfo",method = RequestMethod.POST)
    public String PersonnalInfo(Model model, @ModelAttribute("connectedUser" ) User user )
    {

        userRepository.save(user);
//        System.out.println(user);
//        System.out.println("form image : "+image);

        model.addAttribute("user", user);
        model.addAttribute("active", "changePWD");
        model.addAttribute("error", true);
        return "redirect:/userProfileEdit";

    }


}
