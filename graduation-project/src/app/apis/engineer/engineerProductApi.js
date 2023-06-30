import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/engineer/api/v1";

export const engineerProductApi = createApi({
    reducerPath: "engineerProductApi",
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
        getListProductNewByUser: builder.query ({
            query: ({page,pageSize,term}) => `products?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Engineer'],
        }),
        getProductNewById: builder.query ({
            query: (id) => `product/${id}`,
            providesTags: ['Engineer']
        }),
        updateInformationProductNewById: builder.mutation({
            query: ({id,...data}) => ({
                url: `update-product/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Engineer'],
        }),
        getListComponentPhone: builder.query({
            query: ({page,pageSize}) => `components?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Engineer'],
        }),
        findProductGuaranteeByUserAll: builder.query ({
            query: ({page,pageSize,term}) => `product-guarantee?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Engineer'],
        }),
        updateInformationProductGuaranteeById: builder.mutation ({
            query: ({id,...data}) => ({
                url: `update-product-guarantee/${id}`,
                method: "PUT",
                body: data
            }),
            invalidatesTags: ['Engineer'],
        }),
        findProductGuaranteeByID: builder.query ({
            query: (id) => `product-guarantee/${id}`,
            providesTags: ['Engineer'],
        }),
    }),
});

export const {
    useLazyGetListProductNewByUserQuery,
    useGetProductNewByIdQuery,
    useUpdateInformationProductNewByIdMutation,
    useLazyGetListComponentPhoneQuery,
    useLazyFindProductGuaranteeByUserAllQuery,
    useUpdateInformationProductGuaranteeByIdMutation,
    useFindProductGuaranteeByIDQuery
} = engineerProductApi;