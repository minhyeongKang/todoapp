package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ToDoRequestDto;
import com.sparta.todoapp.dto.ToDoResponseDto;
import com.sparta.todoapp.entity.ToDo;
import com.sparta.todoapp.repasitory.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoResponseDto createToDo(ToDoRequestDto toDoRequestDto) {
        // RequestDto -> Entity
        ToDo toDo = new ToDo(toDoRequestDto);
        // DB 저장
        ToDo saveToDo = toDoRepository.save(toDo);
        // Entity -> ResponseDto
        ToDoResponseDto toDoResponseDto = new ToDoResponseDto(toDo);

        return toDoResponseDto;
    }

    public List<ToDoResponseDto> getToDoCards() {
        // DB 조회
        return toDoRepository.findAllByOrderByModifiedAtDesc().stream().map(ToDoResponseDto::new).toList();
    }

    @Transactional
    public Long updateToDocard(Long id, ToDoRequestDto toDoRequestDto) {
        // 해당 할일카드가 DB에 존재하는지 확인
        ToDo toDo = findToDoCard(id);
        // 할일카드 내용 수정
        toDo.update(toDoRequestDto);

        return id;
    }

    public Long deleteToDocard(Long id, ToDoRequestDto toDoRequestDto) {
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
