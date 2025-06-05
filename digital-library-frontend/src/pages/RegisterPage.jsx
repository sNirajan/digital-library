import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

function RegisterPage() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/users/register", {
        name,
        email,
        password,
      });

      alert(" Registration successful");
      navigate("/login");
    } catch (err) {
      alert(" Registration failed: " + err.response?.data || "Server error");
    }
  };

  return (
    <>
      <Navbar />
      
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
          <form
            onSubmit={handleRegister}
            className="bg-white p-6 rounded shadow-md w-full max-w-md"
          >
            <h2 className="text-2xl font-bold mb-4 text-center">Register</h2>

            <input
              type="text"
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="w-full mb-4 p-2 border rounded"
              required
            />

            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full mb-4 p-2 border rounded"
              required
            />

            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full mb-4 p-2 border rounded"
              required
            />

            <button
              type="submit"
              className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded"
            >
              Register
            </button>
          </form>
        </div>
      
    </>
  );
}

export default RegisterPage;
