package com.kearny.puissance4;

enum PlayerEnum {

  PLAYER1(1), PLAYER2(2);

  private final int value;

  PlayerEnum(final int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
