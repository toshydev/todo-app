package click.snekhome.backend.repo;

import click.snekhome.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardRepoTest {

    private CardRepo cardRepo;

    @BeforeEach
    void setup() {
        this.cardRepo = new CardRepo();
    }

    @Test
    void expectCardInCardList_whenAddingCard() {
        Card newCard = new Card(1, "feeding cat", Status.DONE);
        this.cardRepo.add(newCard);
        List<Card> expected = new ArrayList<>(List.of((newCard)));
        assertThat(this.cardRepo.getCards()).isEqualTo(expected);
    }

    @Test
    void expectCard_whenGettingById() {
        Card newCard = new Card(1, "feeding cat", Status.DONE);
        this.cardRepo.add(newCard);
        assertEquals(cardRepo.get(1), newCard);
    }

    @Test
    void expectUpdatedCard_whenEditingCard() {
        Card newCard = new Card(1, "feeding cat", Status.DONE);
        this.cardRepo.add(newCard);
        this.cardRepo.edit(1, "cleaning", Status.DONE);
        assertEquals(cardRepo.get(1), new Card(1, "cleaning", Status.DONE));
    }


}