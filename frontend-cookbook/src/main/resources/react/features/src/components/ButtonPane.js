import { useState, useEffect, useReducer, useRef } from "react"
import Button from "./Button";
import useButtonClicked from "./hooks/useButtonClicked";
import Input from "./Input";

export default function ButtonPane(props) {
    const [inputValue, setInputValue] = useState("");
    const [myState, setMyState] = useState(true);

    const button = useRef(null);
    const enabled =useButtonClicked(button)

    const inputChangeHandler = (e) => {
        setInputValue(e.target.value);
    };
    const buttons = [];
    for (let i = 0; i < 3; i++) {
        let button = createButton(i, setInputValue, setMyState);
        buttons.push(button);
    }
    const someState = true;
    // let id = Math.random();
    useEffect(() => {
        console.log("Start use effect");
        return () => {
            console.log("End use effect");
        }
    }, [someState]);
    return (
        <div className="buttonPane">
            <Input value={inputValue} inputChangeHandler={inputChangeHandler} />
            {buttons}
            <button ref={button}>{enabled.toString()}</button>
        </div>
    );
}


function createButton(i, setInputValueFn, setMyStateFn) {
    return <Button key={i} buttonText={i} buttonClickHandler={() => {
        setInputValueFn(v => v + i);
        setMyStateFn(b => !b);

        console.log(`clicked ${i}`);
    }} />
}
