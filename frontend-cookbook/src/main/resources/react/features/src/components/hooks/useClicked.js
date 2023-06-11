import { useEffect, useState } from "react";

export default function useClicked(ref) {
    const [on, setOn] = useState(true)
    useEffect(() => {
        const buttonClickHandler = () => {
            let result = !on;
            setOn(result);
            console.log(result ? `enabled - ${result}` : `disabled - ${result}`);
        };

        const element = ref.current;
        // console.log("Init useClicked");
        element.addEventListener("click", buttonClickHandler);
        return () => {
            // console.log("return useButtonClicked");
            element.removeEventListener("click", buttonClickHandler);
        }
    }, [on, ref]);
    return on;
}