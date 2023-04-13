import { useContext } from "react";
import { mathOperations, controls } from "./buttons";
import { CalculatorInputContext } from "components/Calculator";



export default function OperationButtonsPane(props) {
    const calculatorContext = useContext(CalculatorInputContext);
    function clickButtonHandler(e) {
        calculatorContext.calculatorEngine.handleButtonClick(e.target.innerText);
    }
    let controlButtons = [];
    controls.forEach(c => {
        controlButtons.push(<button key={c} className="operationButton" onClick={clickButtonHandler}>{c}</button>);
    })
    let operationButtons = [];
    mathOperations.forEach(mo => {
        operationButtons.push(<button key={mo.key} className="operationButton" onClick={clickButtonHandler}>{mo.key}</button>);
    })

    return (
        <div className="operationButtonPane">
            {controlButtons}
            {operationButtons}
        </div>
    );
}
