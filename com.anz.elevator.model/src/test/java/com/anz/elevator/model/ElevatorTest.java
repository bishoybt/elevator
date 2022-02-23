package com.anz.elevator.model;

import com.anz.elevator.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorTest {
    @Test
    public void testGetMaxStopInDirection_GoingUp() {
        Elevator elevator = Elevator.builder().plan(new ArrayList<>(Arrays.asList(
                2, 5, 7, 8, 3, 12, 16, 10))).build();

        Assert.assertEquals(16, elevator.getMaxStopInDirection(Elevator.Direction.UP));
    }

    @Test
    public void testGetMaxStopInDirection_GoingDown() {
        Elevator elevator = Elevator.builder().plan(new ArrayList<>(Arrays.asList(
                13, 5, 7, 8, 3, 12, 16, 10))).build();

        Assert.assertEquals(3, elevator.getMaxStopInDirection(Elevator.Direction.DOWN));
    }

    @Test
    public void testGoTo_emptyPlan() {
        Elevator elevator = Utils.create(0, null, false, new Integer[0],
                0, 10);

        boolean result = elevator.executeNext();

        Assert.assertFalse(result);
        Assert.assertEquals(0, elevator.getFloor());
        Assert.assertNull(elevator.getDirection());
        Assert.assertFalse(elevator.isDoorIsOpen());
        Assert.assertEquals(0, elevator.getPlan().size());
    }

    @Test
    public void testExecuteNext_doorIsOpen() {
        Elevator elevator = Utils.create(7, Elevator.Direction.UP, true,
                new Integer[] { 10, 8 }, -10, 100);

        boolean result = elevator.executeNext();

        Assert.assertTrue(result);
        Assert.assertEquals(7, elevator.getFloor());
        Assert.assertEquals(Elevator.Direction.UP, elevator.getDirection());
        Assert.assertFalse(elevator.isDoorIsOpen());
        Assert.assertArrayEquals(new Integer[] { 10, 8 }, elevator.getPlan().toArray());
    }

    @Test
    public void testExecuteNext_currentFloorIsNotAStop() {
        Elevator elevator = Utils.create(7, Elevator.Direction.UP, false,
                new Integer[] { 10, 8 }, -10, 100);

        boolean result = elevator.executeNext();

        Assert.assertTrue(result);
        Assert.assertEquals(8, elevator.getFloor());
        Assert.assertEquals(Elevator.Direction.UP, elevator.getDirection());
        Assert.assertFalse(elevator.isDoorIsOpen());
        Assert.assertArrayEquals(new Integer[] { 10, 8 }, elevator.getPlan().toArray());
    }

    @Test
    public void testExecuteNext_currentFloorIsAStop() {
        Elevator elevator = Utils.create(8, Elevator.Direction.UP, false,
                new Integer[] { 10, 8 }, -10, 100);

        boolean result = elevator.executeNext();

        Assert.assertTrue(result);
        Assert.assertEquals(8, elevator.getFloor());
        Assert.assertEquals(Elevator.Direction.UP, elevator.getDirection());
        Assert.assertTrue(elevator.isDoorIsOpen());
        Assert.assertArrayEquals(new Integer[] { 10 }, elevator.getPlan().toArray());
    }

    @Test
    public void testExecuteNext_currentFloorIsTheFinalStop() {
        Elevator elevator = Utils.create(8, Elevator.Direction.UP, false,
                new Integer[] { 8 }, -10, 100);

        boolean result = elevator.executeNext();

        Assert.assertTrue(result);
        Assert.assertEquals(8, elevator.getFloor());
        Assert.assertNull(elevator.getDirection());
        Assert.assertTrue(elevator.isDoorIsOpen());
        Assert.assertArrayEquals(new Integer[0], elevator.getPlan().toArray());
    }

    @Test
    public void testExecuteNext_directionIsChangingFromUpToDown() {
        Elevator elevator = Utils.create(10, Elevator.Direction.UP, false,
                new Integer[] { 10, 8 }, -10, 100);

        boolean result = elevator.executeNext();

        Assert.assertTrue(result);
        Assert.assertEquals(10, elevator.getFloor());
        Assert.assertEquals(Elevator.Direction.DOWN, elevator.getDirection());
        Assert.assertTrue(elevator.isDoorIsOpen());
        Assert.assertArrayEquals(new Integer[] { 8 }, elevator.getPlan().toArray());
    }
}