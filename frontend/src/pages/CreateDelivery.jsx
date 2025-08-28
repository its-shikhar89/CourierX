import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createDelivery } from "../features/deliveries/deliverySlice.js";
import { useNavigate } from "react-router-dom";

export default function CreateDelivery() {
  const [form, setForm] = useState({ packageDescription: "", pickupAddress: "", dropAddress: "" });
  const auth = useSelector(s => s.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    if (!auth.user?.id) return alert("User not found");
    const res = await dispatch(createDelivery({ userId: auth.user.id, delivery: form }));
    if (res.meta.requestStatus === "fulfilled") navigate("/dashboard");
    else alert("Error creating delivery: " + JSON.stringify(res.payload));
  };

  return (
    <div className="max-w-2xl mx-auto bg-white p-6 shadow rounded">
      <h2 className="text-xl font-semibold mb-4">Create Delivery</h2>
      <form onSubmit={submit} className="space-y-3">
        <input value={form.packageDescription} onChange={e => setForm({ ...form, packageDescription: e.target.value })}
          className="w-full border px-3 py-2 rounded" placeholder="Package description" />
        <input value={form.pickupAddress} onChange={e => setForm({ ...form, pickupAddress: e.target.value })}
          className="w-full border px-3 py-2 rounded" placeholder="Pickup address" />
        <input value={form.dropAddress} onChange={e => setForm({ ...form, dropAddress: e.target.value })}
          className="w-full border px-3 py-2 rounded" placeholder="Drop address" />
        <button className="w-full bg-indigo-600 text-white py-2 rounded">Create</button>
      </form>
    </div>
  );
}
