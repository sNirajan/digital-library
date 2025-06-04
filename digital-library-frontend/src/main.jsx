import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App.jsx";
import BooksPage from "./pages/BooksPage.jsx";
import ReturnPage from "./pages/ReturnPage.jsx";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/books" element={<BooksPage />} />
        {/* Inside <Routes>: */}
        <Route path="/return" element={<ReturnPage />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
