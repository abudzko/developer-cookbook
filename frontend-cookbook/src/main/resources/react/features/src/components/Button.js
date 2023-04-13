export default function Button(props) {
    return (
        <button onClick={props.buttonClickHandler} className="button" >{props.buttonText} </button>
    );
}
