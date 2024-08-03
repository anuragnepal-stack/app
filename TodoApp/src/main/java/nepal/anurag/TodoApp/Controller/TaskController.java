package nepal.anurag.TodoApp.Controller;

import lombok.Getter;
import nepal.anurag.TodoApp.Entity.Task;
import nepal.anurag.TodoApp.Entity.TodoRequest;
import nepal.anurag.TodoApp.Entity.User;
import nepal.anurag.TodoApp.Repository.TaskRepository;
import nepal.anurag.TodoApp.Service.EmailService;
import nepal.anurag.TodoApp.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public Task createTodo(@RequestBody TodoRequest todoRequest) {
        return taskService.createTodo(todoRequest);
    }
@GetMapping("/show")
    public List<Task>showall()
{
    return taskService.showAll();
}


    @GetMapping("/user/{uid}")
    public List<Task> getTasksByUid(@PathVariable int uid) {
        return taskService.getTasksByUid(uid);
    }



    @PutMapping("/update/{tid}")
    public Task updateTnameForTask(@PathVariable int tid, @RequestBody Task task) {
        return taskService.updateTnameForTask(tid, task);

    }

    @PutMapping("/updatestatus/{tid}")
    public String update(@PathVariable int tid, @RequestBody Task task) {
         taskService.updateComplete(tid, task);
         return "The Task is Completed Hence now its deleted";

    }

@PostMapping("/email")
    public String emailSender()

    {
        emailService.checkForDueDates();

return "The Email Was Successfully Sent";
    }



    }


