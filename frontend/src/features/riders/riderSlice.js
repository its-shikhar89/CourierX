import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../services/api";

export const fetchAllRiders = createAsyncThunk("riders/fetchAll",
  async (_, { rejectWithValue }) => {
    try {
      const res = await api.get("/rider-service/riders");
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

const slice = createSlice({
  name: "riders",
  initialState: { list: [], loading: false, error: null },
  reducers: {},
  extraReducers: (b) => {
    b
      .addCase(fetchAllRiders.pending, (s) => { s.loading = true; s.error = null; })
      .addCase(fetchAllRiders.fulfilled, (s, a) => { s.loading = false; s.list = a.payload; })
      .addCase(fetchAllRiders.rejected, (s, a) => { s.loading = false; s.error = a.payload; });
  }
});

export default slice.reducer;
