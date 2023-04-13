import CalculatorDisplayPane from "./display/CalculatorDisplayPane";
import CalculatorButtonPane from "./buttons/CalculatorButtonPane";
import { createContext, useEffect } from "react";
import { CalculatorEngine } from "./CalculatorEngine";
import { EQUALS_KEY } from "./buttons/buttons";

export const CalculatorInputContext = createContext(null);
const calculatorEngine = new CalculatorEngine();

export default function Calculator(props) {
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
        <CalculatorInputContext.Provider value={
            {
                calculatorEngine: calculatorEngine,
            }
        }>
            <div className="calculator">
                <CalculatorDisplayPane />
                <CalculatorButtonPane />
            </div>
        </CalculatorInputContext.Provider>
    );
}
