//package ma.emsi.gestionnairedestaches.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import ma.emsi.gestionnairedestaches.model.User;
//import ma.emsi.gestionnairedestaches.services.UserService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("")
//public class AuthController {
//    private final UserService userService;
//
//    private final AuthenticationManager authenticationManager;
//
//    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//    }
//
//
//
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(){
//        return "login";
//    }
//
//    @RequestMapping(value = "/logout",method = RequestMethod.GET)
//    public String logout(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        session.invalidate();
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(null);
//        return "redirect:/login";
//    }
//
//
//    @RequestMapping(value = "/register",method = RequestMethod.GET)
//    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
//        User user = new User();
//        model.addAttribute("user",user);
//        return "register";
//    }
//
//
//
//
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public String createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user){
//
//        try {
//
//            User newUser = userService.createUser(user);
//            if(newUser == null){
//                return "redirect:/register?error";
//            }
//
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            securityContext.setAuthentication(authentication);
//            HttpSession session = request.getSession(true);
//            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);
//
//            return "redirect:/";
//
//        } catch (Exception e){
//            return "redirect:/register?error";
//        }
//
//    }
//
//
//}
//
