import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { fetchUserDeliveries } from "../features/deliveries/deliverySlice.js";
import { Link } from "react-router-dom";
import Loader from "../components/Loader.jsx";

export default function Dashboard() {
  const auth = useSelector(s => s.auth);
  const deliveries = useSelector(s => s.deliveries);
  const dispatch = useDispatch();

  useEffect(() => {
    if (auth.user && auth.user.id) dispatch(fetchUserDeliveries(auth.user.id));
  }, [auth.user, dispatch]);

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Your Deliveries</h1>
        <Link to="/create" className="bg-blue-600 text-white px-3 py-1 rounded">Create Delivery</Link>
      </div>

      {deliveries.loading && <Loader />}

      <div className="grid gap-4">
        {deliveries.list.length === 0 && !deliveries.loading && <div>No deliveries yet</div>}
        {deliveries.list.map(d => (
          <div key={d.id} className="bg-white p-4 rounded shadow">
            <div className="flex justify-between">
              <div>
                <div className="text-lg font-medium">{d.packageDescription}</div>
                <div className="text-sm text-gray-600">From: {d.pickupAddress}</div>
                <div className="text-sm text-gray-600">To: {d.dropAddress}</div>
              </div>
              <div className="text-right">
                <div className="text-sm">Status</div>
                <div className="font-semibold">{d.status}</div>
                <Link to={`/track/${d.id}`} className="mt-2 inline-block text-sm text-blue-600">Track</Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
