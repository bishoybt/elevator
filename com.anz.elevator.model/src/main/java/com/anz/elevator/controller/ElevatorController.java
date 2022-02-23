package com.anz.elevator.controller;

import com.anz.elevator.model.Elevator;

/**
 * Represents keyboard controller in an elevator
 */
public class ElevatorController {
    /**
     * Find the optimal way to add a target floor to an elevator plan
     * Simulates a person pressing floor button from inside an elevator
     * @param elevator The elevator from which a floor button is pressed
     * @param floor The floor button being pressed
     */
    public static void goTo(Elevator elevator, int floor) {
        // Validate target floor
        if (elevator.getBuilding() != null) {
            elevator.getBuilding().getWell().validate(floor);
        }

        // If floor already in plan, do nothing
        if (elevator.getPlan().contains(floor)) {
            return;
        }

        // Add floor to plan
        elevator.getPlan().add(floor);

        // If this is the first floor to add to the plan, decide the direction
        if (elevator.getPlan().size() == 1) {
            elevator.setDirection(floor > elevator.getFloor()
                    ? Elevator.Direction.UP : Elevator.Direction.DOWN);
        }
    }
}
