import InputPane from "./InputPane";
import OutputPane from "./OutputPane";

export default function CalculatorDisplayPane(props) {
    return (
        <div className="calculatorDisplayPane">
            <OutputPane />
            <InputPane />
        </div>
    );
}
