import { useEffect, useState } from "react";
import ProductCard from "../components/ProductCard";
import { getAllProducts } from "../services/productService";

function Home() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = async () => {
        try {
            const response = await getAllProducts();
            setProducts(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container mt-5">

            <div className="p-5 bg-primary text-white rounded mb-5">
                <h1>Welcome to ShopEasy</h1>
                <p>Discover amazing deals every day.</p>
            </div>

            <h2 className="mb-4">
                Featured Products
            </h2>

            <div className="row">

                {
                    products.map(product => (
                        <div className="col-md-3 mb-4" key={product.id}>
                            <ProductCard product={product}/>
                        </div>
                    ))
                }

            </div>

        </div>
    );
}

export default Home;
