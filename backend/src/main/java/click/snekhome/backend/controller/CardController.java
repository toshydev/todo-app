package click.snekhome.backend.controller;

import click.snekhome.backend.models.*;
import click.snekhome.backend.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private static int cardCount;

    @GetMapping
    public List<Card> list() {
        return this.cardService.list();
    }

    @GetMapping("{id}")
    public Card get(@PathVariable int id) {
        return this.cardService.get(id);
    }

    @PostMapping
    public List<Card> add(@RequestBody Map<String, String> details) {
        if (cardCount < 1) {
            cardCount = 1;
        } else {
            cardCount++;
        }
        Card cardToAdd = new Card(cardCount, details.get("description"), Status.valueOf(details.get("status")));
        this.cardService.add(cardToAdd);
        return this.cardService.list();
    }

    @PutMapping("{id}")
    public Card edit(@PathVariable int id, @RequestBody Map<String, String> details) {
        this.cardService.edit(id, details.get("description"), Status.valueOf(details.get("status")));
        return this.cardService.get(id);
    }

    @DeleteMapping("{id}")
    public List<Card> delete(@PathVariable int id) {
        this.cardService.remove(id);
        return this.cardService.list();
    }
}
