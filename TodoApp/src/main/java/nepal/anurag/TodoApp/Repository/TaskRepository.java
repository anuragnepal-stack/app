package nepal.anurag.TodoApp.Repository;

import nepal.anurag.TodoApp.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByUserUid(int uid);

}
