package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ToDoRequestDto;
import com.sparta.todoapp.dto.ToDoResponseDto;
import com.sparta.todoapp.entity.ToDo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repasitory.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoResponseDto createToDoCard(ToDoRequestDto toDoRequestDto, User user) {
        ToDo toDo = toDoRepository.save(new ToDo(toDoRequestDto, user));
        return new ToDoResponseDto(toDo);
    }

    public List<ToDoResponseDto> getToDoCards(User user) {
        List<ToDo> toDoList = toDoRepository.findAllByUser(user);
        List<ToDoResponseDto> responseDtoList = new ArrayList<>();

        for (ToDo toDo : toDoList) {
            responseDtoList.add(new ToDoResponseDto(toDo));
        }

        return responseDtoList;
    }

    @Transactional
    public Long updateToDoCard(Long id, ToDoRequestDto toDoRequestDto, User user) {
        // 할일 카드 찾기
        ToDo toDo = toDoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 할일카드가 없습니다.")
        );
        // 할일 카드의 User와 비교
        if (user.getUsername().equals(toDo.getUser().getUsername())) {
            toDo.update(toDoRequestDto);
            return toDo.getId();
        } else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
    }

    public Long deleteToDoCard(Long id) {
        // 해당 할일카드가 DB에 존재하는지 확인
        ToDo toDo = findToDoCard(id);
        // 할일카드 삭제
        toDoRepository.delete(toDo);

        return id;
    }

    private ToDo findToDoCard(Long id) {
        return toDoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 할일카드는 존재하지 않습니다.")
        );
    }
}
