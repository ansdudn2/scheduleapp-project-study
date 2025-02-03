package org.example.scheduling_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private String task; //할일
    private String author; //작성자명
    private String password;//비밀번호
}
