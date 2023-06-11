import { controls, EQUALS_KEY, RESET_INPUT_KEY, RESET_OUTPUT_KEY } from "./buttons/buttons";

export class CalculatorEngine {
    constructor() {
        let input = null;
        let output = null;

        this.setInput = (i) => input = i;
        this.setOutput = (o) => output = o;
        this.buttonClickHandler = (key) => {
            if (isControl(key)) {
                switch (key) {
                    case RESET_INPUT_KEY:
                        input.setInputValue('');
                        break;
                    case RESET_OUTPUT_KEY:
                        output.setOutputValue('');
                        break;
                    case EQUALS_KEY:
                        output.setOutputValue(calculate(input.inputValue));
                        break;
                    default:
                        console.log("Unsupport key");
                        break;
                }
            } else {
                input.setInputValue(input.inputValue + key);
            }
        }
    }
}
function isControl(key) {
    return controls.includes(key);
}

function calculate(expression) {
    try {
        return eval(expression);
    } catch (ex) {
        return "Can't parse expression";
    }
}
