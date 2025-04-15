package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
    //Teilaufgabe 1: Dieser Test prüft eine bisher nicht getestete Funktion (Subtraktion),
    @Test
    @DisplayName("should display result after minus two positive multi-digit numbers")
    void testPositiveMinus() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "10";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    //Teilaufgabe 2: Zwei Roter Testen hinzufügen
    //Du gibst "x" als ungültige unäre Operation an → wirft IllegalArgumentException, aber es wird nicht richtig abgefangen und nicht in "Error" umgesetzt.
    //Dieser Test schlägt aktuell fehl, bis du den Bug fixst.
    @Test
    @DisplayName("should display error when a non-numeric key is pressed")
    void testNonNumericInput() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressUnaryOperationKey("x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


//Fehlerursache: liegt in der Behandlung des Vorzeichenwechsels in Kombination mit binären Operationen.
//Das pressNegativeKey() wirkt nicht korrekt, weil es vor der Ziffer 3 gedrückt wird – der Bildschirm zeigt dann z. B. -03, was beim Parsen eventuell ignoriert oder falsch interpretiert wird.
//Auch dieser Test schlägt aktuell fehl, zeigt also einen zweiten Fehler, der in einer anderen Methode liegt (pressNegativeKey() bzw. wie pressBinaryOperationKey() und pressEqualsKey() mit negativem zweiten Operanden umgehen).
    @Test
    @DisplayName("should correctly handle addition between positive and negative numbers")
    void testPositiveAndNegativeAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("+");
        calc.pressNegativeKey();
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "4"; // 7 + (-3) = 4
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
}

