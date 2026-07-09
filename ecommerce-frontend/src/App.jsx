import { Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import Orders from "./pages/Orders";
import Home from "./pages/Home";
import Products from "./pages/Products";
import ProductDetails from "./pages/ProductDetails";
import Login from "./pages/Login";
import Cart from "./pages/Cart";


function App() {

    return (
        <>
            <Navbar />

            <Routes>

                <Route path="/" element={<Home />} />

                <Route path="/products" element={<Products />} />

                <Route path="/products/:id" element={<ProductDetails />} />

                <Route path="/login" element={<Login />} />

                <Route path="/cart" element={<Cart />} />

                <Route path="/orders" element={<Orders/>} />

            </Routes>

        </>
    );
}

export default App;
