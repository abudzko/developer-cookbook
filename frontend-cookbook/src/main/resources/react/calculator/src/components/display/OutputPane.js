import { useContext, useState } from "react";
import { CalculatorInputContext } from "components/Calculator";

export default function OutputPane(props) {
    const [outputValue, setOutputValue] = useState("result");
    const calculatorContext = useContext(CalculatorInputContext);
    calculatorContext.calculatorEngine.setOutput({ outputValue, setOutputValue });
    return (
        <div className="calculatorDisplayPane">
            <p className="calculatorOutput">{outputValue}</p>
        </div>
    );
}
