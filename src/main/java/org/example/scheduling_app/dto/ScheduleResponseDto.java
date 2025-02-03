package org.example.scheduling_app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private String createdAt;
    private String updatedAt;
}
