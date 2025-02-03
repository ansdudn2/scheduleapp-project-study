package org.example.scheduling_app.repository;

import org.example.scheduling_app.dto.ScheduleResponseDto;
import org.example.scheduling_app.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    // JdbcTemplate을 생성자 주입 방식으로 받아옵니다.
    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 저장 메서드
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        // 일정 저장을 위한 SQL 쿼리 (createdAt과 updatedAt은 LocalDateTime 값)
        String query = "INSERT INTO schedule (task, author, password, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, schedule.getTask(), schedule.getAuthor(), schedule.getPassword(), schedule.getCreatedAt(), schedule.getUpdatedAt());

        // 최근 생성된 ID를 가져옵니다.
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        // 생성된 ID와 함께 응답을 위한 DTO를 반환합니다.
        return new ScheduleResponseDto(id, schedule.getTask(), schedule.getAuthor(), schedule.getCreatedAt().toString(), schedule.getUpdatedAt().toString());
    }
}
