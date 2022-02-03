package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

    @Autowired
    AllocationService allocationService;

    @Test
    public void create() throws ParseException {
        Allocation allocation = new Allocation();
        allocation.setId(10L);
        allocation.setDayOfWeek(DayOfWeek.MONDAY);
        allocation.setStartHour(sdf.parse("19:00-0300"));
        allocation.setEndHour(sdf.parse("20:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);

        Allocation allocationNew = allocationService.create(allocation);

        System.out.println(allocationNew);
    }

    @Test
    public void update() throws ParseException {
        Allocation allocation = new Allocation();
        allocation.setId(2L);
        allocation.setDayOfWeek(DayOfWeek.WEDNESDAY);
        allocation.setStartHour(sdf.parse("19:00-0300"));
        allocation.setEndHour(sdf.parse("22:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);

        Allocation allocationNew = allocationService.update(allocation);

        System.out.println(allocationNew);
    }

}
