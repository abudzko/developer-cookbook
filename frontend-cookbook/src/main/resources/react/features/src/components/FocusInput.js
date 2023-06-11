import { useRef } from "react";

export default function FocusInput({ placeHolder }) {
    const inputRef = useRef(null);
    function clickHandler() {
        inputRef.current.focus();
    }
    return (
        <>
            <input ref={inputRef} placeholder={placeHolder} />
            <button type="button" onClick={clickHandler}>Focus</button>
        </>
    );
}
