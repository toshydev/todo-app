import React, {useState} from "react";

import styled from "styled-components";

type Props = {
    card: Card
    onEdit: (id: number, data: { [k: string]: FormDataEntryValue; }) => void;
    onAdvance: (id: number, data: { [k: string]: FormDataEntryValue; }) => void;
}

const StyledForm = styled.form`

`;
const StyledCard = styled.li`
  display: grid;
    width: 90%;
  height: 5rem;
  grid-template: repeat(7, 1fr) / repeat(4, 1fr);
  gap: 1rem;
  
  p {
    grid-row: 1 / 6;
    grid-column: 1 / 5;
    background: white;
    padding: 0.5rem;
    border-radius: 5px;
  }
  
  h4 {
    grid-row: 6;
    grid-column: 1 / 5;
    text-align: center;
  }
  
  div {
    grid-row: 7;
    grid-column: 1 / 3;
  }
  
  button {
    grid-row: 7;
    grid-column: 3 / 5;
  }
`;


export default function Card(props: Props) {
    const [edit, setEdit] = useState(false);

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const formData = new FormData(event.currentTarget);
        const data = Object.fromEntries(formData);
        props.onEdit(props.card.id, data)
        event.currentTarget.reset();
        setEdit(false);
    }

    if (edit) {
        return <form onSubmit={handleSubmit}>
            <input id="todo" name="description" type="text" defaultValue={props.card.description}/>
            <select name={"status"}>
                <option value={"OPEN"}>Open</option>
                <option value={"IN_PROGRESS"}>In Progress</option>
                <option value={"DONE"}>Done</option>
            </select>
            <button type={"submit"}>Save</button>
            <button type={"button"} onClick={() => setEdit(false)}>Cancel</button>
        </form>
    } else {
        return (
            <StyledCard>
                <p>{props.card.description}</p>
                <h4>{props.card.status}</h4>
                <div>
                    <button type={"button"} onClick={() => setEdit(!edit)}>Edit</button>
                </div>
                <button onClick={() => {
                    props.onAdvance(props.card.id, {
                        description: props.card.description,
                        status: props.card.status
                    })
                }
                }>{props.card.status === "DONE" ? "Delete" : "Advance"}</button>
            </StyledCard>
        );
    }
}