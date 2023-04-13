import { useEffect, useState } from "react";

export default function useButtonClicked(button) {
    const [on, setOn] = useState(true)
    useEffect(() => {
        const buttonClickHandler = () => {
            let result = !on;
            setOn(result);
            console.log(result ? `enabled - ${result}` : `disabled - ${result}`);
        };

        const b = button.current;
        // console.log("init useButtonClicked");
        b.addEventListener("click", buttonClickHandler);
        return () => {
            // console.log("return useButtonClicked");
            b.removeEventListener("click", buttonClickHandler);
        }
    }, [on, button]);
    return on;
}