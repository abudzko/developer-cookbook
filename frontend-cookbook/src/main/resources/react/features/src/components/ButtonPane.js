import { useState, useEffect, useReducer, useRef } from "react"
import Button from "./Button";
import useClicked from "./hooks/useClicked";
import Input from "./Input";

export default function ButtonPane(props) {
    const [inputValue, setInputValue] = useState("");
    const [toggle, setToggle] = useState(true);

    const toggleRef = useRef(null);
    const customHooktoRef = useRef(null);
    const enabled = useClicked(customHooktoRef)

    const inputChangeHandler = (e) => {
        setInputValue(e.target.value);
    };
    const buttons = [];
    buttons.push(createButton("Change input", (key) => setInputValue(value => value + key)));
    buttons.push(createButton("Change toggle", (key) => setToggle(bool => !bool)));

    useEffect(() => {
        // console.log("ButtonPane use effect");
        return () => { }
    }, [toggle]);
    return (
        <div className="buttonPane">
            <Input value={inputValue} inputChangeHandler={inputChangeHandler} />
            {buttons}
            <p ref={toggleRef}>{toggle.toString()}</p>
            <p ref={customHooktoRef}>{enabled.toString()}</p>
        </div>
    );
}

function createButton(key, fn) {
    return <Button key={key} buttonText={key} buttonClickHandler={() => {
        fn(key);
        console.log(`clicked ${key}`);
    }} />
}
