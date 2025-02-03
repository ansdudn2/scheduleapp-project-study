package org.example.scheduling_app.Service;

import org.example.scheduling_app.dto.ScheduleRequestDto;
import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.example.scheduling_app.entity.Schedule;
import org.example.scheduling_app.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduleService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String formattedDate = LocalDateTime.now().format(formatter);
    private final ScheduleRepository scheduleRepositoys;

    public ScheduleService( ScheduleRepository scheduleRepositoys) {
        this.scheduleRepositoys = scheduleRepositoys;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto){
        //Schedule 엔티티 객체 생성
        Schedule schedule = new Schedule(dto.getTask(), dto.getAuthor(), dto.getPassword(), LocalDateTime.parse(formattedDate, formatter));
        //데이터베이스에 저장하고 응답을 반환
        return scheduleRepositoys.saveSchedule(schedule);
    }
}
