import { fireEvent, render, screen } from '@testing-library/react';
import App from './App';

test('RadioPane selected attribute', () => {
  render(<App />);
  const selectedRadioOption = document.querySelector('#v1');
  fireEvent.click(selectedRadioOption);
  expect(selectedRadioOption.checked).toBe(true);
});
