package com.example.time.controller;

import com.example.time.dto.ShiftDTO;
import com.example.time.entity.Shift;
import com.example.time.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hrms/time/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    /**
     * POST /api/v1/hrms/time/shifts
     */
    @PostMapping
    public Shift createShift(@RequestBody ShiftDTO dto) {
        Shift shift = new Shift();
        shift.setShiftName(dto.getShiftName());
        shift.setStartTime(dto.getStartTime());
        shift.setEndTime(dto.getEndTime());
        return shiftService.createShift(shift);
    }

    /**
     * GET /api/v1/hrms/time/shifts
     */
    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }
}
