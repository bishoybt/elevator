package com.anz.elevator.controller;

import com.anz.elevator.Utils;
import com.anz.elevator.model.Building;
import com.anz.elevator.model.Elevator;
import org.junit.Assert;
import org.junit.Test;

public class FloorControllerTest {
    @Test
    public void testCall_whenSingleElevator() {
        Elevator elevator = Utils.create(0, null, false, new Integer[0],
                0, 10);

        FloorController.call(elevator.getBuilding(), 5);

        Assert.assertEquals(Elevator.Direction.UP, elevator.getDirection());
        Assert.assertArrayEquals(new Integer[] { 5 }, elevator.getPlan().toArray());
    }

    @Test
    public void testCall_findNearestAmongThreeElevators() {
        Building building = Utils.create(
                7, Elevator.Direction.UP, false, new Integer[] { 15, 30 },
                20, Elevator.Direction.DOWN, false, new Integer[] { 10, 5, 20 },
                40, Elevator.Direction.UP, false, new Integer[] { 41, 50, 10 },
                0, 10);

        FloorController.call(building, 4);

        Assert.assertEquals(Elevator.Direction.UP, building.getElevators()[0].getDirection());
        Assert.assertArrayEquals(new Integer[] { 15, 30 }, building.getElevators()[0].getPlan().toArray());
        Assert.assertEquals(Elevator.Direction.DOWN, building.getElevators()[1].getDirection());
        Assert.assertArrayEquals(new Integer[] { 10, 5, 20, 4 }, building.getElevators()[1].getPlan().toArray());
        Assert.assertEquals(Elevator.Direction.UP, building.getElevators()[2].getDirection());
        Assert.assertArrayEquals(new Integer[] { 41, 50, 10 }, building.getElevators()[2].getPlan().toArray());
    }
}