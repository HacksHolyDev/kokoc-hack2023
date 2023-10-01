package com.holydev.sportcharity.DTO.organizations.FundDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FundInfo {
    private Long id;

    private String name;

    private String description;

    private Long budget;
}
