package com.anz.elevator.model;

import com.anz.elevator.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class BuildingSerializationTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialization() throws JsonProcessingException {
        Building building = Utils.create(
                7, Elevator.Direction.UP, false, new Integer[] { 15, 30 },
                20, Elevator.Direction.DOWN, false, new Integer[] { 10, 5, 20 },
                40, Elevator.Direction.UP, false, new Integer[] { 41, 50, 10 },
                0, 10);

        String json = objectMapper.writeValueAsString(building);

        Assert.assertEquals("{\"elevators\":[{\"floor\":7,\"direction\":\"UP\",\"doorIsOpen\":false," +
                "\"plan\":[15,30]},{\"floor\":20,\"direction\":\"DOWN\",\"doorIsOpen\":false,\"plan\":[10,5,20]}," +
                "{\"floor\":40,\"direction\":\"UP\",\"doorIsOpen\":false,\"plan\":[41,50,10]}]," +
                "\"well\":{\"topFloor\":10,\"bottomFloor\":0}}", json);

        Building result = objectMapper.readValue(json, Building.class);

        Assert.assertEquals(3, result.getElevators().length);
        Assert.assertEquals(7, result.getElevators()[0].getFloor());
        Assert.assertEquals(20, result.getElevators()[1].getFloor());
        Assert.assertEquals(40, result.getElevators()[2].getFloor());
        Assert.assertEquals(0, result.getWell().getBottomFloor());
        Assert.assertEquals(10, result.getWell().getTopFloor());
    }
}
