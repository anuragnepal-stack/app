package nepal.anurag.TodoApp.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private String tname;
    private LocalDate end;
    private Integer uid;
}