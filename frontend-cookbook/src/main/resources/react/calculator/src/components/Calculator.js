import CalculatorDisplayPane from "./display/CalculatorDisplayPane";
import CalculatorButtonPane from "./buttons/CalculatorButtonPane";
import { useEffect } from "react";
import { EQUALS_KEY } from "./buttons/buttons";
import { useCalculatorContext } from "./CalculatorContext";

export default function Calculator(props) {
    console.log("Calculator");
    const calculatorEngine = useCalculatorContext().calculatorEngine;
    useEffect(
        () => {
            const eventListenerId = window.addEventListener("keydown", (e) => {
                if (e.code === 'Equal' || e.code === 'Enter') {
                    calculatorEngine.handleButtonClick(EQUALS_KEY);
                }
            });
            return () => {
                window.removeEventListener("keydown", eventListenerId)
            }
        }
    );
    return (
        <div className="calculator">
            <CalculatorDisplayPane />
            <CalculatorButtonPane />
        </div>
    );
}
