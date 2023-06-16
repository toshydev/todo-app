package click.snekhome.backend.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @ParameterizedTest
    @MethodSource("cards")
    void expectUpdatedCard_whenCardIsEdited(String description, Status status, Card expected) {
        Card original = new Card(1, "", Status.OPEN);
        assertEquals(expected, original.edited(description, status));
    }

    static Stream<Arguments> cards() {
        return Stream.of(
                Arguments.of("shopping", Status.IN_PROGRESS, new Card(1, "shopping", Status.IN_PROGRESS)),
                Arguments.of("123", Status.OPEN, new Card(1, "123", Status.OPEN)),
                Arguments.of("_____", Status.IN_PROGRESS, new Card(1, "_____", Status.IN_PROGRESS)),
                Arguments.of("", Status.DONE, new Card(1, "", Status.DONE)),
                Arguments.of("TEST", Status.OPEN, new Card(1, "TEST", Status.OPEN))
        );
    }
}