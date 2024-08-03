package nepal.anurag.TodoApp.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;
    @Column
    private String tname;
    @Column
    private LocalDate start;
    @Column
    private LocalDate end;
    @Column
    private boolean completed;
@ManyToOne
@JoinColumn(name = "uid")
@JsonIgnore

    private User user;



    public Task(String tname, LocalDate end, Integer uid, User user) {
        this.tname = tname;
        this.end = end;
        this.start = LocalDate.now(); // set start date to current date
        this.completed = false; // set completed to false
        this.user = user;
    }

}
