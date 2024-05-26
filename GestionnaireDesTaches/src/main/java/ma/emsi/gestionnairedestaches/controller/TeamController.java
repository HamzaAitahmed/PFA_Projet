package ma.emsi.gestionnairedestaches.controller;

import ma.emsi.gestionnairedestaches.model.Team;
import ma.emsi.gestionnairedestaches.model.User;
import ma.emsi.gestionnairedestaches.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes("connectedUser")
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamRepository teamrepository;
  

    @GetMapping
    public String listTeams(Model model, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("teams", teamrepository.findAll());
        return "Main/TeamPages/team-list";
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
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("team", new Team());
        return "Main/TeamPages/team-add";
    }

    @PostMapping
    public String addTeam(@ModelAttribute Team team, Model model, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        team.setLeader(user); // Assume the connected user is the leader for simplicity
        teamrepository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{id}")
    public String editTeamForm(@PathVariable Integer id, Model model, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        Team teams = teamrepository.findTeamById(id);

        if (teams!=null) {
            model.addAttribute("teams", teams);
            model.addAttribute("user", user);
            return "Main/TeamPages/team-edit";
        } else {
            return "redirect:/teams";
        }
    }

    @PostMapping("/edit/{id}")
    public String editTeam(@PathVariable Integer id, @ModelAttribute Team team, Model model, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        team.setId(id);
        teamrepository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Integer id, @ModelAttribute("connectedUser") User user) {
        if (user == null) {
            return "redirect:/login";
        }
        teamrepository.deleteById(id);
        return "redirect:/teams";
    }
}
