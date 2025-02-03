package org.example.scheduling_app.controller;

import org.example.scheduling_app.Service.ScheduleService;
import org.example.scheduling_app.dto.ScheduleRequestDto;
import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //HTTP POST 요청을 처리하는 메서드
    @PostMapping
    //SchduleService의 createSchedule 메서드를 호출하여 일정을 생성
    public ResponseEntity<ScheduleResponseDto> createSchdule(@RequestBody ScheduleRequestDto dto){
        // 클라이언트로부터 받은 DTO를 바탕으로 일정 생성
        ScheduleResponseDto response = scheduleService.createSchedule(dto);
        //SchduleResponseDto로 응답을 반환, 응답상태는 201로 설정
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }
    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        // 모든 일정을 조회하여 리스트로 반환
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();

        // 성공적인 조회 후, HTTP 200 상태 코드와 함께 전체 일정 반환
        return ResponseEntity.ok(schedules);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        // 선택한 일정 조회
        ScheduleResponseDto scheduleResponse = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(scheduleResponse);
    }
}
