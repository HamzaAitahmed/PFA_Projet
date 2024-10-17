package ma.emsi.gestionnairedestaches.controller;
import ma.emsi.gestionnairedestaches.model.*;

import ma.emsi.gestionnairedestaches.repository.ProjectRepository;
import ma.emsi.gestionnairedestaches.repository.TaskRepository;
import ma.emsi.gestionnairedestaches.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("connectedUser")
public class test {
    @Autowired TaskRepository taskRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired UserRepository userRepository;
    @GetMapping(path="/AjouteUserTeam")
    public String AjouteUserTeam(Model model , int currentPage, Team team, int size, String searchName,
                                      @ModelAttribute("connectedUser" ) User user ,
                                      @RequestParam(name="idUserAjouter", defaultValue = "-1") int idUserAjouter,
                                      @RequestParam(name="Nom", defaultValue = "xxxx") String Nom,
                                      @RequestParam(name="matricule", defaultValue = "xxxx") String matricule,
                                      @RequestParam(name="id", defaultValue = "-1") int id)
    {
        User etd = userRepository.findUserById(idUserAjouter);
        if (etd != null) {
//            etd.setMyTeams(team);
            userRepository.save(etd);
        }
        model.addAttribute("user", user);
        return "redirect:/editTeam?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }




    @GetMapping(path="/DeleteUserTeam")
    public String DeleteUserTeam(int currentPage,Team team,int size,String searchName,
                                      @RequestParam(name="idUserSupprimer", defaultValue = "-1") int idUser,
                                      @RequestParam(name="id", defaultValue = "-1") int id)
    {
        User etd = userRepository.findUserById(idUser);
        if (etd != null) {
//            etd.setTeam(null);
            userRepository.save(etd);
        }
        return "redirect:/editTeam?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }
}
