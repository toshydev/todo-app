import React from "react";
import styled from "styled-components";

type Props = {
    onAdd: (data: { [k: string]: FormDataEntryValue }) => void;
}

const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 15rem;
  background: #f0f0f0;
  position: absolute;
  border-radius: 12px;
  border: 2px solid orange;
  gap: 1rem;
  transition: 0.1s ease-in;
  top: 75%;
  
  &:hover {
    transform: scale(1.1);
  }
  
  label {
    text-align: center;
    font-weight: bold;
    font-size: 1.5rem;
  }
  
  
  input {
    border-radius: 50px;
    border: 1px solid transparent;
    margin: 0.5rem;
    height: 2rem;
    text-align: center;
  }
  
  input:focus {
    outline: 2px dashed orange;
  }
  
  button {
    background: lightblue;
    font-weight: bold;
    border: none;
    border-radius: 50px;
    transition: 0.1s ease-in;
    margin: 0.5rem;
    height: 2rem;
  }
  
  button:hover {
    background: orange;
  }
`;
function AddForm(props: Props) {

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const formData = new FormData(event.currentTarget);
        const data = Object.fromEntries(formData);
        console.log(data);
        data.status = "OPEN"
        props.onAdd(data);
        event.currentTarget.reset();
    }

    return (
        <StyledForm onSubmit={handleSubmit}>
            <label htmlFor="newTodo">Add Todo</label>
            <input id="newTodo" name="description" type="text" placeholder="Todo description"/>
            <button type="submit">Submit</button>
        </StyledForm>
    );
}

export default AddForm;