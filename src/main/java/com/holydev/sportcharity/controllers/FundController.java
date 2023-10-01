package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.organizations.FundDTO.FundInfo;
import com.holydev.sportcharity.entities.organizations.Fund;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.services.EntityBased.organizations.FundService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fund")
@RequiredArgsConstructor
public class FundController {
    private final FundService fundService;

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get all fund")
    @GetMapping
    public ResponseEntity<List<FundInfo>> getAll() {
        var objects = fundService.getAll()
                .stream().map(this::convert)
                .toList();
        return ResponseEntity.ok(objects);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get fund")
    @GetMapping("/{id}")
    public ResponseEntity<FundInfo> get(@PathVariable long id) {
        var object = fundService.get(id);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "Create fund")
    @PostMapping
    public ResponseEntity<FundInfo> create(@RequestBody FundInfo info) {
        var object = fundService.create(info);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "update fund")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody FundInfo info) {
        fundService.update(id, info);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "delete fund")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        fundService.delete(id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "add user to fund")
    @PostMapping("/add/{id}")
    public void add(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        fundService.addUser(authUser.getId(), id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "remove user from fund")
    @PostMapping("/remove/{id}")
    public void remove(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        fundService.removeUser(authUser.getId(), id);
    }

    private FundInfo convert(Fund fund) {
        return FundInfo.builder()
                .id(fund.getId())
                .name(fund.getName())
                .description(fund.getDescription())
                .budget(fund.getBudget())
                .build();
    }
}
