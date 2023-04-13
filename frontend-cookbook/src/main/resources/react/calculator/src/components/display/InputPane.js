import { useContext, useState } from "react";
import { CalculatorInputContext } from "components/Calculator";

export default function InputPane(props) {
    const [inputValue, setInputValue] = useState("");
    const calculatorContext = useContext(CalculatorInputContext);
    calculatorContext.calculatorEngine.setInput({ inputValue, setInputValue });
    function inputChangeHandler(e) {
        setInputValue(e.target.value);
    }
    return (
        <>
            <input value={inputValue} className="calculatorInput" onChange={inputChangeHandler} />
        </>
    );
}
