import api from "./api";

export const getAllProducts = () => {
    return api.get("/products");
};

export const getProductById = (id) => {
    return api.get(`/products/${id}`);
};

export const searchProducts = (name) => {
    return api.get(`/products/search?name=${name}`);
};


export const filterByCategory = (id) => {
    return api.get(`/products/category/${id}`);
};


export const filterByPrice = (min,max) => {
    return api.get(`/products/price?min=${min}&max=${max}`);
};
