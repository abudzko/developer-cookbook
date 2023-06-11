import { useEffect, useState } from "react";


//High Order Component with Render props
export default function HocPane(props) {
    return (
        <div>
            <PanelMouseLogger />
            <PointMouseLogger />
        </div>
    );
}

const MousePosition = ({ render }) => {
    const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });
    useEffect(() => {
        const changeMousePositionHandler = (e) => {
            setMousePosition({
                x: e.clientX,
                y: e.clientY,
            });
        };
        window.addEventListener("click", changeMousePositionHandler);
        return () => {
            window.removeEventListener("click", changeMousePositionHandler);
        }
    }, []);
    return render({ mousePosition });
};


const PanelMouseLogger = () => {
    return (
        <div>
            <MousePosition
                render={
                    ({ mousePosition }) => (
                        <div>
                            <div>PaneMouseLogger</div>
                            <p>x: {mousePosition.x}</p>
                            <p>y: {mousePosition.y}</p>
                        </div>
                    )
                }
            />
        </div>
    );
};

const PointMouseLogger = () => {
    return (
        <div>
            <MousePosition render={
                ({ mousePosition }) => (
                    <p> PointMouseLogger: ({mousePosition.x}, {mousePosition.y})</p>
                )
            } />
        </div >
    );
};
