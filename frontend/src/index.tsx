import { createRoot } from "react-dom/client";
import React from "react";
import "./style.css";

document.addEventListener("DOMContentLoaded", () => {
  const rootNode = document.getElementById("root");
  const root = createRoot(rootNode);
  root.render(<h1>Привет, мир!</h1>);
});
