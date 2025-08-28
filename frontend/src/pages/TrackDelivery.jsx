import { useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getDeliveryLocation } from "../features/deliveries/deliverySlice.js";
import Loader from "../components/Loader.jsx";

export default function TrackDelivery() {
  const { deliveryId } = useParams();
  const dispatch = useDispatch();
  const deliveries = useSelector(s => s.deliveries);
  const intervalRef = useRef(null);

  useEffect(() => {
    // initial fetch
    dispatch(getDeliveryLocation(deliveryId));

    // poll every 5s (replace with WebSocket in Week 8)
    intervalRef.current = setInterval(() => {
      dispatch(getDeliveryLocation(deliveryId));
    }, 5000);

    return () => clearInterval(intervalRef.current);
  }, [deliveryId, dispatch]);

  const loc = deliveries.currentLocation;

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Track Delivery #{deliveryId}</h2>
      {deliveries.loading && <Loader />}

      {!loc && !deliveries.loading && <div className="bg-white p-4 rounded shadow">No location available yet.</div>}

      {loc && (
        <div className="bg-white p-4 rounded shadow">
          <div className="mb-2">Live Location Data:</div>
          <pre className="text-sm bg-gray-100 p-3 rounded overflow-auto">{JSON.stringify(loc, null, 2)}</pre>

          {loc.latitude && loc.longitude && (
            <div className="mt-3">
              <div>Latitude: {loc.latitude}</div>
              <div>Longitude: {loc.longitude}</div>
              <div className="text-sm text-gray-600 mt-2">(Map view integration planned in Week 8.)</div>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
