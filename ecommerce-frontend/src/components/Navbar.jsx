import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const token = localStorage.getItem("token");


    const logout = () => {

        localStorage.removeItem("token");

        navigate("/login");

    };


    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

            <div className="container">

                <Link className="navbar-brand fw-bold" to="/">
                    ShopEasy
                </Link>


                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>


                <div className="collapse navbar-collapse" id="navbarNav">


                    <ul className="navbar-nav me-auto">


                        <li className="nav-item">
                            <Link className="nav-link" to="/">
                                Home
                            </Link>
                        </li>


                        <li className="nav-item">
                            <Link className="nav-link" to="/products">
                                Products
                            </Link>
                        </li>


                    </ul>



                    <div className="d-flex">


                        {
                            token ? (

                                <>

                                    <span className="text-white me-3 mt-2">
                                        Welcome User
                                    </span>


                                    <button
                                        className="btn btn-danger me-2"
                                        onClick={logout}
                                    >
                                        Logout
                                    </button>

                                </>


                            ) : (


                                <Link
                                    className="btn btn-outline-light me-2"
                                    to="/login"
                                >
                                    Login
                                </Link>


                            )
                        }

                        <Link
                            className="btn btn-info me-2"
                            to="/orders"
                        >
                            Orders
                        </Link>

                        <Link
                            className="btn btn-warning"
                            to="/cart"
                        >
                            <i className="bi bi-cart"></i>
                            Cart
                        </Link>


                    </div>


                </div>

            </div>

        </nav>
    );
}


export default Navbar;
