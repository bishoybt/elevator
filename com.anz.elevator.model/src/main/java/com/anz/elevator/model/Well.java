package com.anz.elevator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Well {
    @Builder
    public static class InvalidFloorException extends RuntimeException {
        private Well well;
        private int floor;

        @Override
        public String getMessage() {
            return "Invalid floor: " + floor +", floor should be between: "
                    + well.bottomFloor + " and " + well.topFloor + ".";
        }
    }

    @Getter
    private int topFloor;
    @Getter
    private int bottomFloor;

    public void validate(int floor) {
        if (floor > topFloor || floor < bottomFloor) {
            throw InvalidFloorException.builder().well(this).floor(floor).build();
        }
    }
}
