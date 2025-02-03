package org.example.scheduling_app.Service;

import org.example.scheduling_app.dto.ScheduleRequestDto;
import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.example.scheduling_app.entity.Schedule;
import org.example.scheduling_app.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String formattedDate = LocalDateTime.now().format(formatter);
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
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

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        // 기존 일정 조회
        Schedule existingSchedule = scheduleRepository.findScheduleById(id);

        // 비밀번호 검증
        if (!existingSchedule.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 수정할 항목 갱신
        existingSchedule.update(dto.getTask(), dto.getPassword()); // 할일과 비밀번호 수정
        existingSchedule.setAuthor(dto.getAuthor()); // 작성자명 수정
        existingSchedule.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)); // 수정일을 현재 시간으로 갱신

        // 수정된 일정 저장 후, 응답용 DTO 반환
        return scheduleRepository.saveSchedule(existingSchedule);
    }
    // 일정 삭제
    public void deleteSchedule(Long id, String password) {
        // 기존 일정 조회
        Schedule existingSchedule = scheduleRepository.findScheduleById(id);

        // 비밀번호 검증
        if (!existingSchedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 일정 삭제
        scheduleRepository.deleteSchedule(id); // 삭제 메서드 호출
    }
}