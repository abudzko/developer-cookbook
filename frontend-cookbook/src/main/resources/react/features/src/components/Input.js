export default function Input(props) {
    return (
        <input value={props.value} onChange={props.inputChangeHandler} className="input">{props.buttonText}</input>
    );
}
