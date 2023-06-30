import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/warranty-employee/api/v1";

export const warrantyProductApi = createApi({
    reducerPath: "warrantyProductApi",
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
    tagTypes: ['Warranty'],
    endpoints: (builder) => ({
        findProductDeliveredAll: builder.query ({
            query: ({page,pageSize,term}) => `product-delivered?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Warranty']
        }),
        findProductDeliveredByID: builder.query ({
            query: (id) => `product-delivered/${id}`,
            providesTags: ['Warranty'],
        }),
        createProductGuarantee: builder.mutation ({
            query: (data) => ({
                url: "product-guarantee/create",
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Warranty'],
        }),
        findProductGuaranteeWaitingForRepairAll: builder.query ({
            query: ({page,pageSize, term}) => `product-guarantee?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Warranty'],
        }),
        findProductGuaranteeByID: builder.query ({
            query: (id) => `product-guarantee/${id}`,
            providesTags: ['Warranty'],
        }),
        registerEngineerInformationByProductGuarantee: builder.mutation ({
            query: ({id,...data}) => ({
                url: `register-engineer/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Warranty'],
        }),
        findProductGuaranteeRepairedAll: builder.query ({
            query: ({page, pageSize, term}) => `product-guarantee-repaired?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Warranty'],
        }),
        createNewGuarantee: builder.mutation({
            query: (data) => ({
                url: "guarantee-create",
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Warranty'],
        }),
        createBill: builder.mutation({
            query: (data) => ({
                url: "create-bill",
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Warranty'],
        }),
        findProductGuaranteeWaitingForReturnAll: builder.query({
            query: ({page, pageSize, term}) => `product-guarantee-return?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Warranty'],
        }),
    }),
});

export const {
    useLazyFindProductDeliveredAllQuery,
    useFindProductDeliveredByIDQuery,
    useCreateProductGuaranteeMutation,
    useLazyFindProductGuaranteeWaitingForRepairAllQuery,
    useFindProductGuaranteeByIDQuery,
    useRegisterEngineerInformationByProductGuaranteeMutation,
    useLazyFindProductGuaranteeRepairedAllQuery,
    useCreateBillMutation,
    useCreateNewGuaranteeMutation,
    useLazyFindProductGuaranteeWaitingForReturnAllQuery,
} = warrantyProductApi;