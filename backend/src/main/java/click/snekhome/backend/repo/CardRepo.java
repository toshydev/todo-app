package click.snekhome.backend.repo;

import click.snekhome.backend.models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CardRepo {
    private List<Card> cards;

    public CardRepo() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card get(int id) {
        for (Card card : cards) {
            if (card.id() == id) {
                return card;
            }
        }
        return null;
    }

    public void remove(int id) {
        this.cards.removeIf((card) -> card.id() == id);
    }

    public void edit(int id, String description, Status status) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).id() == id) {
                this.cards.set(i, cards.get(i).edited(description, status));
            }
        }
    }
}
