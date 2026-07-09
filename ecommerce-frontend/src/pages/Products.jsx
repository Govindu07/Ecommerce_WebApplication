import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import {
    getAllProducts,
    searchProducts,
    filterByPrice
} from "../services/productService";


function Products() {

    const [products, setProducts] = useState([]);

    const [search, setSearch] = useState("");

    const [minPrice, setMinPrice] = useState("");

    const [maxPrice, setMaxPrice] = useState("");



    useEffect(() => {

        loadProducts();

    }, []);



    const loadProducts = async () => {

        try {

            const response = await getAllProducts();

            setProducts(response.data);

        } catch(error) {

            console.log(error);

            alert("Failed to load products");

        }

    };



    const handleSearch = async () => {

        try {

            if(search.trim()===""){

                loadProducts();

                return;

            }


            const response = await searchProducts(search);

            setProducts(response.data);


        } catch(error){

            console.log(error);

        }

    };



    const handlePriceFilter = async () => {

        try {

            const response = await filterByPrice(
                minPrice,
                maxPrice
            );

            setProducts(response.data);


        } catch(error){

            console.log(error);

        }

    };



    return (

        <div className="container mt-5">


            <h2 className="mb-4">
                Products
            </h2>



            {/* Search */}

            <div className="row mb-4">


                <div className="col-md-6">

                    <input

                        className="form-control"

                        placeholder="Search products..."

                        value={search}

                        onChange={(e)=>setSearch(e.target.value)}

                    />

                </div>


                <div className="col-md-2">

                    <button

                        className="btn btn-primary"

                        onClick={handleSearch}

                    >

                        Search

                    </button>

                </div>


            </div>




            {/* Price Filter */}

            <div className="row mb-4">


                <div className="col-md-3">

                    <input

                        className="form-control"

                        placeholder="Min Price"

                        value={minPrice}

                        onChange={(e)=>setMinPrice(e.target.value)}

                    />

                </div>



                <div className="col-md-3">

                    <input

                        className="form-control"

                        placeholder="Max Price"

                        value={maxPrice}

                        onChange={(e)=>setMaxPrice(e.target.value)}

                    />

                </div>



                <div className="col-md-2">

                    <button

                        className="btn btn-success"

                        onClick={handlePriceFilter}

                    >

                        Filter

                    </button>

                </div>


            </div>




            <div className="row">


                {
                    products.map((product)=>(


                        <div

                            className="col-md-4 mb-4"

                            key={product.id}

                        >


                            <div className="card h-100">


                                <div className="card-body">


                                    <h4>
                                        {product.name}
                                    </h4>


                                    <h5>
                                        ₹ {product.price}
                                    </h5>


                                    <p>
                                        {product.description}
                                    </p>



                                    <Link

                                        to={`/products/${product.id}`}

                                        className="btn btn-primary"

                                    >

                                        View Details

                                    </Link>


                                </div>


                            </div>


                        </div>


                    ))
                }


            </div>


        </div>

    );

}


export default Products;
