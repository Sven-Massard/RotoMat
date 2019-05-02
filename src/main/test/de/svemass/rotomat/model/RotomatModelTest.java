package de.svemass.rotomat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotomatModelTest {

    private final static int amountShelves = 3;
    private final static int amountCompartmentsPerShelf = 6;
    private RotomatModel rotomatModel;

    @BeforeEach
    void beforeEach() {
        rotomatModel = new RotomatModel(amountShelves, amountCompartmentsPerShelf);
    }

    @Test
    void renameSlotTest() {
        rotomatModel.renameSlot(1, 3, "Test");
        String expectedResult = "[Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach]\n"
                + "[Leeres Fach, Leeres Fach, Leeres Fach, Test, Leeres Fach, Leeres Fach]\n"
                + "[Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach]\n";
        assertEquals(expectedResult, rotomatModel.toString());
    }

    @Test
    void toStringTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amountShelves; i++) {
            sb.append("[");
            for (int j = 0; j < amountCompartmentsPerShelf; j++) {
                sb.append("Leeres Fach, ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("]");
            sb.append("\n");
        }
        assertEquals(sb.toString(), rotomatModel.toString());
    }


}