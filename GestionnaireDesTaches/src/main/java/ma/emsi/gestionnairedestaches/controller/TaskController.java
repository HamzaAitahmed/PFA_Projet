package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.gestionnairedestaches.model.*;
import ma.emsi.gestionnairedestaches.repository.ProjectRepository;
import ma.emsi.gestionnairedestaches.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class TaskController {

    @Autowired TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;


    @GetMapping(path="/task")
    public String task(Model model, @RequestParam(name = "Project_id" , defaultValue = "1" ) int Project_id, @SessionAttribute("connectedUser" ) User user1)
    {
        Collection<Task> tasks = null;
//        Collection<Project> projects2 = user1.getProjects();
//        for (Project project3 : projects2) {
//            System.out.println(project3);
//        }
        Project project = projectRepository.findProjectById(Project_id);
        if(Project_id == -1)
        {
//            Collection<Project> projects = user1.getProjects();
//            Project project = projects.iterator().next();
//            tasks = projectRepository.findProjectById(project.getId()).getTasks();
            tasks = projectRepository.findProjectById(1).getTasks();
        }else{
            tasks = projectRepository.findProjectById(Project_id).getTasks();
        }

        List<Project> ListProject = projectRepository.findByProjectOwner(user1.getId());

        model.addAttribute("user", user1);
        model.addAttribute("ListTasks", tasks);
        model.addAttribute("ListProject", ListProject);
        model.addAttribute("CurrentProject",project);

        return "Main/TaskPages/task";
    }

//    @RequestMapping(value = "/deleteTask",method = RequestMethod.GET)
//    public String deleteTask(Integer id, String search){
//        Task task = taskRepository.findTaskById(id);
////        for(Task task : tasks){
////            task.;
////        }
//        taskRepository.deleteById(id);
//
//        return "redirect:/Task?search="+search;
//    }
//
//    @RequestMapping(value = "/NewTask",method = RequestMethod.GET)
//    public String NewTask(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("connectedUser" ) User user , Model model){
//        System.out.println("NewTask Get");
//
//        List<Team> Teams = teamRepository.findAll();
//        if(Teams.isEmpty())
//        {
//            Teams = null;
//        }
//        System.out.println("NewTask Team");
//
//        Task NewTask = new Task();
//        model.addAttribute("Task",NewTask);
//        model.addAttribute("user",user);
//        model.addAttribute("ListTeams",Teams);
//        System.out.println("return Main/NewTask");
//        return "Main/TaskPages/AddTask";
//    }
//
//    @RequestMapping(value = "/NewTask",method = RequestMethod.POST)
//    public String CreateNewTask(HttpServletRequest request, HttpServletResponse response,
//                                   @ModelAttribute("connectedUser" ) User user ,
//                                   @ModelAttribute("Task") Task NewTask ,
//                                   Model model)
//    {
//        model.addAttribute("user",user);
//        try {
//            if(NewTask == null){
//                return "redirect:/NewTask?error";
//            }
//            NewTask.setTaskOwner(user);
//            System.out.println("$$$$$$$$$$$NewTask Post Task : "+NewTask+" | Team : "+NewTask.getTaskTeam()+"\n");
//
//            TaskRepository.save(NewTask);
//            return "redirect:/Task";
//
//        } catch (Exception e){
//            return "redirect:/NewTask?error";
//
//        }
//
//    }
//
//    @RequestMapping(value = "/EditTask",method = RequestMethod.GET)
//    public String EditTask(RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response,int Task_id, String search , @ModelAttribute("connectedUser" ) User user , Model model){
//        System.out.println("\nEditTask Get");
//        System.out.println("^^^^^^^^^^^^^ EditTask Get User : "+user);
//        System.out.println("^^^^^^^^^^^^^ EditTask Get EditTask id : "+Task_id);
//
//        List<Team> Teams = teamRepository.findAll();
////        Teams.removeAll( teamRepository.findNotNullTasks() );
//
//        if(Teams.isEmpty())
//        {
//            Teams = null;
//        }
//        Task EditTask = TaskRepository.findTaskById(Task_id);
//
//        List<Task> Tasks = TaskRepository.findTaskTeamByUserId(user.getId());
//
//        System.out.println("%%%%%%%%%%%%% EditTask Get :  "+EditTask);
//        model.addAttribute("PorjectList",Tasks);
//        model.addAttribute("Task",EditTask);
//        model.addAttribute("user",user);
//        model.addAttribute("ListTeams",Teams);
//        model.addAttribute("search", search);
//        redirectAttributes.addAttribute("search", search);
//        System.out.println("%%%%%%%%%%%%%%EditTask Get User : "+user);
//        System.out.println("EditTask Get Done \n");
//
//        return "Main/TaskPages/EditTask";
//    }
//
//    @RequestMapping(value = "/EditTask",method = RequestMethod.POST)
//    public String EditTask(RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response, Model model,
//                              @RequestParam(name = "nom" ) String nom,
//                              @RequestParam(name = "Task_id" ) int Task_id,
//                              @RequestParam(name = "description" ) String description,
//                              @RequestParam(name = "TaskTeam" ,defaultValue = "-1") int TaskTeam,
//                              @ModelAttribute("search" ) String search,
//                              @ModelAttribute("connectedUser" ) User user )
//    {
//        System.out.println("\n#############EditTask Post User : "+user);
//
//        System.out.println("#############  EditTask Post id : "+ Task_id + " | Nom : "+nom+" | TaskTeam : "+TaskTeam+" | description : "+description);
//
//        model.addAttribute("user",user);
//        Task EditTask = TaskRepository.findTaskById(Task_id);
//        Team Teams = teamRepository.findTeamById(TaskTeam);
//
//        try {
//            if(EditTask == null){
//                return "redirect:/EditTask?error";
//            }
//            EditTask.setNom(nom);
//            EditTask.setDescription(description);
//            EditTask.setTaskTeam(Teams);
//
//            TaskRepository.save(EditTask);
//            System.out.println("return Main/EditTask POST %%%%%%%%%%%%%\n");
//            return "redirect:/Task";
//
//        } catch (Exception e){
//            return "redirect:/EditTask?error";
//
//        }
//
//    }
}
