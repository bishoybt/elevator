package com.anz.elevator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    @Getter
    private Elevator[] elevators;
    @Getter
    private Well well;
}
