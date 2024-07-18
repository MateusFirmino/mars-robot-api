package br.com.mateus.mars.robot;

import br.com.mateus.mars.robot.model.Robot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class RobotTest {

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
//@ParameterizedTest
//@NullSource
//@EmptySource
//@ValueSource(strings = "AAAAAAAAAAA")
//  void test2(String command) {
//    Robot robot = new Robot();
//    Assertions.assertThatThrownBy(() -> robot.processCommands(command))
//      .isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid command: A");
//  }

  static Stream<Arguments> validCommandProvider(){
    return Stream.of(
      Arguments.of("MMRMMRMM","(2, 0, S)"),
      Arguments.of("MML","(0, 2, W)")
    );
  }

}
