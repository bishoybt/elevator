package com.anz.elevator.controller;

import com.anz.elevator.model.Building;
import com.anz.elevator.model.Elevator;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class FloorController {
    /**
     * Decide which elevator is best to call and add add floor to elevator plan
     * @param building Reference to the building where the floor controller is called
     * @param floor The floor from which the calling button is pressed
     */
    public static void call(Building building, int floor) {
        // Validate target floor
        building.getWell().validate(floor);

        // Find the nearest elevator to calling floor
        Elevator elevator =
            Arrays.stream(building.getElevators())
                .map(e -> new SimpleEntry<Integer, Elevator>(calculateDistance(e, floor), e))
                .min(Comparator.comparingInt(SimpleEntry::getKey))
                .orElseThrow(IllegalStateException::new).getValue();

        ElevatorController.goTo(elevator, floor);
    }

    /**
     * Calculates how many floors an elevator needs to pass by to reach a certain floor
     * @param elevator Elevator to be examined
     * @param floor Target floor to calculate distance to
     * @return number of floors the elevator will go through to reach the target floor
     */
    private static int calculateDistance(Elevator elevator, int floor) {
        int targetDirection = Integer.signum(floor - elevator.getFloor());

        // If elevator is not moving or  moving in the same direction,
        // then steps are the difference between target floor and current
        if (elevator.getPlan().isEmpty() || elevator.getDirection().getValue() == targetDirection) {
            return Math.abs(floor - elevator.getFloor());
        } else { // Otherwise, cater for the elevator going all the way to max in this direction then back
            int max = elevator.getMaxStopInDirection(elevator.getDirection());

            return Math.abs(2 * max - elevator.getFloor() - floor);
        }
    }
}
