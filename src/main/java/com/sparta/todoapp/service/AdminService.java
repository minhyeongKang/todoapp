package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ToDoResponseDto;
import com.sparta.todoapp.entity.ToDo;
import com.sparta.todoapp.repasitory.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ToDoRepository toDoRepository;

    public List<ToDoResponseDto> getTotalCards() {
        // DB 조회
        return toDoRepository.findAll().stream().map(ToDoResponseDto::new).toList();
    }

    public Long adminDeleteCard(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        ToDo toDo = findToDo(id);

        // memo 삭제
        toDoRepository.delete(toDo);

        return id;
    }

    private ToDo findToDo(Long id) {
        return toDoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 할일 카드는 존재하지 않습니다.")
        );
    }
}
