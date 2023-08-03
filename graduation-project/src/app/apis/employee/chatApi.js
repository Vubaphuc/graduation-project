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
        getMemberByRoomId: builder.query ({
            query: (id) => `room/${id}/members`,
            providesTags: ['Chat'],
        }),
        createRoom: builder.mutation ({
            query: (data) => ({
                url: "createRoom",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Chat'],
        }),
        addMemberToRoom: builder.mutation ({
            query: (data) => ({
                url: "room/members",
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Chat'],
        }),
        deleteRoomChat: builder.mutation ({
            query: (id) => ({
                url: `room/${id}`,
                method: "DELETE",
            }),
            invalidatesTags: ['Chat'],
        }),
        getRoomAll: builder.query ({
            query: () => "room",
            providesTags: ['Chat']
        }),
        getRoomByUserId: builder.query ({
            query: (id) => `room/members/${id}`,
            providesTags: ['Chat'],
        })
    }),
});;

export const { 
    useLazyGetMessageByRoomIdQuery,
    useGetMemberByRoomIdQuery,
    useLazyGetMemberByRoomIdQuery,
    useCreateRoomMutation,
    useAddMemberToRoomMutation,
    useDeleteRoomChatMutation,
    useGetRoomAllQuery,
    useLazyGetRoomByUserIdQuery,
    useGetRoomByUserIdQuery,
} = chatApi;