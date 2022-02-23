package com.anz.elevator.controller;

import com.anz.elevator.Utils;
import com.anz.elevator.model.Elevator;
import com.anz.elevator.model.Well;
import org.junit.Assert;
import org.junit.Test;

public class ElevatorControllerTest {
    @Test(expected = Well.InvalidFloorException.class)
    public void testGoTo_floorAboveTopFloor() {
        Elevator elevator = Utils.create(0, Elevator.Direction.UP, true,
                new Integer[] { 10, 8 }, -10, 100);

        ElevatorController.goTo(elevator, 101);
    }

    @Test(expected = Well.InvalidFloorException.class)
    public void testGoTo_floorBelowBottomFloor() {
        Elevator elevator = Utils.create(0, Elevator.Direction.UP, true,
                new Integer[] { 10, 8 }, -10, 100);

        ElevatorController.goTo(elevator, -11);
    }

    @Test
    public void testGoTo_firstFloorToAdd() {
        Elevator elevator = Utils.create(0, null, false, new Integer[0],
                0, 10);

        ElevatorController.goTo(elevator, 5);

        Assert.assertEquals(Elevator.Direction.UP, elevator.getDirection());
        Assert.assertArrayEquals(new Integer[] { 5 }, elevator.getPlan().toArray());
    }

    @Test
    public void testGoTo_ElevatorAlreadyMoving() {
        Elevator elevator = Utils.create(7, Elevator.Direction.UP, true,
                new Integer[] { 10, 8 }, -10, 100);

    }

    @Test
    public void testGoTo_floorAlreadyInPlan() {
        Elevator elevator = Utils.create(7, Elevator.Direction.UP, true,
                new Integer[] { 10, 8 }, -10, 100);

    }
}