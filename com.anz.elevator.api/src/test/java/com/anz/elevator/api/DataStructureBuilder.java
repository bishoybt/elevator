package com.anz.elevator.api;

import com.anz.elevator.model.Building;
import com.anz.elevator.model.Elevator;
import com.anz.elevator.model.Well;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;

import java.util.List;

public class DataStructureBuilder {
    private static Building building;

    public static Building getBuilding() { return building; }

    @ParameterType("open|closed")
    public boolean doorStatus(String doorStatus) {
        return doorStatus.equalsIgnoreCase("open");
    }

    @ParameterType("down|up")
    public Elevator.Direction direction(String direction) {
        return direction.equalsIgnoreCase("down") ? Elevator.Direction.DOWN : Elevator.Direction.UP;
    }

    @Given("Building has {int} elevators with top floor at {int} and bottom floor at {int}")
    public void constructBuilding(Integer elevators, Integer topFloor, Integer bottomFloor) {
        building = Building.builder()
                .elevators(new Elevator[elevators])
                .well(Well.builder()
                        .topFloor(topFloor)
                        .bottomFloor(bottomFloor).build())
                .build();
    }

    @Given("Elevator {int} is at level {int} moving {direction} with its door {doorStatus} and plans to stop at floors:")
    public void setupElevator(Integer elevatorId, Integer floor, Elevator.Direction direction,
                              boolean doorIsOpen, @Transpose List<Integer> plan) {
        building.getElevators()[elevatorId - 1] = Elevator.builder()
                .floor(floor)
                .doorIsOpen(doorIsOpen)
                .plan(plan)
                .direction(direction)
                .build();
    }
}
