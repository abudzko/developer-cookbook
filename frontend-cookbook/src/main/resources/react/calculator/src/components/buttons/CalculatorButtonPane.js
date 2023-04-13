import NumberButtonsPane from "./NumberButtonsPane";
import OperationButtonsPane from "./OperationButtonsPane";

export default function CalculatorButtonPane(props) {
    return (
        <>
            <OperationButtonsPane />
            <NumberButtonsPane />
        </>
    );
}
