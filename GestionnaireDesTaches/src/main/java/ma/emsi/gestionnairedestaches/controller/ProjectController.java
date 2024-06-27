package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.emsi.gestionnairedestaches.model.*;
import ma.emsi.gestionnairedestaches.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class ProjectController {


    @Autowired ProjectRepository projectRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired TaskRepository taskRepository;
    @Autowired UserRepository userRepository;

    @GetMapping(path="/project")
    public String project(@RequestParam(name = "search" , defaultValue = "My Projects") String search , @ModelAttribute("connectedUser" ) User user3 , Model model )
    {
        System.out.println("\n%%%%%%% User 3 : "+user3);
        List<Project> OtherProjects = projectRepository.findOtherProjectByUserId(user3.getId());

        List<Project> MyProjects = projectRepository.findByProjectOwner(user3.getId());

        List<Project> AllProjects = projectRepository.findAllProjectByUserId(user3.getId());

//        Collections.sort(AllProjects , Comparator.comparingLong(Project::getId)); // sort List by Project Id


        if(search.equals("My Projects")){ // Done
            model.addAttribute("PorjectList", MyProjects);
        }
        if(search.equals("Other Projects")){
            model.addAttribute("PorjectList", OtherProjects);
        }
        if(search.equals("All Projects")){
            model.addAttribute("PorjectList", AllProjects);
        }
        model.addAttribute("search", search);
        model.addAttribute("user", user3);

        return "Main/ProjectPages/project";
    }

    @RequestMapping(value = "/deleteProject",method = RequestMethod.GET)
    public String deleteProject(Integer Project_id, String search){
        List<Task> tasks = taskRepository.findProjectTaskById(Project_id);
        for(Task task : tasks){
            task.setProjectTask(null);
        }
        projectRepository.deleteById(Project_id);

        return "redirect:/project?search="+search;
    }

    @RequestMapping(value = "/NewProject",method = RequestMethod.GET)
    public String NewProject(RedirectAttributes redirectAttributes ,
                             @ModelAttribute("connectedUser" ) User user ,
                             String search , Model model){
        System.out.println("NewProject Get");
        System.out.println("Search : "+search);

        List<Team> Teams = teamRepository.findAll();
        if(Teams.isEmpty())
        {
            Teams = null;
        }
        System.out.println("NewProject Team");

        List<Project> OtherProjects = projectRepository.findOtherProjectByUserId(user.getId());

        List<Project> MyProjects = projectRepository.findByProjectOwner(user.getId());

        List<Project> AllProjects = projectRepository.findAllProjectByUserId(user.getId());

//        Collections.sort(AllProjects , Comparator.comparingLong(Project::getId)); // sort List by Project Id


        if(search.equals("My Projects")){ // Done
            model.addAttribute("PorjectList", MyProjects);
        }
        if(search.equals("Other Projects")){
            model.addAttribute("PorjectList", OtherProjects);
        }
        if(search.equals("All Projects")){
            model.addAttribute("PorjectList", AllProjects);
        }

        System.out.println("SEachrv :: "+search);
        Project NewProject = new Project();
        model.addAttribute("Project",NewProject);
        model.addAttribute("user",user);
        model.addAttribute("search",search);
        model.addAttribute("ListTeams",Teams);
        redirectAttributes.addAttribute("search", search);
        return "Main/ProjectPages/AddProject";
    }

    @RequestMapping(value = "/NewProject",method = RequestMethod.POST)
    public String CreateNewProject(@ModelAttribute("connectedUser" ) User user ,
                                   @ModelAttribute("Project") Project NewProject ,
                                   String search , Model model)
    {
        model.addAttribute("user",user);
        System.out.println("#### NewProject POST Search : "+search);
        try {
            if(NewProject == null){
                return "redirect:/NewProject?error";
            }
            NewProject.setProjectOwner(user);
            projectRepository.save(NewProject);
            return "redirect:/project?search="+search;

        } catch (Exception e){
            return "redirect:/NewProject?error";

        }

    }

    @RequestMapping(value = "/EditProject",method = RequestMethod.GET)
    public String EditProject(RedirectAttributes redirectAttributes , int Project_id , String search ,
                              @ModelAttribute("connectedUser" ) User user , Model model)
    {

        List<Team> Teams = teamRepository.findAll();
//        Teams.removeAll( teamRepository.findNotNullProjects() );

        if(Teams.isEmpty())
        {
            Teams = null;
        }
        Project EditProject = projectRepository.findProjectById(Project_id);

        List<Project> OtherProjects = projectRepository.findOtherProjectByUserId(user.getId());

        List<Project> MyProjects = projectRepository.findByProjectOwner(user.getId());

        List<Project> AllProjects = projectRepository.findAllProjectByUserId(user.getId());

//        Collections.sort(AllProjects , Comparator.comparingLong(Project::getId)); // sort List by Project Id


        if(search.equals("My Projects")){ // Done
            model.addAttribute("PorjectList", MyProjects);
        }
        if(search.equals("Other Projects")){
            model.addAttribute("PorjectList", OtherProjects);
        }
        if(search.equals("All Projects")){
            model.addAttribute("PorjectList", AllProjects);
        }

        model.addAttribute("Project",EditProject);
        model.addAttribute("user",user);
        model.addAttribute("ListTeams",Teams);
        model.addAttribute("search", search);
        redirectAttributes.addAttribute("search", search);

        return "Main/ProjectPages/EditProject";
    }

    @RequestMapping(value = "/EditProject",method = RequestMethod.POST)
    public String EditProject(@RequestParam(name = "nom" ) String nom,
                              @RequestParam(name = "Project_id" ) int Project_id,
                              @RequestParam(name = "description" ) String description,
                              @RequestParam(name = "ProjectTeam" ,defaultValue = "-1") int ProjectTeam,
                              @ModelAttribute("search" ) String search,
                              @ModelAttribute("connectedUser" ) User user ,Model model )
    {

        model.addAttribute("user",user);
        Project EditProject = projectRepository.findProjectById(Project_id);
        Team Teams = teamRepository.findTeamById(ProjectTeam);

        try {
            if(EditProject == null){
                return "redirect:/EditProject?error";
            }
            EditProject.setNom(nom);
            EditProject.setDescription(description);
            EditProject.setProjectTeam(Teams);
            if (Teams == null)
            {
                for(Task task : EditProject.getTasks())
                {
                    task.setUserTask(null);
//                    taskRepository.save(task);
                }
            }
            projectRepository.save(EditProject);
            return "redirect:/project?search="+search;

        } catch (Exception e){
            return "redirect:/EditProject?error";

        }

    }
}

