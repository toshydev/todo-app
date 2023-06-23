import Card from "../Card/Card.tsx";
import styled from "styled-components";

type Props = {
    cards: Card[]
    onEdit: (id: number, data: { [k: string]: FormDataEntryValue; }) => void;
    onAdvance: (id: number, data: { [k: string]: FormDataEntryValue; }) => void;
}

const StyledBoard = styled.div`
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: wrap;
  gap: 3rem;
  background: lightblue;
  justify-content: center;
  width: 100%;
  height: 100vh;
`;


const StyledList = styled.ul`
  list-style: none;
  margin: 0.5rem;
  padding: 0;
  display: flex;
  flex-direction: column;
  background: #f0f0f0;
  border: 1px solid black;
  border-radius: 12px;
  width: 25%;
  height: 50%;
  transition: 0.1s ease-in;
  align-items: center;
  
  &:hover {
    transform: scale(1.05);
  }
  
  
  h2 {
    color: orange;
    text-align: center;
  }
  
  form {
    align-self: center;
  }
  
  li {
    align-self: center;
  }
`;

const StyledColumn = styled.ul`
  padding: 0;
  list-style: none;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

function CardList(props: Props) {
    return (
        <StyledBoard>
            <StyledList>
                <h2>Todo</h2>
                <StyledColumn>
                    {props.cards.filter(card => card.status === "OPEN").map(card => {
                        return <Card card={card} key={card.id} onEdit={props.onEdit} onAdvance={props.onAdvance}/>
                    })}
                </StyledColumn>
            </StyledList>
            <StyledList>
                <h2>Doing</h2>
                <StyledColumn>
                    {props.cards.filter(card => card.status === "IN_PROGRESS").map(card => {
                        return <Card card={card} key={card.id} onEdit={props.onEdit} onAdvance={props.onAdvance}/>
                    })}
                </StyledColumn>
            </StyledList>
            <StyledList>
                <h2>Done</h2>
                <StyledColumn>
                    {props.cards.filter(card => card.status === "DONE").map(card => {
                        return <Card card={card} key={card.id} onEdit={props.onEdit} onAdvance={props.onAdvance}/>
                    })}
                </StyledColumn>
            </StyledList>
        </StyledBoard>
    );
}

export default CardList;