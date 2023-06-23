import {useEffect, useState} from "react";
import axios from "axios";
import CardList from "./components/CardList/CardList.tsx";
import Header from "./components/Header/Header.tsx";
import AddForm from "./components/AddForm/AddForm.tsx";
import "./App.css"

function App() {
    const [cards, setCards] = useState<Card[]>([]);

    useEffect(() => {
        axios.get("/api/todo")
            .then(response => response.data)
            .catch(console.error)
            .then(data => setCards(data))
    }, [])

    function handleAdd(data: { [k: string]: FormDataEntryValue}) {
        axios.post("/api/todo", data, {
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => response.data)
            .catch(console.error)
            .then(data => setCards(data))
    }

    function handleEdit(id: number, data: { [k: string]: FormDataEntryValue}) {
        axios.put(`/api/todo/${id}`, data, {
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => response.data)
            .catch(console.error)
            .then(data => setCards(cards.map(card => {
                if (card.id === id) {
                    return data;
                }
                return card;
            })))
    }

    function handleAdvance(id: number, data: { [k: string]: FormDataEntryValue}) {
        console.log(data)
        switch (data.status) {
            case "OPEN":
                data.status = "IN_PROGRESS";
                break;
            case "IN_PROGRESS":
                data.status = "DONE";
                break;
            default:
                data.status = "DELETE"
                break;
        }
        if (data.status === "DELETE") {
            handleDelete(id);
        } else {
            handleEdit(id, data)
        }
    }

    function handleDelete(id: number) {
        axios.delete(`/api/todo/${id}`)
            .then(response => response.data)
            .catch(console.error)
            .then(data => setCards(data))
    }

  return (
    <main>
        <Header/>
        {cards && <CardList cards={cards} onEdit={handleEdit} onAdvance={handleAdvance}/>}
        <AddForm onAdd={handleAdd}/>
    </main>
  )
}

export default App
