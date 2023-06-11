import { numbers } from "./buttons";
import { useCalculatorContext } from "components/CalculatorContext";

export default function NumberButtonsPane(props) {
    console.log("NumberButtonsPane");
    const calculatorEngine = useCalculatorContext().calculatorEngine;
    function buttonClickHandler(e) {
        calculatorEngine.buttonClickHandler(e.target.innerText);
    }
    let buttons = [];
    numbers.forEach(b => {
        let id = `button${b}`;
        buttons.push(<button key={id} id={id} className="numberButton" onClick={buttonClickHandler} >{b}</button>);
    })

    return (
        <div className="numberButtonPane">
            {buttons}
        </div>
    );
}
