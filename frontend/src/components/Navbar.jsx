import { Link, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logout } from "../features/auth/authSlice.js";

export default function Navbar() {
  const auth = useSelector(s => s.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    navigate("/login");
  };

  return (
    <nav className="bg-white shadow">
      <div className="container mx-auto px-4 py-3 flex justify-between items-center">
        <div><Link className="text-xl font-bold" to="/">CourierX</Link></div>
        <div className="space-x-4">
          {auth.user ? (
            <>
              <Link className="px-3 py-1 rounded hover:bg-gray-100" to="/dashboard">Dashboard</Link>
              <Link className="px-3 py-1 rounded hover:bg-gray-100" to="/create">Create</Link>
              <button onClick={handleLogout} className="px-3 py-1 bg-red-500 text-white rounded">Logout</button>
            </>
          ) : (
            <>
              <Link className="px-3 py-1 rounded hover:bg-gray-100" to="/login">Login</Link>
              <Link className="px-3 py-1 rounded hover:bg-gray-100" to="/signup">Register</Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}
