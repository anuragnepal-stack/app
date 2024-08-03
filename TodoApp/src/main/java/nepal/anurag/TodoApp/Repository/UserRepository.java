package nepal.anurag.TodoApp.Repository;

import nepal.anurag.TodoApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
