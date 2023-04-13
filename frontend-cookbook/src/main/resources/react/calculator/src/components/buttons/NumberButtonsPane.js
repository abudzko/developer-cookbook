import { numbers } from "./buttons";
import { CalculatorInputContext } from "components/Calculator";
import { useContext } from "react";

export default function NumberButtonsPane(props) {
    const calculatorContext = useContext(CalculatorInputContext);
    function clickButtonHandler(e) {
        calculatorContext.calculatorEngine.handleButtonClick(e.target.innerText);
    }
    let buttons = [];
    numbers.forEach(b => {
        let id = `button${b}`;
        buttons.push(<button key={id} id={id} className="numberButton" onClick={clickButtonHandler} >{b}</button>);
    })

    return (
        <div className="numberButtonPane">
            {buttons}
        </div>
    );
}
