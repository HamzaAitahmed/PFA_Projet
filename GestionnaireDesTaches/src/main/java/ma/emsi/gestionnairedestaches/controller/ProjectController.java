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

import java.util.List;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class ProjectController {


    @Autowired ProjectRepository projectRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired TaskRepository taskRepository;
    @Autowired UserRepository userRepository;

    @GetMapping(path="/project")
    public String project(HttpServletRequest request,RedirectAttributes redirectAttributes, @RequestParam(name = "search" , defaultValue = "All Projects") String search ,
//                          @ModelAttribute("connectedUser" ) User user3 ,
                          Model model )
    {
        User user3 = userRepository.findUserByEmail("hamza@gmail.com");

        HttpSession session = request.getSession(true);
        session.setAttribute("connectedUser",user3);

        List<Project> projects = null;
        if(search.equals("All Projects")){
            System.out.println("All Projects");
            projects = projectRepository.findByProjectOwner(user3.getId());
        }
        if(search.equals("My Projects")){ // Done
            System.out.println("My Projects");
            projects = projectRepository.findByProjectOwner(user3.getId());
        }

        if(search.equals("Other Projects")){
            System.out.println("Other Projects");
            projects = projectRepository.findAll();
        }
        System.out.println("user id : "+user3.getId());
        for(Project p : projects){
            System.out.println(p);
        }
        model.addAttribute("PorjectList", projects);
        model.addAttribute("search", search);
        model.addAttribute("user", user3);
        System.out.println("return Main/project");

        return "Main/ProjectPages/project";
    }

    @RequestMapping(value = "/deleteProject",method = RequestMethod.GET)
    public String deleteProject(Integer id, String search){
        List<Task> tasks = taskRepository.findProjectTaskById(id);
        for(Task task : tasks){
            task.setProjectTask(null);
        }
        projectRepository.deleteById(id);

        return "redirect:/project?search="+search;
    }

    @RequestMapping(value = "/NewProject",method = RequestMethod.GET)
    public String NewProject(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("connectedUser" ) User user , Model model){
        System.out.println("NewProject Get");

        List<Team> Teams = teamRepository.findAll();
        if(Teams.isEmpty())
        {
            Teams = null;
        }
        System.out.println("NewProject Team");

        Project NewProject = new Project();
        model.addAttribute("Project",NewProject);
        model.addAttribute("user",user);
        model.addAttribute("ListTeams",Teams);
        System.out.println("return Main/NewProject");
        return "Main/ProjectPages/AddProject";
    }

    @RequestMapping(value = "/NewProject",method = RequestMethod.POST)
    public String CreateNewProject(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("connectedUser" ) User user , @ModelAttribute("Project") Project NewProject , Model model){
        model.addAttribute("user",user);
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

    @RequestMapping(value = "/EditProject",method = RequestMethod.GET)
    public String EditProject(RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response,Integer id,String search, @ModelAttribute("connectedUser" ) User user , Model model){
        System.out.println("\nEditProject Get");

        List<Team> Teams = teamRepository.findAll();
        Teams.removeAll( teamRepository.findNotNullProjects() );

        if(Teams.isEmpty())
        {
            Teams = null;
        }

        Project EditProject = projectRepository.findProjectById(id);
        System.out.println("%%%%%%%%%%%%% EditProject Get :  "+EditProject);
        model.addAttribute("Project",EditProject);
        model.addAttribute("user",user);
        model.addAttribute("ListTeams",Teams);
        model.addAttribute("search", search);
        redirectAttributes.addAttribute("search", search);
        System.out.println("EditProject Get Done \n");

        return "Main/ProjectPages/EditProject";
    }

    @RequestMapping(value = "/EditProject",method = RequestMethod.POST)
    public String EditProject(RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response, Model model, int id,
                              @RequestParam(name = "nom" ) String nom,
                              @RequestParam(name = "description" ) String description,
                              @RequestParam(name = "ProjectTeam" ,defaultValue = "-1") int ProjectTeam,
                              @ModelAttribute("search" ) String search,
                              @ModelAttribute("connectedUser" ) User user
    )
    {
        System.out.println("\n#############  EditProject Get id : "+ id + " | Nom : "+nom+" | ProjectTeam : "+ProjectTeam+" | description : "+description);

        model.addAttribute("user",user);
        Project EditProject = projectRepository.findProjectById(id);
        Team Teams = teamRepository.findTeamById(ProjectTeam);

        try {
            if(EditProject == null){
                return "redirect:/EditProject?error";
            }
            EditProject.setNom(nom);
            EditProject.setDescription(description);
            EditProject.setProjectTeam(Teams);

            projectRepository.save(EditProject);
            System.out.println("return Main/EditProject POST %%%%%%%%%%%%%\n");
            return "redirect:/project";

        } catch (Exception e){
            return "redirect:/EditProject?error";

        }

    }
}

