import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice.js";
import deliveryReducer from "../features/deliveries/deliverySlice.js";
import riderReducer from "../features/riders/riderSlice.js";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    deliveries: deliveryReducer,
    riders: riderReducer
  }
});
