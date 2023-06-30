import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import "./Style.css"
import './index.css'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.js";
import '@fortawesome/fontawesome-free/css/all.min.css';
import { Provider } from "react-redux"
import store from './app/store.js'
import { BrowserRouter } from 'react-router-dom'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>,
);
