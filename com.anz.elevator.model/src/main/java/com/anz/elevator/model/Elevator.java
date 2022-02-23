package com.anz.elevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Collections;
import java.util.List;


/**
 * Represents a single Elevator
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Elevator {
    /**
     * Enum representing elevator possible directions
     */
    public enum Direction {
        UP(1),
        DOWN(-1);

        @Getter
        private final int value;

        Direction(final int newValue) {
            value = newValue;
        }
    }

    @Getter
    private int floor;
    @Getter @Setter
    private Direction direction;
    @Getter
    private boolean doorIsOpen;

    @Getter
    private List<Integer> plan;

    @JsonIgnore
    @Getter @Setter
    private Building building;

    /**
     * Calculates the highest or lowest floor (according to the direction param) currently on this elevator's plan
     * @param direction direction in which a max floor is to be calculated
     * @return Max floor currently on this elevator's plan, or empty if plan is empty
     */
    public int getMaxStopInDirection(Direction direction) {
        // Only callable when the plan is not empty
        if (plan.isEmpty()) {
            throw new IllegalStateException("This method is not supposed to be called with plan being empty");
        }

        return direction == Direction.UP ? Collections.max(plan) : Collections.min(plan);
    }

    /**
     * Run the next action from the plan queue and update elevator status

     * @return true if an action was executed, false in case the plan queue is empty
     */
    public boolean executeNext() {
        // If elevator door is open, then action will be to close the door
        if (doorIsOpen) {
            doorIsOpen = false;
            return true;
        }

        // If door is closed, and the plan is empty, return false
        if (plan.isEmpty()) {
            return false;
        }

        // If current floor is in the plan, open the door and remove from the plan
        if (plan.contains(floor)) {
            doorIsOpen = true;

            // If this floor is max in current direction, and still more floors in plan, switch direction
            if (floor == getMaxStopInDirection(direction)) {
                direction = direction == Direction.UP ? Direction.DOWN : Direction.UP;
            }

            plan.remove((Integer) floor);

            // If no more floors in plan, reset direction
            if (plan.isEmpty()) {
                direction = null;
            }

            return true;
        }

        // Increment or decrement floor according to current direction
        floor += direction.getValue();

        return true;
    }
}
