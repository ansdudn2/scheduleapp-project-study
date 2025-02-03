package org.example.scheduling_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private Long id;
    private String task;
    private String author;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //생성자
    public Schedule(String task, String author, String password, LocalDateTime createdAt) {
        this.task = task;
        this.author = author;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;//최초작성시,수정일은 작성일과 동일하다
    }
    //일정 수정 메서드
    public void update(String task,String password){
        this.task=task;
        this.password=password;
        this.updatedAt=LocalDateTime.now();//수정시 현재 시간으로 업데이트
    }
}