package br.com.mateus.mars.robot.controller;

import br.com.mateus.mars.robot.model.Robot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars")
public class RobotController {

  @PostMapping("/{commands}")
  public ResponseEntity<String> moveRobot(@PathVariable String commands) {
    try {
      Robot robot = new Robot();
      robot.processCommands(commands);
      return ResponseEntity.ok(robot.toString());
    }
    catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
