package com.example.time.controller;


import com.example.time.dto.HolidayDTO;
import com.example.time.entity.Holiday;
import com.example.time.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hrms/time/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping
    public Holiday createHoliday(@RequestBody HolidayDTO dto) {
        Holiday holiday = new Holiday();
        holiday.setHolidayDate(dto.getHolidayDate());
        holiday.setOccasion(dto.getOccasion());
        holiday.setLocation(dto.getLocation());
        holiday.setOptional(dto.isOptional());
        return holidayService.createHoliday(holiday);
    }

    @GetMapping("/location/{location}")
    public List<Holiday> getByLocation(@PathVariable String location) {
        return holidayService.getHolidaysByLocation(location);
    }

}
