package click.snekhome.backend.models;

public enum Status {
    OPEN("open"),
    IN_PROGRESS("in progress"),
    DONE("done");

    private final String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
