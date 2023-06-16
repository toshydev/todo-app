package click.snekhome.backend.models;

public record Card(int id, String description, Status status) {

    public Card edited(String newDescription, Status newStatus) {
        return new Card(this.id(), newDescription, newStatus);
    }
}
