import { Link } from "react-router-dom";

function ProductCard({ product }) {
    return (
        <div className="card h-100 shadow-sm">

            <img
                src={product.imgUrl}
                className="card-img-top"
                alt={product.name}
                style={{ height: "220px", objectFit: "cover" }}
            />

            <div className="card-body">

                <h5 className="card-title">
                    {product.name}
                </h5>

                <p className="card-text">
                    {product.description}
                </p>

                <h4 className="text-success">
                    ₹{product.price}
                </h4>

                <Link
                    to={`/products/${product.id}`}
                    className="btn btn-outline-primary w-100 mb-2"
                >
                    View Details
                </Link>
                <button className="btn btn-primary w-100">
                    Add to Cart
                </button>

            </div>

        </div>
    );
}

export default ProductCard;