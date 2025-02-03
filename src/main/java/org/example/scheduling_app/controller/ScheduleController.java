package org.example.scheduling_app.controller;

import org.example.scheduling_app.Service.ScheduleService;
import org.example.scheduling_app.dto.ScheduleRequestDto;
import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //HTTP POST 요청을 처리하는 메서드
    @PostMapping("/schedules")
    //SchduleService의 createSchedule 메서드를 호출하여 일정을 생성
    public ResponseEntity<ScheduleResponseDto> createSchdule(@RequestBody ScheduleRequestDto dto){
        //SchduleResponseDto로 응답을 반환, 응답상태는 201로 설정
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }
}
