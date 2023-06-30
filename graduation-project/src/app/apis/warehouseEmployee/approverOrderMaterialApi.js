import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/warehouse-employee/api/v2";

export const approverOrderMaterialApi = createApi ({
    reducerPath: "approverOrderMaterialApi",
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
    tagTypes: ['Warehouse'],
    endpoints: (builder) => ({
        getListOrderMaterialStatusFalse: builder.query ({
            query: ({page,pageSize}) => `order-pending?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Warehouse'],
        }),
        getListOrderMaterialStatusTrue: builder.query ({
            query: ({page,pageSize}) => `order-ok?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Warehouse'],
        }),
        getOrderMaterialById: builder.query ({
            query: (id) => `order-material/${id}`,
            providesTags: ['Warehouse'],
        }),
        approveOrderMaterial: builder.mutation ({
            query: ({id,...data}) => ({
                url: `app-order/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Warehouse'],
        }),
        searchOrderMaterialByTerm: builder.query ({
            query: ({page,pageSize, term}) => `search-term?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Warehouse'],
        }),
    }),
});;

export const { 
   useLazyGetListOrderMaterialStatusFalseQuery,
   useLazyGetListOrderMaterialStatusTrueQuery,
   useGetOrderMaterialByIdQuery,
   useApproveOrderMaterialMutation,
   useLazySearchOrderMaterialByTermQuery
} = approverOrderMaterialApi;