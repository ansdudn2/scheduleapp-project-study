package org.example.scheduling_app.Service;

import org.example.scheduling_app.dto.ScheduleRequestDto;
import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.example.scheduling_app.entity.Schedule;
import org.example.scheduling_app.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String formattedDate = LocalDateTime.now().format(formatter);
    private final ScheduleRepository scheduleRepository;

    public ScheduleService( ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto){
        //Schedule 엔티티 객체 생성
        Schedule schedule = new Schedule(dto.getTask(), dto.getAuthor(), dto.getPassword(), LocalDateTime.parse(formattedDate, formatter));
        //데이터베이스에 저장하고 응답을 반환
        return scheduleRepository.saveSchedule(schedule);
    }


    // 모든 일정 조회 메소드
    public List<ScheduleResponseDto> getAllSchedules() {
        // DB에서 모든 일정 목록을 조회하고 DTO로 변환하여 반환
        return scheduleRepository.findAllSchedules()
                .stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        // 선택한 일정 조회
        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }
}
