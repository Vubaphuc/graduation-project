import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/admin/api/v3";

export const productManageApi = createApi ({
    reducerPath: "productManageApi",
    baseQuery: fetchBaseQuery ({
        baseUrl: END_POINT,
        prepareHeaders: (headers, { getState }) => {
            const token = getState().auth.token;

            if (token) {
                headers.set("Authorization", `Bearer ${token}`);
            }
            return headers;
        },
    }),
    tagTypes: ['Product'],
    endpoints: (builder) => ({
        findStatisticsTotalProductToday: builder.query ({
            query: () => "products-total",
            providesTags: ['Product']
        }),
        findTotalProductByEngineerAll: builder.query ({
            query: () => "products-engineer",
            providesTags: ['Product']
        }),
        findTotalProductByEngineerYesterdayAll: builder.query ({
            query: () => "products-engineer-Yesterday",
            providesTags: ['Product']
        }),
        findProductOKAll: builder.query ({
            query: ({page, pageSize}) => `products-ok?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Product'], 
        }),
        findProductPendingAll: builder.query ({
            query: ({page, pageSize}) => `products-pending?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Product'], 
        }),
        findProductWaitingForRepairAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-for-reapir?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductUnderRepairAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-under-reapir?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductRepairedAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-repaired?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductWaitingForReturnAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-for-return?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductDeliveredAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-delivered?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `products?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }), 
        findProductByNameModelLimit: builder.query ({
            query: () => "product-limit",
            providesTags: ['Product'],
        }),   
        findCustomerByProductLimit: builder.query ({
            query: () => "customer-limit",
            providesTags: ['Product'],
        }),
        findTotalProductOkAndPending: builder.query ({
            query: () => "total-product",
            providesTags: ['Product'],
        }),
        findTotalProductByEngineer: builder.query ({
            query: () => "total-product-engineer",
            providesTags: ['Product'],
        }),
    }),

})

export const { 
   useFindStatisticsTotalProductTodayQuery,
   useFindTotalProductByEngineerAllQuery,
   useFindTotalProductByEngineerYesterdayAllQuery,
   useLazyFindProductOKAllQuery,
   useLazyFindProductPendingAllQuery,
   useLazyFindProductWaitingForRepairAllQuery,
   useLazyFindProductUnderRepairAllQuery,
   useLazyFindProductRepairedAllQuery,
   useLazyFindProductWaitingForReturnAllQuery,
   useLazyFindProductDeliveredAllQuery,
   useLazyFindProductAllQuery,
   useFindProductByNameModelLimitQuery,
   useFindCustomerByProductLimitQuery,
   useFindTotalProductOkAndPendingQuery,
   useFindTotalProductByEngineerQuery,
} = productManageApi;