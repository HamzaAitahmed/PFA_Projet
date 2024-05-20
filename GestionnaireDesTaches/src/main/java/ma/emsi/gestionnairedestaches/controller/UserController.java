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
        System.out.println("userProfileEdit user : "+user);
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
        if(user.getPassword().equals(currentPWD)) {
            if (ConfirmationPWD.equals(newPWD)) {
                user.setPassword(newPWD);
                userRepository.save(user);
                model.addAttribute("user", user);
                model.addAttribute("active", "changePWD");
                return "Main/UserPages/user-profile-edit";
            }else{
                model.addAttribute("message", "current password Incorrect");
            }
        }else {
            model.addAttribute("message", "New password doesn't match");
        }

        model.addAttribute("user", user);
        model.addAttribute("active", "changePWD");
        model.addAttribute("error", true);
        return "Main/UserPages/user-profile-edit";

    }

    @RequestMapping(value = "/PersonnalInfo",method = RequestMethod.POST)
    public String PersonnalInfo(Model model, @ModelAttribute("connectedUser" ) User user,
                            @RequestParam(name = "image" ) String image,
                            @RequestParam(name = "FirstName" ) String FirstName,
                            @RequestParam(name = "LastName" ) String LastName,
                            @RequestParam(name = "username" ) String username,
                            @RequestParam(name = "Gender" ) String Gender,
                            @RequestParam(name = "dateOfBirth" ) Date dateOfBirth,
                            @RequestParam(name = "email" ) String email)
    {

//        userRepository.save(user);
        System.out.println("user date : "+user.getDateOfBirth());
        System.out.println("form date : "+dateOfBirth);
        System.out.println("user image : "+user.getProfilePicture());
        System.out.println("form image : "+image);
        System.out.println("user Gender : "+user.getGender());
        System.out.println("form Gender : "+ Gender );

        model.addAttribute("user", user);
        model.addAttribute("active", "changePWD");
        model.addAttribute("error", true);
        return "Main/UserPages/user-profile-edit";

    }


}
