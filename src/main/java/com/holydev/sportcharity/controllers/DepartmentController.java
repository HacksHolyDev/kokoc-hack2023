package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.organizations.DepartmentDTO.DepartmentInfo;
import com.holydev.sportcharity.entities.organizations.Department;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.services.EntityBased.organizations.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get all department")
    @GetMapping
    public ResponseEntity<List<DepartmentInfo>> getAll() {
        var objects = departmentService.getAll()
                .stream().map(this::convert)
                .toList();
        return ResponseEntity.ok(objects);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get department")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentInfo> get(@PathVariable long id) {
        var object = departmentService.get(id);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "Create department")
    @PostMapping
    public ResponseEntity<DepartmentInfo> create(@RequestBody DepartmentInfo info) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var object = departmentService.create(authUser.getId(), info);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "update department")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody DepartmentInfo info) {
        departmentService.update(id, info);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "delete department")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        departmentService.delete(id);
    }


    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "add training to course")
    @PostMapping("/add/{id}")
    public void add(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        departmentService.addUser(authUser.getId(), id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "remove training from course")
    @PostMapping("/remove/{id}")
    public void remove(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        departmentService.removeUser(authUser.getId(), id);
    }

    private DepartmentInfo convert(Department department) {
        return DepartmentInfo.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}
