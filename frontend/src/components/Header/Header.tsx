import Dodo from "../../assets/pngegg.png"
import styled from "styled-components";


const StyledHeader = styled.header`
  display: flex;
  background: linear-gradient(orange, lightblue);
  align-items: center;
  justify-content: center;
  width: 100%;
`;
function Header() {
    return (
        <StyledHeader>
            <img src={Dodo} alt="Dodo" width={100} height={"auto"}/>
            <h1>Todo-Dodo</h1>
        </StyledHeader>
    );
}

export default Header;