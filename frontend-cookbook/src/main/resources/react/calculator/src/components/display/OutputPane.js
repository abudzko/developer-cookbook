import { useState } from "react";
import { useCalculatorContext } from "components/CalculatorContext";

export default function OutputPane(props) {
    const [outputValue, setOutputValue] = useState("result");
    useCalculatorContext().calculatorEngine.setOutput({ outputValue, setOutputValue });
    return (
        <div className="calculatorDisplayPane">
            <p className="calculatorOutput">{outputValue}</p>
        </div>
    );
}
