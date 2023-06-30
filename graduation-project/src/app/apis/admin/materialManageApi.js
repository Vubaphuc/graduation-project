import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/admin/api/v2";

export const materialManageApi = createApi ({
    reducerPath: "materialManageApi",
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
    tagTypes: ['Materials'],
    endpoints: (builder) => ({
        findOrderMaterialsAll: builder.query ({
            query: ({page, pageSize, startDate, endDate}) => `order-materials?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}`,
            providesTags: ['Materials'],
        }),
        totalPriceAndQuantityMaterial: builder.query ({
            query: () => "total-material",
            providesTags: ['Product'],
        }),
        findMaterialsAll: builder.query ({
            query: ({page, pageSize, startDate, endDate,term}) => `materials?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}&term=${term}`,
            providesTags: ['Materials'],
        }),
        findListTotalQuantityExportMaterialByMaterialCode: builder.query ({
            query: ({page,pageSize}) => `total-materials?page=${page}&pageSize=${pageSize}`,
            providesTags: ['Materials'],
        }),
        findOrderMaterialLimit: builder.query ({
            query: () => "order-materials-limit",
            providesTags: ['Product'], 
        }),
        findMaterialRemainingQuantityLimit: builder.query ({
            query: () => "materials-remaining-quantity",
            providesTags: ['Materials']
        })
        
    }),
});;

export const { 
    useLazyFindOrderMaterialsAllQuery,
    useTotalPriceAndQuantityMaterialQuery,
    useLazyFindMaterialsAllQuery,
    useLazyFindListTotalQuantityExportMaterialByMaterialCodeQuery,
    useLazyFindOrderMaterialLimitQuery,
    useFindMaterialRemainingQuantityLimitQuery,
} = materialManageApi;