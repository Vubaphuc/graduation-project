import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/engineer/api/v2";

export const engineerOrderMaterialApi = createApi({
    reducerPath: "engineerOrderMaterialApi",
    baseQuery: fetchBaseQuery({
        baseUrl: END_POINT,
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token;

            if (token) {
                headers.set("Authorization", `Bearer ${token}`);
            }
            return headers;
        },
    }),
    tagTypes: ['Engineer'],
    endpoints: (builder) => ({
        getListMaterialByQuantity: builder.query ({
            query: ({page,pageSize}) => `materialies?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Engineer']
        }),
        getMaterialById: builder.query ({
            query: (id) => `material/${id}`,
            providesTags: ['Engineer']
        }),
        CreateOrderMaterial: builder.mutation ({
            query: (data) => ({
                url: "order",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Engineer']
        }),
        getOrderMaterialById: builder.query ({
            query: (id) => `order/${id}`,
            providesTags: ['Engineer'],
        }),
        getListOrderMaterialByStatusTrue: builder.query ({
            query: ({page, pageSize}) => `order/status-true?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Engineer'],
        }),
        getListOrderMaterialByStatusFalse: builder.query ({
            query: ({page, pageSize}) => `order/status-false?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Engineer'],
        }),
        updateQuantityOrderMaterialById: builder.mutation ({
            query: ({id,...data}) => ({
                url: `order/${id}`,
                method: "PUT",
                body: data
            }),
            invalidatesTags: ['Engineer'],
        }),
        deleteOrderById: builder.mutation ({
            query: (id) => ({
                url: `order/${id}`,
                method: "DELETE",
            }),
            invalidatesTags: ['Engineer']
        })
    }),
});

export const {
    useLazyGetListMaterialByQuantityQuery,
    useGetMaterialByIdQuery,
    useCreateOrderMaterialMutation,
    useGetOrderMaterialByIdQuery,
    useLazyGetListOrderMaterialByStatusTrueQuery,
    useLazyGetListOrderMaterialByStatusFalseQuery,
    useUpdateQuantityOrderMaterialByIdMutation,
    useDeleteOrderByIdMutation
} = engineerOrderMaterialApi;