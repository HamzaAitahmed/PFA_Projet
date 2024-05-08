package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.gestionnairedestaches.controller.*;
import ma.emsi.gestionnairedestaches.model.*;
import ma.emsi.gestionnairedestaches.repository.ProjectRepository;
import ma.emsi.gestionnairedestaches.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class ProjectController {

    @Autowired ProjectRepository projectRepository;
    @Autowired TeamRepository teamRepository;

    @GetMapping(path="/project")
    public String project(RedirectAttributes redirectAttributes, @ModelAttribute("connectedUser" ) User user3 , Model model){
        model.addAttribute("user", user3);
        System.out.println("# project User : "+user3);
        return "Main/page-project";
    }

//    @PostMapping(path = "/saveEtudiant")
//    public String saveEtudiant(Model model, Etudiant s,
//                               @RequestParam(name="currentPage", defaultValue = "0") int page,
//                               @RequestParam(name="IdGroup", defaultValue = "-1") int IdGroup,
//                               @RequestParam(name="size", defaultValue = "3") int size,
//                               @RequestParam(name="searchName", defaultValue = "") String search){
//
//        s.setGroups(groupsRepository.findById(IdGroup));
//        etudiantRepository.save(s);
//
//        return "redirect:/indexEtudiant?page="+page+"&size="+size+"&search="+search;
//    }
//
//
//    @GetMapping(path="/deleteEtudiant")
//    public String deleteEtudiant(@RequestParam(name="id") Integer id){
//        projectRepository.deleteById(id);
//        return "redirect:/project";
//    }
//
//
    @RequestMapping(value = "/NewProject",method = RequestMethod.GET)
    public String NewProject(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("connectedUser" ) User user , Model model){
        System.out.println("# NewProject Get ");

        List<Team> Teams = teamRepository.findAll();
        if(Teams.isEmpty())
            Teams = null;
        System.out.println("# NewProject Team : "+Teams);
        Project NewProject = new Project();
        model.addAttribute("Project",NewProject);
        model.addAttribute("user",user);
        model.addAttribute("ListTeams",Teams);
        return "Main/AddProject";
    }

    @RequestMapping(value = "/NewProject",method = RequestMethod.POST)
    public String CreateNewProject(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("connectedUser" ) User user , @ModelAttribute("Project") Project NewProject , Model model){
        model.addAttribute("user",user);
        System.out.println("# NewProject Post ");

        try {
            if(NewProject == null){
                return "redirect:/NewProject?error";
            }
            NewProject.setProjectOwner(user);
            projectRepository.save(NewProject);
            return "redirect:/project";

        } catch (Exception e){
            return "redirect:/NewProject?error";

        }

    }


}

