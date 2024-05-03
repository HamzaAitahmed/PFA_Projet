package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.emsi.gestionnairedestaches.model.User;
import ma.emsi.gestionnairedestaches.repository.UserRepository;
import ma.emsi.gestionnairedestaches.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class AuthController {
    private final UserService userService;


    @Autowired UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private User ConnectedUser ;
    public AuthController(UserService userService,AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        System.out.println("home GET ");

        return "Auth/home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        System.out.println("Login Get ");

        model.addAttribute("message","Login Failed");
        return "Auth/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user,@ModelAttribute("email" ) String email,@ModelAttribute("password") String password, Model model){
        model.addAttribute("message","Login XXX");
        System.out.println("Login Post ");

        if(user!=null)
        {
            email=user.getEmail();
        }
        System.out.println(user.toString());

        System.out.println("Login user ");

        try {
            User OldUser = userRepository.findUserByEmail(email);
            ConnectedUser = OldUser;
            System.out.println("Login Post ");

            if(OldUser == null){
                model.addAttribute("message","Email Not Found");
                return "redirect:/login?error";
            }
            System.out.println("Login Post ");

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            return "redirect:/";

        } catch (Exception e){
            return "redirect:/login?error";
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,Model model){
        System.out.println("Logout GET");
        model.addAttribute("message","Logout Failed");
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        System.out.println(ConnectedUser);
        model.addAttribute("user",ConnectedUser);
        return "Auth/logout";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user, @ModelAttribute("password") String password, Model model ){
        System.out.print("email : "+user.getEmail()+"password : "+password);

        model.addAttribute("message","Login XXX");
        System.out.println("Logout Post ");

        try {
            User OldUser = userRepository.findUserByEmail(user.getEmail());
            ConnectedUser = OldUser;
            if(OldUser == null){
                model.addAttribute("message","Email Not Found");
                return "redirect:/logout?error";
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),password));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            return "redirect:/";

        } catch (Exception e){
            return "redirect:/logout?error";
        }

    }



    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("message","Registration Failed");
        return "Auth/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user , Model model){
        String message = "Registration Failed";
        model.addAttribute("message",message);
        try {
            user.setRole("USER");
            User newUser = userService.createUser(user);
            if(newUser == null){
                return "redirect:/register?error&message="+message;
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
//            HttpSession session = request.getSession(true);
//            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            return "redirect:/login";

        } catch (Exception e){
            return "redirect:/register?error&message="+message;

        }

    }


}
