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
    commands = commands.toUpperCase();
    for (char command : commands.toCharArray()) {
      switch (command) {
        case 'L':
          this.turnLeft();
          break;
        case 'R':
          this.turnRight();
          break;
        case 'M':
          this.move();
          break;
        default:
          throw new IllegalArgumentException("Invalid command: " + command);
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
      case N:
        if (this.y < MAX_Y) {
          this.y++;
        } else {
          throw new IllegalArgumentException("Move out of bounds");
        }
        break;
      case E:
        if (this.x < MAX_X) {
          this.x++;
        } else {
          throw new IllegalArgumentException("Move out of bounds");
        }
        break;
      case S:
        if (this.y > 0) {
          this.y--;
        } else {
          throw new IllegalArgumentException("Move out of bounds");
        }
        break;
      case W:
        if (this.x > 0) {
          this.x--;
        } else {
          throw new IllegalArgumentException("Move out of bounds");
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %s)", this.x, this.y, this.direction);
  }

}
