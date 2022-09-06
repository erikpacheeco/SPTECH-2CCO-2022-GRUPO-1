import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import "./Pages/general.css"
import VLibras from "@djpfs/react-vlibras"

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App/>
    <VLibras forceOnload={true}></VLibras>
  </React.StrictMode>
);

