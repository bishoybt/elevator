package com.anz.elevator.api;

import com.anz.elevator.controller.ElevatorController;
import com.anz.elevator.controller.FloorController;
import com.anz.elevator.model.Building;
import com.anz.elevator.model.Elevator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/tick")
    public Building tick(@RequestBody Building building) {
        Arrays.stream(building.getElevators()).forEach(Elevator::executeNext);
        return building;
    }

    @PostMapping("/call")
    public Building call(@RequestBody Building building, int floor) {
        FloorController.call(building, floor);
        return building;
    }

    @PostMapping("/press_button")
    public Building pressButton(@RequestBody Building building, int elevator, int floor) {
        ElevatorController.goTo(building.getElevators()[elevator], floor);
        return building;
    }
}
