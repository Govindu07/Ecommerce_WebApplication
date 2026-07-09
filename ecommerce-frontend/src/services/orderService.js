import api from "./api";


export const getOrdersByUser = (userId) => {

    return api.get(`/orders/user/${userId}`);

};