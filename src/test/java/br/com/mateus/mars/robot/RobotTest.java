package br.com.mateus.mars.robot;

import br.com.mateus.mars.robot.controller.RobotController;
import br.com.mateus.mars.robot.model.Robot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

@SpringBootTest
public class RobotTest {

  @Autowired
  private RobotController robotController;

  @ParameterizedTest
  @MethodSource("validCommandProvider")
  void testValidCommand(
    String command,
    String result
  ) {
    Robot robot = new Robot();
    robot.processCommands(command);
    Assertions.assertThat(robot.toString()).isEqualTo(result);
  }

  @Test
  public void testMoveRight() {
    ResponseEntity<String> response = robotController.moveRobot("MMR");
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody()).isEqualTo("(0, 2, E)");
  }

  @Test
  public void testMoveLeft() {
    ResponseEntity<String> response = robotController.moveRobot("MML");
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody()).isEqualTo("(0, 2, W)");
  }

  @Test
  public void testOutOfBounds() {
    ResponseEntity<String> response = robotController.moveRobot("MMMMMMMMMMMMMMMMMMMMMMMMMM");
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    Assertions.assertThat(response.getBody()).isEqualTo("Move out of bounds");
  }

  @Test
  public void testInvalidCommand() {
    ResponseEntity<String> response = robotController.moveRobot("AAA");
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    Assertions.assertThat(response.getBody()).isEqualTo("Invalid command: A");
  }

  @Test
  public void testMixedCaseCommands() {
    ResponseEntity<String> response = robotController.moveRobot("MmRmmLm");
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody()).isEqualTo("(2, 3, N)");
  }

  static Stream<Arguments> validCommandProvider() {
    return Stream.of(
      Arguments.of("MMRMMRMM", "(2, 0, S)"),
      Arguments.of("MML", "(0, 2, W)"),
      Arguments.of("MMRMMLM","(2, 3, N)"),
      Arguments.of("MML","(0, 2, W)")

    );
  }

}
