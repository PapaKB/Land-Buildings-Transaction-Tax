import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class LBTTTest {
    LBTT tax;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tax = new LBTT();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Band 1: £0 to £175,000")
    void test1() {
        assertEquals(0, tax.calculate(0));
        assertEquals(0, tax.calculate(50000));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Band 2: £175,001 to £250,000")
    void test2() {
        assertEquals(0.02, tax.calculate(175001));
        assertEquals(1500.00, tax.calculate(250000));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Band 3: £250,001 to £325,000")
    void test3() {
        assertEquals(1500.05, tax.calculate(250001));
        assertEquals(5250.00, tax.calculate(325000));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Band 4: £325,001 to £750,000")
    void test4() {
        assertEquals(5250.10, tax.calculate(325001));
        assertEquals(22750.00, tax.calculate(500000));
        assertEquals(47750.00, tax.calculate(750000));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Band 5: £750,001+")
    void test5() {
        assertEquals(47750.12, tax.calculate(750001));
        assertEquals(65750.00, tax.calculate(900000));
    }
}
