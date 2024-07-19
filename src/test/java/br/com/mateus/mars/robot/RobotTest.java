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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

@SpringBootTest
public class RobotTest {

  @Autowired
  private RobotController robotController;

  @ParameterizedTest
  @MethodSource("validCommandProvider")
  void test1(
    String command,
    String result
  ) {
    Robot robot = new Robot();
    robot.processCommands(command);
    Assertions.assertThat(robot.toString()).isEqualTo(result);
  }

  @Test
  public void testMoveRight() {
    ResponseEntity<String> response = robotController.moveRobot("MMRMMRMM");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("(2, 0, S)", response.getBody());
  }

  @Test
  public void testMoveLeft() {
    ResponseEntity<String> response = robotController.moveRobot("MML");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("(0, 2, W)", response.getBody());
  }

  static Stream<Arguments> validCommandProvider() {
    return Stream.of(
      Arguments.of("MMRMMRMM", "(2, 0, S)"),
      Arguments.of("MML", "(0, 2, W)")
    );
  }

}
