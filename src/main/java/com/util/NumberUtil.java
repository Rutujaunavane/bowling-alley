package com.util;

public class NumberUtil {

  public static int getRandomInteger(int maximum, int minimum) {
    return (int) Math.round(Math.random() * (maximum - minimum)) + (int) minimum;
  }

}
