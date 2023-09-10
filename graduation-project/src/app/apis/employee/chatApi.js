import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react"

const END_POINT = "http://localhost:8080/chat/api/v1";

export const chatApi = createApi ({
    reducerPath: "chatApi",
    baseQuery: fetchBaseQuery ({
        baseUrl:END_POINT,
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token;

            if (token) {
                headers.set("Authorization", `Bearer ${token}`);
            }
            return headers;
        },
    }),
    tagTypes: ['Chat'],
    endpoints: (builder) => ({     
        getMessageByRoomId: builder.query ({
            query: (id) => `message/${id}`,
            providesTags: ['Chat']
        }),   
    }),
});;

export const { 
    useLazyGetMessageByRoomIdQuery,
} = chatApi;