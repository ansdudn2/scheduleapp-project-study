package org.example.scheduling_app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduling_app.entity.Schedule;

import java.time.format.DateTimeFormatter;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private String createdAt;
    private String updatedAt;

    // DateTimeFormatter 사용하여 포맷 지정
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt().format(formatter); // createdAt 포맷
        this.updatedAt = schedule.getUpdatedAt().format(formatter); // updatedAt 포맷
    }
}