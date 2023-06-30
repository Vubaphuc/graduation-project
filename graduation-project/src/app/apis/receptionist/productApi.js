import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/receptionist/api/v2";

export const productApi = createApi ({
    reducerPath: "productApi",
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
        // danh sách sản phẩm ok chờ trả khách
        findProductWaitingReturnCustomerAll: builder.query ({
            query: ({page,pageSize,term}) => `products-return-customer?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist'],
        }),
        // danh sách sản phẩm đã sửa xong chờ đăng ký bảo hành
        findProductRepairedAll: builder.query ({
            query: ({page,pageSize,term}) => `product-repaired?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist'],
        }),
        // danh sách sản phẩm chờ chuyển cho người sửa chữa
        findProductWaitingForRepairAll: builder.query ({
            query: ({page,pageSize,term}) => `product-waiting-repair?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist'],
        }),
        // sản phẩm theo id
        findProductByID: builder.query ({
            query: (id) => `product/${id}`,
            providesTags: ['Receptionist']
        }),
        registerEngineerInformationByProduct: builder.mutation ({
            query: ({id,...data}) => ({
                url: `register-engineer/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Receptionist'],
        }),
        findProductUnderRepairAll: builder.query ({
            query: ({page,pageSize,term}) => `product-under-repair?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist'],
        }),
        createProduct: builder.mutation ({
            query: (data) => ({
                url: "create-product",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Receptionist'],
        }),
        findProductPendingAll: builder.query ({
            query: ({page,pageSize,term}) => `product-pending?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Receptionist'],
        }),
        deletePorductBtID: builder.mutation ({
            query: (id) => ({
                url: `product/${id}`,
                method: "DELETE",
            }),
            invalidatesTags: ['Receptionist'],
        }),
        createNewGuarantee: builder.mutation ({
            query: (data) => ({
                url: "guarantee-create",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Receptionist'],
        }),
        createBill: builder.mutation ({
            query: (data) => ({
                url: "create-bill",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Receptionist'],
        }),
       
    }),
});;

export const { 
    useLazyFindProductWaitingReturnCustomerAllQuery,
    useLazyFindProductRepairedAllQuery,
    useLazyFindProductWaitingForRepairAllQuery,
    useFindProductByIDQuery,
    useRegisterEngineerInformationByProductMutation,
    useLazyFindProductUnderRepairAllQuery,
    useCreateProductMutation,
    useLazyFindProductPendingAllQuery,
    useDeletePorductBtIDMutation,
    useCreateBillMutation,
    useCreateNewGuaranteeMutation,
} = productApi;