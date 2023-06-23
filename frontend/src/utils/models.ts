enum Status {
    OPEN = "Open",
    IN_PROGRESS = "In Progess",
    DONE = "Done"
}

interface Card {
    id: number,
    description: string,
    status: string
}
