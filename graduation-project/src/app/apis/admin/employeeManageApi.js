import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/dist/query/react";

const END_POINT = "http://localhost:8080/admin/api/v1";

export const employeeManageApi = createApi ({
    reducerPath: "employeeManageApi",
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
    tagTypes: ['Employees'],
    endpoints: (builder) => ({
        findEmployeesAll: builder.query ({
            query: ({page, pageSize, term}) => `employees?page=${page}&pageSize=${pageSize}&term=${term}`,
            providesTags: ['Employees'],
        }),
        findEmployeeById: builder.query ({
            query: (id) => `employee/${id}`,
            providesTags: ['Employees'],
        }),
        findRolesAll: builder.query ({
            query: () => "roles",
            providesTags: ['Employees']
        }),
        createEmployee: builder.mutation ({
            query: (data) => ({
                url: "create/employee",
                method: "POST",
                body: data,
            }),
            invalidatesTags: ['Employees'],
        }),
        updateInformationEmployeeById: builder.mutation ({
            query: ({id,...data}) => ({
                url: `employee/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Employees'],
        }),
        updatePasswordAccEmployeeById: builder.mutation({
            query: ({id,...data}) => ({
                url: `employee/password/${id}`,
                method: "PUT",
                body: data,
            }),
            invalidatesTags: ['Employees'],
        }),
        deleteEmployeeById: builder.mutation ({
            query: (id) => ({
                url: `employee/${id}`,
                method: "DELETE"
            }),
            invalidatesTags: ['Employees'],
        }),
        enabledEmployeeById: builder.mutation ({
            query: (id) => ({
                url: `enabled-employee/${id}`,
                method: "PUT"
            }),
            invalidatesTags: ['Employees']
        }),
    }),
});;

export const { 
        useLazyFindEmployeesAllQuery,
        useFindEmployeeByIdQuery,
        useFindRolesAllQuery,
        useCreateEmployeeMutation,
        useUpdateInformationEmployeeByIdMutation,
        useUpdatePasswordAccEmployeeByIdMutation,
        useDeleteEmployeeByIdMutation,
        useEnabledEmployeeByIdMutation
} = employeeManageApi;