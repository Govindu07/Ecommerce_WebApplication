import api from "./api";


export const getCart = (cartId) => {
    return api.get(`/cart/${cartId}`);
};


export const addToCart = (cartId, productId) => {
    return api.post(`/cart/${cartId}/add/${productId}?qty=1`);
};


export const removeCartItem = (cartItemId) => {
    return api.delete(`/cart/remove/${cartItemId}`);
};


export const updateCartQuantity = (cartItemId, qty) => {
    return api.put(`/cart/update/${cartItemId}?qty=${qty}`);
};