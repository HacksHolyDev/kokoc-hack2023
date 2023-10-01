package com.holydev.sportcharity.DTO.organizations.DepartmentDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DepartmentInfo {
    private Long id;

    private String name;

    private String description;
}
