import Calculator from 'components/Calculator';
import StartPage from 'components/StartPage';
import './App.css';
import { Routes, Route, Link } from 'react-router-dom';

export default function App() {
  return (
    <div>
      <nav>
        <Link to="/" className="nav-item">Start Page</Link>
        <Link to="/calculator" className="nav-item">Calculator</Link>
      </nav>
      <Routes>
        <Route path="/" element={<StartPage />}></Route>
        <Route path="/calculator" element={<Calculator />}></Route>
      </Routes>
    </div>
  );
}
