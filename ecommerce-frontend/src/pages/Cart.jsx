import { useEffect, useState } from "react";
import {
    getCart,
    removeCartItem,
    updateCartQuantity
} from "../services/cartService";

function Cart() {

    const [cart, setCart] = useState(null);


    useEffect(() => {
        loadCart();
    }, []);



    const loadCart = async () => {

        try {

            const response = await getCart(3);

            console.log("CART RESPONSE:", response.data);

            setCart(response.data);

        } catch (error) {

            console.log("CART ERROR:", error);

            alert("Failed to load cart");

        }

    };



    const handleRemove = async (itemId) => {

        try {

            await removeCartItem(itemId);

            alert("Item removed");

            loadCart();

        } catch(error) {

            console.log(error);

            alert("Failed to remove item");

        }

    };



    if (!cart) {

        return (

            <div className="container mt-5">

                <h3>Loading Cart...</h3>

            </div>

        );

    }

    const handleQuantity = async (itemId, currentQty, change) => {

        try {

            const newQty = currentQty + change;


            if(newQty <= 0){
                return;
            }


            await updateCartQuantity(itemId, newQty);


            loadCart();


        } catch(error){

            console.log(error);

            alert("Failed to update quantity");

        }

    };

    return (

        <div className="container mt-5">


            <h2 className="mb-4">
                Shopping Cart
            </h2>



            {
                !cart.items || cart.items.length === 0 ?

                (

                    <div className="alert alert-warning">

                        Your cart is empty.

                    </div>

                )

                :

                (

                    <>


                    {
                        cart.items.map((item)=>(


                            <div
                                className="card mb-3"
                                key={item.id}
                            >

                                <div className="card-body">


                                    <h4>
                                        {item.product.name}
                                    </h4>


                                    <p>
                                        Price:
                                        ₹{item.product.price}
                                    </p>


                                    <div className="d-flex align-items-center gap-3">

                                        <button
                                            className="btn btn-secondary"
                                            onClick={() =>
                                                handleQuantity(
                                                    item.id,
                                                    item.quantity,
                                                    -1
                                                )
                                            }
                                        >
                                            -
                                        </button>


                                        <h5>
                                            {item.quantity}
                                        </h5>


                                        <button
                                            className="btn btn-success"
                                            onClick={() =>
                                                handleQuantity(
                                                    item.id,
                                                    item.quantity,
                                                    1
                                                )
                                            }
                                        >
                                            +
                                        </button>

                                    </div>


                                    <p>
                                        Total:
                                        ₹{item.product.price * item.quantity}
                                    </p>


                                    <button
                                        className="btn btn-danger"
                                        onClick={() => handleRemove(item.id)}
                                    >
                                        Remove
                                    </button>


                                </div>

                            </div>


                        ))
                    }



                    <h3 className="mt-4">

                        Grand Total:
                        ₹
                        {
                            cart.items.reduce(
                                (total,item)=>
                                total +
                                (item.product.price * item.quantity),
                                0
                            )
                        }

                    </h3>


                    </>

                )

            }



        </div>

    );

}


export default Cart;