package click.snekhome.backend.service;

import click.snekhome.backend.models.*;
import click.snekhome.backend.repo.CardRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CardServiceTest {
    CardRepo cardRepo = mock(CardRepo.class);
    CardService cardService = new CardService(cardRepo);

    @Test
    void getCards_shouldReturnAllCards() {
        //GIVEN
        Card testCard = new Card(1, "shopping", Status.OPEN);
        List<Card> expected = new ArrayList<>(List.of(testCard));
        //WHEN
        when(cardRepo.getCards()).thenReturn(expected);
        List<Card> result = cardService.list();
        //THEN
        assertEquals(expected, result);
        verify(cardRepo).getCards();
    }

    @Test
    void get_shouldReturnCorrectCard() {
        //GIVEN
        int id = 1;
        Card expected = new Card(1, "shopping", Status.OPEN);
        //WHEN
        when(cardRepo.get(id)).thenReturn(expected);
        Card result = cardService.get(1);
        //THEN
        assertEquals(expected, result);
        verify(cardRepo).get(id);
    }

    @Test
    void add_shouldReturnAllCards() {
        //GIVEN
        Card testCard = new Card(1, "shopping", Status.OPEN);
        List<Card> expected = new ArrayList<>(List.of(testCard));
        //WHEN
        when(cardRepo.getCards()).thenReturn(expected);
        List<Card> result = cardService.add(testCard);
       //THEN
        assertEquals(expected, result);
        verify(cardRepo).getCards();
    }

    @Test
    void remove_shouldReturnAllCards() {
        int id = 1;
        List<Card> expected = new ArrayList<>();
        //WHEN
        when(cardRepo.getCards()).thenReturn(expected);
        List<Card> result = cardService.remove(id);
        //THEN
        assertEquals(expected, result);
        verify(cardRepo).getCards();
    }

    @Test
    void edit_shouldReturnUpdateCard() {
        //GIVEN
        int id = 1;
        String description = "cooking";
        Status status = Status.IN_PROGRESS;
        Card expected = new Card(1, "cooking", Status.IN_PROGRESS);
        //WHEN
        when(cardRepo.get(id)).thenReturn(expected);
        Card result = cardService.edit(id, description, status);
        //THEN
        assertEquals(expected, result);
        verify(cardRepo).get(id);
    }
}