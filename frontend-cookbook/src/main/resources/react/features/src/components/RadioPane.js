import React, { useState } from "react";

export default function RadioPane(props) {
    const [selected, setSelected] = useState("");
    return (
        <div>
            <div>{(() => true)()}</div>
            <h2>Radio</h2>
            <RadioGroup onChange={setSelected} selected={selected}>
                <RadioOption  value="v1">V1</RadioOption>
                <RadioOption value="v2">V2</RadioOption>
                <RadioOption value="v3">V3</RadioOption>
            </RadioGroup>
        </div>
    );
}

const RadioGroup = ({ onChange, selected, children }) => {
    let radioOptions = React.Children.map(children, (child) => {
        return React.cloneElement(child, {
            onChange,
            checked: child.props.value === selected,
        })
    });
    return (
        <div className="RadioGroup">{radioOptions}</div>
    );
}

const RadioOption = ({ value, checked, onChange, children }) => {
    return (
        <div className="radioOption">
            <input
                id={value}
                type="radio"
                name="value"
                value={value}
                checked={checked}
                onChange={e => { onChange(e.target.value) }} />
            <label htmlFor="{value}">{children}</label>
        </div>
    );
};
