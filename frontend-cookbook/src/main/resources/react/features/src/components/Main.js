import ButtonPane from "./ButtonPane";
import FocusInput from "./FocusInput";
import HocPane from "./HocPane";
import RadioPane from "./RadioPane";
import TmpPane from "./TmpPane";

export default function Main(props) {
    return (
        <>
            <ButtonPane />
            <FocusInput placeHolder={"Type ..."} />
            <RadioPane />
            <HocPane />
            <TmpPane />
        </>
    )

}
