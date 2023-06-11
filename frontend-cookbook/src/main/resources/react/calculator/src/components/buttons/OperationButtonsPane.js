import { mathOperations, controls } from "./buttons";
import { useCalculatorContext } from "components/CalculatorContext";

export default function OperationButtonsPane(props) {
    const calculatorEngine = useCalculatorContext().calculatorEngine

    function clickHandelr(e) {
        let key = e.target.innerText
        calculatorEngine.buttonClickHandler(key);
    }
    let controlButtons = [];
    controls.forEach(c => {
        controlButtons.push(<button key={c} className="operationButton" onClick={clickHandelr}>{c}</button>);
    })
    let operationButtons = [];
    mathOperations.forEach(op => {
        operationButtons.push(<button key={op.key} className="operationButton" onClick={clickHandelr}>{op.key}</button>);
    })

    return (
        <div className="operationButtonPane">
            {controlButtons}
            {operationButtons}
        </div>
    );
}
