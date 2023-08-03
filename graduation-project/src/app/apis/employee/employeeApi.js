import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react"

const END_POINT = "http://localhost:8080/employee/api/v1";

export const employeeApi = createApi ({
    reducerPath: "employeeApi",
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
    tagTypes: ['Employee'],
    endpoints: (builder) => ({     
        findEmployeeById: builder.query ({
            query: (id) => `employee/${id}`,
            providesTags:  ['Employee']
        }),
        findEngineerAll: builder.query ({
            query: () => "engineer",
            providesTags: ['Employee'],
        }),
        findReceptionistAll: builder.query ({
            query: () => "receptionist",
            providesTags: ['Employee'],
        }),
        findWarehouseEmployeeAll: builder.query ({
            query: () => "warehouse-employee",
            providesTags: ['Employee'],
        }),
        findReceptionistAndWarrantyEmployeeAll: builder.query ({
            query: () => "receive-pay",
            providesTags: ['Employee']
        }),   
        changePassword: builder.mutation ({
            query: (data) => ({
                url: "change-password",
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Account'],
        }),
        updatePersonalInformation: builder.mutation ({
            query: (data) => ({
                url: "update-information",
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Account'],
        }),
        getAvatar: builder.query ({
            query: (id) => `avatar/${id}`,
            providesTags: ['Account'],
        }),
        updateAvatar: builder.mutation ({
            query: (data) => ({
                url: "upload-avatar",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Account'],
        }),
        forgotPassword: builder.mutation ({
            query: (data) => ({
                url: "forgot-password",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Account'],
        }),   
        searchHistoryProduct: builder.query ({
            query: ({page, pageSize, startDate, endDate,term}) => `search?page=${page}&pageSize=${pageSize}&startDate=${startDate}&endDate=${endDate}&term=${term}`,
            providesTags: ['Account'],
        }),
        getAllUsers: builder.query ({
            query: () => "users",
            providesTags: ['Employee']
        })
    }),
});;

export const { 
    useFindEmployeeByIdQuery,
    useFindEngineerAllQuery,
    useFindReceptionistAllQuery,
    useFindWarehouseEmployeeAllQuery,
    useFindReceptionistAndWarrantyEmployeeAllQuery,
    useChangePasswordMutation,
    useUpdateAvatarMutation,
    useUpdatePersonalInformationMutation,
    useForgotPasswordMutation,
    useGetAvatarQuery,
    useLazySearchHistoryProductQuery,
    useGetAllUsersQuery,
} = employeeApi;