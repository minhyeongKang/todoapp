package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.ToDoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todocard")
public class ToDo extends User {

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    public ToDo(ToDoRequestDto toDoRequestDto) {
        super();
        this.contents = toDoRequestDto.getContents();
    }

    public void update(ToDoRequestDto toDoRequestDto) {
        this.contents = toDoRequestDto.getContents();
    }


}
