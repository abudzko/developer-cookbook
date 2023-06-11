import { useState } from "react";

export default function TmpPane(props) {
    const [todos, setTodos] = useState([
        {
            id: 'todo1',
            createdAt: '18:00',
        },
        {
            id: 'todo2',
            createdAt: '20:30',
        }
    ]);

    const reverseOrder = () => {
        // Reverse is a mutative operation, so we need to create a new array first.
        setTodos([...todos].reverse());
    };

    return (
        <>
            <div>
                <button onClick={reverseOrder}>Reverse</button>
                {todos.map((todo, index) => (
                    <ToDo key={index} id={todo.id} createdAt={todo.createdAt} />
                ))}
            </div>
            <MyButton onClick={() => console.log("AppClick")}>Submit</MyButton>
        </>
    );
}

const ToDo = props => (
    <p>
        <label>{props.id}</label>
        <input />
        <label>{props.createdAt}</label>
    </p>
);


const Button = ({ children, ...rest }) => (
    <button onClick={() => console.log("ButtonClick")} {...rest}>
        {children}
    </button>
);

const withClick = (Component) => {
    const handleClick = () => {
        console.log("WithClick");
    };

    return (props) => {
        return <Component {...props} onClick={handleClick} />;
    };
};

const MyButton = withClick(Button);