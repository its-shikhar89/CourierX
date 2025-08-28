import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

export default function ProtectedRoute(){
  const auth = useSelector(s => s.auth);
  if (!auth.user && !auth.token) return <Navigate to="/login" replace />;
  return <Outlet />;
}
