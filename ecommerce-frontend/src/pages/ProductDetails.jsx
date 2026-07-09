import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";
import { addToCart } from "../services/cartService";


function ProductDetails(){


    const {id} = useParams();


    const [product,setProduct] = useState(null);



    useEffect(()=>{


        getProduct();


    },[]);



    const getProduct = async()=>{


        try{

            const response =
                await api.get(`/products/${id}`);


            setProduct(response.data);


        }
        catch(error){

            console.log(error);

        }

    };



    const handleAddToCart = async()=>{


        try{


          await addToCart(3, product.id);


            alert("Product added to cart");


        }
       catch(error){

           console.log(error);

           if(error.response?.status === 403){

               alert("Access denied. Check backend security.");

           }
           else if(error.response?.status === 401){

               alert("Session expired. Please login again.");

           }
           else{

               alert("Something went wrong.");

           }

       }

    };



    if(!product){

        return <h3>Loading...</h3>;

    }



    return(

        <div className="container mt-5">


            <div className="card">


                <div className="card-body">


                    <h2>
                        {product.name}
                    </h2>



                    <h4>
                        ₹ {product.price}
                    </h4>



                    <p>
                        {product.description}
                    </p>



                    <button
                        className="btn btn-warning"
                        onClick={handleAddToCart}
                    >

                        Add To Cart

                    </button>


                </div>


            </div>


        </div>

    );

}


export default ProductDetails;
