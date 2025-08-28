import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../services/api";

export const fetchUserDeliveries = createAsyncThunk(
  "deliveries/fetchUserDeliveries",
  async (userId, { rejectWithValue }) => {
    try {
      const res = await api.get(`/tracking/deliveries/user/${userId}`);
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

export const createDelivery = createAsyncThunk(
  "deliveries/createDelivery",
  async ({ userId, delivery }, { rejectWithValue }) => {
    try {
      const res = await api.post(`/tracking/deliveries?userId=${userId}`, delivery);
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

export const getDeliveryLocation = createAsyncThunk(
  "deliveries/getDeliveryLocation",
  async (deliveryId, { rejectWithValue }) => {
    try {
      const res = await api.get(`/tracking/deliveries/${deliveryId}/location`);
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

const slice = createSlice({
  name: "deliveries",
  initialState: { list: [], currentLocation: null, loading: false, error: null },
  reducers: {},
  extraReducers: (b) => {
    b
      .addCase(fetchUserDeliveries.pending, (s) => { s.loading = true; s.error = null; })
      .addCase(fetchUserDeliveries.fulfilled, (s, a) => { s.loading = false; s.list = a.payload; })
      .addCase(fetchUserDeliveries.rejected, (s, a) => { s.loading = false; s.error = a.payload; })

      .addCase(createDelivery.pending, (s) => { s.loading = true; s.error = null; })
      .addCase(createDelivery.fulfilled, (s, a) => { s.loading = false; s.list.push(a.payload); })
      .addCase(createDelivery.rejected, (s, a) => { s.loading = false; s.error = a.payload; })

      .addCase(getDeliveryLocation.pending, (s) => { s.loading = true; s.error = null; })
      .addCase(getDeliveryLocation.fulfilled, (s, a) => { s.loading = false; s.currentLocation = a.payload; })
      .addCase(getDeliveryLocation.rejected, (s, a) => { s.loading = false; s.error = a.payload; });
  }
});

export default slice.reducer;
