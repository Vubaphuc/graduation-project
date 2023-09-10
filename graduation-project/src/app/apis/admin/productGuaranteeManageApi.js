import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/admin/api/v4";

export const productGuaranteeManageApi = createApi ({
    reducerPath: "productGuaranteeManageApi",
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
        findProductGuaranteeWaitingForRepairAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-for-reapir?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductGuaranteeUnderRepairAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-under-reapir?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductGuaranteeRepairedAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-repaired?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductGuaranteeWaitingForReturnAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-for-return?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findProductGuaranteeDeliveredAll: builder.query ({
            query: ({page,pageSize,startDate,endDate}) => `product-delivered?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Product'], 
        }),
        findTotalProductGuaranteeByEngineer: builder.query({
           query: () => "total-product-guarantee-engineer",
           providesTags: ['Product'],
        }),
    }),

})

export const { 
   useLazyFindProductGuaranteeWaitingForRepairAllQuery,
   useLazyFindProductGuaranteeUnderRepairAllQuery,
   useLazyFindProductGuaranteeRepairedAllQuery,
   useLazyFindProductGuaranteeWaitingForReturnAllQuery,
   useLazyFindProductGuaranteeDeliveredAllQuery,
   useFindTotalProductGuaranteeByEngineerQuery
} = productGuaranteeManageApi;