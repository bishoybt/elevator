package com.anz.elevator;

import com.anz.elevator.model.Building;
import com.anz.elevator.model.Elevator;
import com.anz.elevator.model.Well;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    public static Elevator create(int floor, Elevator.Direction direction, boolean doorIsOpen,
                            Integer[] plan, int bottomFloor, int topFloor) {
        Elevator elevator = Elevator.builder()
                .floor(floor).direction(direction).doorIsOpen(doorIsOpen)
                .plan(new ArrayList<>((Arrays.asList(plan))))
                .build();

        elevator.setBuilding(
                Building.builder()
                        .well(Well.builder().topFloor(topFloor).bottomFloor(bottomFloor).build())
                        .elevators(new Elevator[] { elevator })
                        .build());

        return elevator;
    }

    public static Building create(int floor1, Elevator.Direction direction1, boolean doorIsOpen1, Integer[] plan1,
                                  int floor2, Elevator.Direction direction2, boolean doorIsOpen2, Integer[] plan2,
                                  int bottomFloor, int topFloor) {
        Elevator elevator1 = Elevator.builder()
                .floor(floor1).direction(direction1).doorIsOpen(doorIsOpen1)
                .plan(new ArrayList<>((Arrays.asList(plan1))))
                .build();

        Elevator elevator2 = Elevator.builder()
                .floor(floor2).direction(direction2).doorIsOpen(doorIsOpen2)
                .plan(new ArrayList<>((Arrays.asList(plan2))))
                .build();

        Building building = Building.builder()
                .well(Well.builder().topFloor(topFloor).bottomFloor(bottomFloor).build())
                .elevators(new Elevator[] { elevator1, elevator2 })
                .build();

        elevator1.setBuilding(building);
        elevator2.setBuilding(building);

        return building;
    }

    public static Building create(int floor1, Elevator.Direction direction1, boolean doorIsOpen1, Integer[] plan1,
                                  int floor2, Elevator.Direction direction2, boolean doorIsOpen2, Integer[] plan2,
                                  int floor3, Elevator.Direction direction3, boolean doorIsOpen3, Integer[] plan3,
                                  int bottomFloor, int topFloor) {
        Elevator elevator1 = Elevator.builder()
                .floor(floor1).direction(direction1).doorIsOpen(doorIsOpen1)
                .plan(new ArrayList<>((Arrays.asList(plan1))))
                .build();

        Elevator elevator2 = Elevator.builder()
                .floor(floor2).direction(direction2).doorIsOpen(doorIsOpen2)
                .plan(new ArrayList<>((Arrays.asList(plan2))))
                .build();

        Elevator elevator3 = Elevator.builder()
                .floor(floor3).direction(direction3).doorIsOpen(doorIsOpen3)
                .plan(new ArrayList<>((Arrays.asList(plan3))))
                .build();

        Building building = Building.builder()
                .well(Well.builder().topFloor(topFloor).bottomFloor(bottomFloor).build())
                .elevators(new Elevator[] { elevator1, elevator2, elevator3 })
                .build();

        elevator1.setBuilding(building);
        elevator2.setBuilding(building);
        elevator3.setBuilding(building);

        return building;
    }
}
