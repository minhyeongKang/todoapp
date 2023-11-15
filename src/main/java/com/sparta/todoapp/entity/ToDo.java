package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.ToDoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todocard")
public class ToDo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ToDo(ToDoRequestDto toDoRequestDto, User user) {
        this.username = toDoRequestDto.getUsername();
        this.password = toDoRequestDto.getPassword();
        this.title = toDoRequestDto.getTitle();
        this.contents = toDoRequestDto.getContents();
    }

    public void update(ToDoRequestDto toDoRequestDto) {
        this.contents = toDoRequestDto.getContents();
    }


}
