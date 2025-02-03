package org.example.scheduling_app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduling_app.entity.Schedule;




@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private String createdAt;
    private String updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getUpdatedAt().toString();
        this.updatedAt = schedule.getUpdatedAt().toString();
    }
}