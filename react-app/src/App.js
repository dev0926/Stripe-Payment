import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import {AppContextProvider} from "./context/AppContext";
import Products from "./components/products/Products";
import Checkout from "./components/checkout/Checkout";
import Success from "./components/success/Success";

function App() {
    return (
        <div className="App">
            <AppContextProvider>
                <Router>
                    <Routes>
                        <Route path="/" element={<Products />} />
                        <Route path="/checkout" element={<Checkout />} />
                        <Route path="/success" element={<Success />} />
                    </Routes>
                </Router>
            </AppContextProvider>
        </div>
    );
}

export default App;
