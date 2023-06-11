import { useState } from "react";
import { useCalculatorContext } from "components/CalculatorContext";

export default function InputPane(props) {
    const [inputValue, setInputValue] = useState("");
    useCalculatorContext().calculatorEngine.setInput({ inputValue, setInputValue });
    function inputChangeHandler(e) {
        setInputValue(e.target.value);
    }
    return (
        <>
            <input value={inputValue} className="calculatorInput" onChange={inputChangeHandler} />
        </>
    );
}
