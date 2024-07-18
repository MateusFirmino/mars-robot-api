package br.com.mateus.mars.robot.model;

public class Robot {

  private int x;
  private int y;
  private Direction direction;
  private static final int MAX_X = 4;
  private static final int MAX_Y = 4;

  public Robot() {
    this.x = 0;
    this.y = 0;
    this.direction = Direction.N;
  }

  public void processCommands(String commands) {
    for (char command : commands.toCharArray()) {
      switch (command) {
        case 'L' -> this.turnLeft();
        case 'R' -> this.turnRight();
        case 'M' -> this.move();
        default -> throw new IllegalArgumentException("Invalid command: " + command);
      }
    }
  }

  private void turnLeft() {
    this.direction = this.direction.turnLeft();
  }

  private void turnRight() {
    this.direction = this.direction.turnRight();
  }

  private void move() {
    switch (this.direction) {
      case N -> this.y++;
      case E -> this.x++;
      case S -> this.y--;
      case W -> this.x--;
      default -> throw new IllegalArgumentException("Invalid direction");
    }
    if (this.y < 0 || this.y > MAX_Y || this.x < 0 || this.x > MAX_X) {
      throw new IllegalArgumentException("Move out of bounds");
    }
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %s)", this.x, this.y, this.direction);
  }

}
