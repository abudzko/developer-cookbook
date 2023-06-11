import Calculator from 'components/Calculator';
import StartPage from 'components/StartPage';
import './App.css';
import { Routes, Route, Link } from 'react-router-dom';
import { CalculatorContextProvider } from 'components/CalculatorContext';
import React from 'react';

export default function App() {
  const CalculatorM = React.memo(() => <Calculator />);
  return (
    <div>
      <nav>
        <Link to="/" className="nav-item">Start Page</Link>
        <Link to="/calculator" className="nav-item">Calculator</Link>
      </nav>
      <Routes>
        <Route path="/" element={<StartPage />}></Route>
        <Route path="/calculator"
          element={
            <CalculatorContextProvider>
              <CalculatorM />
            </CalculatorContextProvider>
          }>
        </Route>
      </Routes>
    </div>
  );
}
