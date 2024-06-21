package ma.emsi.gestionnairedestaches.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.gestionnairedestaches.model.*;
import ma.emsi.gestionnairedestaches.repository.ProjectRepository;
import ma.emsi.gestionnairedestaches.repository.TaskRepository;
import ma.emsi.gestionnairedestaches.repository.UserRepository;
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
    @Autowired ProjectRepository projectRepository;
    @Autowired UserRepository userRepository;


    @GetMapping(path="/task")
    public String task(Model model, @RequestParam(name = "Project_id" , defaultValue = "-1" ) int Project_id, @SessionAttribute("connectedUser" ) User user)
    {
        Project project;
        if(Project_id == -1)
        {
            List<Project> projects = projectRepository.findByProjectOwner(user.getId());
            project = projects.get(0);
            Project_id = project.getId();
        }else{
            project = projectRepository.findProjectById(Project_id);
        }


        List<Project> ListProject = projectRepository.findByProjectOwner(user.getId());
        List<Task> ListTaskDone = taskRepository.findProjectTaskByTaskDone(Project_id,true);
        List<Task> ListTaskNotDone = taskRepository.findProjectTaskByTaskDone(Project_id,false);

        model.addAttribute("user", user);
        model.addAttribute("ListTaskDone", ListTaskDone);
        model.addAttribute("ListTaskNotDone", ListTaskNotDone);
        model.addAttribute("ListProject", ListProject);
        model.addAttribute("CurrentProject",project);

        return "Main/TaskPages/task";
    }

    @GetMapping(path="/TaskStatus")
    public String taskStatus(@RequestParam(name = "Project_id" ) int Project_id,@RequestParam(name = "Task_id" ) int Task_id)
    {
        Task taskCheck = taskRepository.findTaskById(Task_id);
        taskCheck.setTaskDone( ! ( taskCheck.isTaskDone() ) ) ; // convert task status if its Done or Not Done
        taskRepository.save(taskCheck);
        return "redirect:/task?Project_id="+Project_id;
    }

    @RequestMapping(value = "/DeleteTask",method = RequestMethod.GET)
    public String deleteTask(@RequestParam(name = "Project_id" ) int Project_id,@RequestParam(name = "Task_id" ) int Task_id){
        taskRepository.deleteById(Task_id);
        return "redirect:/task?Project_id="+Project_id;
    }

    @RequestMapping(value = "/NewTask",method = RequestMethod.GET)
    public String NewTask(@ModelAttribute("connectedUser" ) User user , @RequestParam(name = "Project_id") int Project_id, Model model)
    {
        Project ObjProject = projectRepository.findProjectById(Project_id);

        Task NewTask = new Task();
        NewTask.setProjectTask(ObjProject);

        Team team = ObjProject.getProjectTeam();
        Collection<User> users = null;
        if(team!=null)
        {
            users = team.getMembers();
            users.add(team.getLeader());
            users.add(ObjProject.getProjectOwner());
        }

        List<Task> ListTaskDone = taskRepository.findProjectTaskByTaskDone(Project_id,true);
        List<Task> ListTaskNotDone = taskRepository.findProjectTaskByTaskDone(Project_id,false);

        model.addAttribute("NewTask",NewTask);
        model.addAttribute("Project_id",Project_id);
        model.addAttribute("users",users);
        model.addAttribute("user",user);
        model.addAttribute("ListTaskDone", ListTaskDone);
        model.addAttribute("ListTaskNotDone", ListTaskNotDone);
        model.addAttribute("CurrentProject",ObjProject);
        return "Main/TaskPages/AddTask";
    }

    @RequestMapping(value = "/NewTask",method = RequestMethod.POST)
    public String AddNewTask(@ModelAttribute("connectedUser" ) User user ,
                                   @ModelAttribute("NewTask") Task NewTask ,
                                   @ModelAttribute("Project_id") Integer Project_id )
    {
        System.out.println("NewTask Post : "+NewTask);
        try {
            if(NewTask == null){
                return "redirect:/NewTask?Project_id="+Project_id+"&error";
            }
            taskRepository.save(NewTask);
            projectRepository.save(projectRepository.findProjectById(Project_id));
            return "redirect:/task?Project_id="+Project_id;

        } catch (Exception e){
            System.out.println("NewTask erro exception ");
            return "redirect:/NewTask?Project_id="+Project_id+"&error";
        }

    }


//    @RequestMapping(value = "/EditTask",method = RequestMethod.GET)
//    public String EditTask(@ModelAttribute("connectedUser" ) User user , @RequestParam(name = "Project_id") int Project_id,@RequestParam(name = "Task_id") int Task_id, Model model)
//    {
//        Project ObjProject = projectRepository.findProjectById(Project_id);
//        Project ObjTask = projectRepository.findProjectById(Task_id);
//
//        Team team = ObjProject.getProjectTeam();
//        Collection<User> users = null;
//        if(team!=null)
//        {
//            users = team.getMembers();
//            users.add(team.getLeader());
//            users.add(ObjProject.getProjectOwner());
//        }
//
//        List<Task> ListTaskDone = taskRepository.findProjectTaskByTaskDone(Project_id,true);
//        List<Task> ListTaskNotDone = taskRepository.findProjectTaskByTaskDone(Project_id,false);
//
//        model.addAttribute("EditTask",ObjTask);
//        model.addAttribute("Project_id",Project_id);
//        model.addAttribute("users",users);
//        model.addAttribute("user",user);
//        model.addAttribute("ListTaskDone", ListTaskDone);
//        model.addAttribute("ListTaskNotDone", ListTaskNotDone);
//        model.addAttribute("CurrentProject",ObjProject);
//        return "Main/TaskPages/AddTask";
//    }
//
//    @RequestMapping(value = "/EditTask",method = RequestMethod.POST)
//    public String EditTask(@ModelAttribute("connectedUser" ) User user ,
//                                @ModelAttribute("NewTask") Task NewTask ,
//                                @ModelAttribute("Project_id") Integer Project_id )
//    {
//        System.out.println("NewTask Post : "+NewTask);
//        try {
//            if(NewTask == null){
//                return "redirect:/NewTask?Project_id="+Project_id+"&error";
//            }
//            taskRepository.save(NewTask);
//            projectRepository.save(projectRepository.findProjectById(Project_id));
//            return "redirect:/task?Project_id="+Project_id;
//
//        } catch (Exception e){
//            System.out.println("NewTask erro exception ");
//            return "redirect:/NewTask?Project_id="+Project_id+"&error";
//        }
//
//    }


}
