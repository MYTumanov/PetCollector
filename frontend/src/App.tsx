import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./reset.css";
import { MainList } from "./components/MainList/MainList";

function App() {
  return (
    <div className="App">
      <MainList items={["Максим", "Денис", "Дарина", "Вася"]} />
    </div>
  );
}

export default App;
