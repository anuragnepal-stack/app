package nepal.anurag.TodoApp.Service;

import nepal.anurag.TodoApp.Entity.Task;
import nepal.anurag.TodoApp.Entity.TodoRequest;
import nepal.anurag.TodoApp.Entity.User;
import nepal.anurag.TodoApp.Repository.TaskRepository;
import nepal.anurag.TodoApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;



//Creating a new task
    public Task createTodo(@RequestBody TodoRequest todoRequest) {
        User user = userRepository.findById(todoRequest.getUid()).orElse(null);
        if (user != null) {
            Task todo = new Task();
            todo.setTname(todoRequest.getTname()); // set tname from TodoRequest
            todo.setEnd(todoRequest.getEnd()); // set end from TodoRequest
            todo.setUser(user);
            todo.setCompleted(false);
            todo.setStart(LocalDate.now());
            return taskRepository.save(todo);
        } else {
            throw new RuntimeException("User Not found");
        }

    }

    //Listing all tasks by the userid of the user
public List<Task>showAll()
{
    return taskRepository.findAll();
}

    public List<Task> getTasksByUid(int uid) {
        return taskRepository.findByUserUid(uid);
    }




//Updating the tname of the existing task
    public Task updateTnameForTask(int tid,Task task) {
  Task existingTask= taskRepository.findById(tid).orElseThrow();
  existingTask.setTname(task.getTname());
  return  taskRepository.save(existingTask);

    }

//Changing the Status of Complete of the task

    public String updateComplete(int tid,Task task) {
        Task existingTask= taskRepository.findById(tid).orElseThrow();
        existingTask.setCompleted(task.isCompleted());
        taskRepository.save(existingTask);
        deleteBytid(task,tid);
        return "The Task is Completed So Deleted";

    }

    //Deleting the task when its completed

    public void deleteBytid(Task task,int tid)
    {
        Task existingTask=taskRepository.findById(tid).orElseThrow();
        if(existingTask.isCompleted())
        {
            taskRepository.deleteById(tid);
        }

    }

}
