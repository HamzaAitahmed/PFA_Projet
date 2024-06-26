package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.gestionnairedestaches.model.Project;
import ma.emsi.gestionnairedestaches.model.Team;
import ma.emsi.gestionnairedestaches.model.User;
import ma.emsi.gestionnairedestaches.repository.ProjectRepository;
import ma.emsi.gestionnairedestaches.repository.TeamRepository;
import ma.emsi.gestionnairedestaches.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("connectedUser")

public class TeamController {

    @Autowired
    TeamRepository teamrepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/team")
    public String listTeams(@RequestParam(name = "search" , defaultValue = "My Teams") String search,
                            @ModelAttribute("connectedUser") User user,Model model) {
        //if (user == null) {
        //     return "redirect:/login";
        // }
        // model.addAttribute("user", user);
        // System.out.println("------------------------------------");

        // model.addAttribute("TeamsList", teamrepository.findTeamsByLeader(user.getId()));
        System.out.println("\n%%%%%%% User 3 : "+user);
        List<Team> MyTeams = teamrepository.findTeamsByLeader(user.getId());
        if(search.equals("My Teams")){
            model.addAttribute("TeamList", MyTeams);
        }


        model.addAttribute("search", search);
        model.addAttribute("user", user);
        return "Main/TeamPages/team";
    }


    @GetMapping("/{id}")
    public String viewTeam(@PathVariable Integer id, Model model, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        Team team = teamrepository.findTeamById(id);
        if (team!=null) {
            model.addAttribute("team", team);
            model.addAttribute("user", user);
            return "Main/TeamPages/team";
        } else {
            return "redirect:/teams";
        }
    }

    @GetMapping("/add")
    public String addTeamForm(Model model, @ModelAttribute("connectedUser") User user) {
      //  List<User> Members = UserRepository.;
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("team", new Team());
       // model.addAttribute("ListMembers",Members);
        return "Main/TeamPages/team-add";
    }
    @RequestMapping(value = "/create-new-team",method = RequestMethod.GET)
    public String CreateNewTeam(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("connectedUser" ) User user ,
                                   @RequestParam(name = "nom") String teamName,
                                   Model model)
    {
        model.addAttribute("user",user);
        try {
            Team team=new Team();
            System.out.println("********************************************************");
            team.setNom(teamName);
            team.setLeader(user);
            teamrepository.save(team);
            return "redirect:/team";

        } catch (Exception e){
            return "redirect:/team?error";

        }

    }


    @GetMapping("/edit-team")
    public String editTeamForm(@RequestParam("Team_id") Integer teamId, Model model,
                               @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        Team team = teamrepository.findById(teamId).orElse(null);
        if (team == null) {
            return "redirect:/team?error=notfound";
        }
        model.addAttribute("user", user);
        model.addAttribute("team", team);
        return "Main/TeamPages/EditTeam";
    }
    @RequestMapping(value="/update-team" , method = RequestMethod.GET)
    public String updateTeam(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("connectedUser") User user,
                             @RequestParam("id") Integer id,
                             @RequestParam("nom") String name,
                             Model model) {
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        try {
            Team team = teamrepository.findById(id).orElse(null);
            if (team == null) {
                return "redirect:/team?error=notfound";
            }
            team.setNom(name);
            team.setLeader(user);
            teamrepository.save(team);
            return "redirect:/team";
        } catch (Exception e) {
            return "redirect:/team?error=update";
        }

    }

    @GetMapping("DeleteTeam")
    public String deleteTeam( Integer Team_id, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        // Find the team by id
        Team team = teamrepository.findById(Team_id).orElse(null);
        if (team != null) {
            // Disassociate projects
            for (Project project : team.getProjects()) {
                project.setProjectTeam(null);
                projectRepository.save(project);
            }
            // Now delete the team
            teamrepository.delete(team);}

        return "redirect:/team";
    }
}
