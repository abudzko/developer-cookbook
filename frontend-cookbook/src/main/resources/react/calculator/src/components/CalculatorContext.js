import { createContext, useContext } from "react";
import { CalculatorEngine } from "./CalculatorEngine";

const CalculatorContext = createContext(null);

export const CalculatorContextProvider = ({ children }) => {
    console.log("CalculatorContextProvider");
    const calculatorEngine = new CalculatorEngine();
    const context = { calculatorEngine: calculatorEngine }
    return <CalculatorContext.Provider value={context}>{children}</CalculatorContext.Provider>
}

export const useCalculatorContext = () => useContext(CalculatorContext);