import { useState } from "react";
import { loginUser } from "../services/authService";
import { useNavigate } from "react-router-dom";


function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();


    const handleLogin = async (e) => {

        e.preventDefault();

        try {
            console.log("EMAIL:", email);
            console.log("PASSWORD:", password);
            const response = await loginUser({
                email,
                password
            });


            // Store logged-in user details
            localStorage.setItem(
                "user",
                JSON.stringify(response.data)
            );


            alert("Login Successful");


            // redirect to products page
            navigate("/products");


        } catch(error) {

              console.error("Login failed", error.response || error.message);

              alert(
                  error.response?.data?.message ||
                  "Login request failed"
              );

          }

    };


    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-5">

                    <div className="card shadow">

                        <div className="card-body">

                            <h2 className="text-center mb-4">
                                Login
                            </h2>


                            <form onSubmit={handleLogin}>


                                <input

                                    type="email"

                                    className="form-control mb-3"

                                    placeholder="Email"

                                    value={email}

                                    onChange={(e)=>
                                        setEmail(e.target.value)
                                    }

                                    required

                                />


                                <input

                                    type="password"

                                    className="form-control mb-3"

                                    placeholder="Password"

                                    value={password}

                                    onChange={(e)=>
                                        setPassword(e.target.value)
                                    }

                                    required

                                />


                                <button
                                    type="submit"
                                    className="btn btn-primary w-100"
                                >

                                    Login

                                </button>


                            </form>


                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}


export default Login;