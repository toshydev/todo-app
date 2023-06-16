package click.snekhome.backend.service;

import click.snekhome.backend.models.*;
import click.snekhome.backend.repo.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    public final CardRepo cardRepo;

    public List<Card> add(Card card) {
        this.cardRepo.add(card);
        return cardRepo.getCards();
    }

    public Card get(int id) {
        return this.cardRepo.get(id);
    }

    public List<Card> list() {
        return this.cardRepo.getCards();
    }

    public List<Card> remove(int id) {
        this.cardRepo.remove(id);
        return this.cardRepo.getCards();
    }

    public Card edit(int id, String description, Status status) {
        this.cardRepo.edit(id, description, status);
        return this.cardRepo.get(id);
    }

}
