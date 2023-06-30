import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../apis/login/authApi";
import { getData, setData } from "../../utils/SessionStorage";

const defaultState = {
  auth: null,
  token: null,
  isAuthenticated: false,
};

const initialState = getData("authLogin") ? getData("authLogin") : defaultState;

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout: (state) => {
      state = defaultState;
      // lưu vào trong sessionStorage
      setData("authLogin", state);
      return state;
    },

    updateUser: (state, action) => {
      state.auth = action.payload.auth;
      state.token = action.payload.token;
      state.isAuthenticated = action.payload.isAuthenticated;

      // Lưu vào sessionStorage
      setData("authLogin", state);
    },

  },

  // những tác động từ bên ngoài thay đổi state
  extraReducers: (builder) => {
    builder.addMatcher(
      authApi.endpoints.login.matchFulfilled,
      (state, action) => {
        state.auth = action.payload.auth;
        state.token = action.payload.token;
        state.isAuthenticated = action.payload.isAuthenticated;

        // lưu vào trong sessionStorage
        setData("authLogin", state);
      }
    );

    // Thêm matcher để xử lý hành động cập nhật người dùng
    builder.addMatcher(updateUser.match, (state, action) => {
      state.auth = action.payload.auth;
      state.token = action.payload.token;
      state.isAuthenticated = action.payload.isAuthenticated;

      // Lưu vào sessionStorage
      setData("authLogin", state);
    });
    
  },
});

export const { logout, updateUser } = authSlice.actions;

export default authSlice.reducer;