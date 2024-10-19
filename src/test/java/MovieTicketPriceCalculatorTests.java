import taskone.MovieTicketPriceCalculator;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MovieTicketPriceCalculatorTests {
  private final MovieTicketPriceCalculator calculator =
      new MovieTicketPriceCalculator(LocalTime.of(14, 0, 0), LocalTime.of(17, 0, 0), 12, 65);

  @org.junit.jupiter.api.Test
  void computeSeniorDiscount() {
    assertEquals(400, calculator.computeDiscount(65));
  }

  @org.junit.jupiter.api.Test
  void computeChildDiscount() {
    assertEquals(300, calculator.computeDiscount(12));
  }

  @org.junit.jupiter.api.Test
  void computeStandardDiscount() {
    assertEquals(0, calculator.computeDiscount(43));
  }

  @org.junit.jupiter.api.Test
  void computeMatineePrice() {
    assertEquals(2400, calculator.computePrice(LocalTime.of(14, 0, 0), 13));
  }

  @org.junit.jupiter.api.Test
  void computeBeforeMatineePrice() {
    assertEquals(2700, calculator.computePrice(LocalTime.of(5, 0, 0), 13));
  }

  @org.junit.jupiter.api.Test
  void computeBadTimesPrice() {
    try {
      final MovieTicketPriceCalculator badcalculator =
          new MovieTicketPriceCalculator(LocalTime.of(14, 0, 0), LocalTime.of(12, 0, 0), 12, 65);
      fail("matinee start time cannot come after end time");
    } catch (IllegalArgumentException e) {
      final String expected = "matinee start time cannot come after end time";
      assertEquals(expected, e.getMessage());
    }
  }

  @org.junit.jupiter.api.Test
  void computeMatineeSeniorPrice() {
    assertEquals(2000, calculator.computePrice(LocalTime.of(16, 59, 59), 115));
  }

  @org.junit.jupiter.api.Test
  void computeMatineeChildPrice() {
    assertEquals(2100, calculator.computePrice(LocalTime.of(16, 13, 23), 1));
  }

  @org.junit.jupiter.api.Test
  void computeEndMatineePrice() {
    assertEquals(2700, calculator.computePrice(LocalTime.of(17, 0, 0), 45));
  }

  @org.junit.jupiter.api.Test
  void computeStandardPrice() {
    assertEquals(2700, calculator.computePrice(LocalTime.of(19, 2, 3), 25));
  }
}
