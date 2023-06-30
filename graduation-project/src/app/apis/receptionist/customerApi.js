import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/receptionist/api/v1";

export const customerApi = createApi ({
    reducerPath: "customerApi",
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
    tagTypes: ['Receptionist'],
    endpoints: (builder) => ({
        createCustomer: builder.mutation ({
            query: (data) => ({
                url: "create-customer",
                method: "POST",
                body: data
            }),
            invalidatesTags: ['Receptionist']
        }),
        getCustomerById: builder.query ({
            query: (id) => `customer/${id}`,
            providesTags: ['Receptionist']
        }),
        getListProductByCustomerId: builder.query ({
            query: ({id,page,pageSize}) => `products?page=${page}&pageSize=${pageSize}&id=${id}`,
            providesTags: ['Receptionist'],
        }),
        searchProductByCustomer: builder.query ({
            query: ({page,pageSize,term}) => `search/product?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist']
        }),
        updateCustomerById: builder.mutation ({
            query: ({id,...data}) => ({
                url: `customer/${id}`,
                method: "PUT",
                body: data
            }),
            invalidatesTags: ['Receptionist']
        }),
        deleteCustomerById: builder.mutation ({
            query: (id) => ({
                url: `customer/${id}`,
                method: "DELETE",
            }),
            invalidatesTags: ['Receptionist'],
        }),

    }),
});;

export const { 
   useCreateCustomerMutation,
   useGetCustomerByIdQuery,
   useLazyGetListProductByCustomerIdQuery,
   useLazySearchProductByCustomerQuery,
   useUpdateCustomerByIdMutation,
   useDeleteCustomerByIdMutation,
} = customerApi;