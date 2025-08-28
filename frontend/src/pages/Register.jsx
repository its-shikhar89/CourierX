import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { registerUser } from "../features/auth/authSlice.js";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [form, setForm] = useState({ username: "", password: "", role: "USER" });
  const dispatch = useDispatch();
  const auth = useSelector(s => s.auth);
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    const res = await dispatch(registerUser(form));
    if (res.meta.requestStatus === "fulfilled") navigate("/login");
  };

  return (
    <div className="max-w-md mx-auto bg-white p-6 shadow rounded">
      <h2 className="text-2xl font-semibold mb-4">Register</h2>
      {auth.error && <div className="text-sm text-red-600 mb-2">{String(auth.error)}</div>}
      <form onSubmit={submit} className="space-y-3">
        <input value={form.username} onChange={e => setForm({ ...form, username: e.target.value })}
          className="w-full border px-3 py-2 rounded" placeholder="Username" />
        <input value={form.password} onChange={e => setForm({ ...form, password: e.target.value })}
          type="password" className="w-full border px-3 py-2 rounded" placeholder="Password" />
        <select value={form.role} onChange={e => setForm({ ...form, role: e.target.value })}
          className="w-full border px-3 py-2 rounded">
          <option value="USER">USER</option>
          <option value="RIDER">RIDER</option>
          <option value="ADMIN">ADMIN</option>
        </select>
        <button className="w-full bg-green-600 text-white py-2 rounded">{auth.loading ? "Registering..." : "Register"}</button>
      </form>
    </div>
  );
}
