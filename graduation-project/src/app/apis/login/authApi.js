import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/public/api";

export const authApi = createApi ({
    reducerPath: "authApi",
    baseQuery: fetchBaseQuery ({ baseUrl: END_POINT }),
    tagTypes: ['Account'],
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({
                url: "login",
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Account'],
        }),     
    }),
});

export const {
    useLoginMutation,
} = authApi;