import { useEffect, useState } from "react";
import { getOrdersByUser } from "../services/orderService";


function Orders(){

    const [orders, setOrders] = useState([]);


    useEffect(() => {

        loadOrders();

    }, []);



    const loadOrders = async () => {

        try {

            const response = await getOrdersByUser(4);

            console.log("ORDERS:", response.data);

            setOrders(response.data);

        } catch(error) {

            console.log(error);

            alert("Failed to load orders");

        }

    };



    return(

        <div className="container mt-5">

            <h2 className="mb-4">
                My Orders
            </h2>


            {
                orders.length === 0 ? (

                    <div className="alert alert-warning">
                        No orders found
                    </div>

                ) : (


                    orders.map((order)=>(

                        <div 
                            className="card mb-3"
                            key={order.id}
                        >

                            <div className="card-body">


                                <h5>
                                    Order ID: {order.id}
                                </h5>


                                <p>
                                    Status: {order.status}
                                </p>


                                <p>
                                    Date: {order.orderDate}
                                </p>


                                <p>
                                    Total Amount:
                                    ₹{order.totalAmount}
                                </p>


                                <hr/>


                                <h6>
                                    Products
                                </h6>


                                {
                                    order.orderItems?.map((item)=>(

                                        <div key={item.id}>

                                            {item.product.name}
                                            {" "} 
                                            × {item.quantity}

                                        </div>

                                    ))
                                }


                            </div>


                        </div>

                    ))

                )
            }


        </div>

    );

}


export default Orders;