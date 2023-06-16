package click.snekhome.backend;

import click.snekhome.backend.models.Card;
import click.snekhome.backend.models.Status;
import click.snekhome.backend.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public Map<Integer, Card> list() {
        return this.cardService.list();
    }

    @GetMapping("{id}")
    public Card get(@PathVariable int id) {
        return this.cardService.get(id);
    }

    @PostMapping
    public Map<Integer, Card> add(@RequestBody Card card) {
        this.cardService.add(card);
        return this.cardService.list();
    }

    @PutMapping("{id}")
    public Card edit(@PathVariable int id, @RequestBody String description, @RequestBody Status status) {
        this.cardService.edit(id, description, status);
        return this.cardService.get(id);
    }

    @DeleteMapping("{id}")
    public Map<Integer, Card> delete(@PathVariable int id) {
        this.cardService.remove(id);
        return this.cardService.list();
    }
}
