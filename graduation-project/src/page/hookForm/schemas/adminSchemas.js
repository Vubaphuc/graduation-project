import * as yup from "yup";


export const newCreateEmployeeSchema = yup.object ({
    fullName: yup.string().required("Họ và tên không được để trống"),
    email: yup
    .string()
    .required("Email không được để trống")
    .matches(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
        "Email không hợp lệ"
    ),
    phoneNumber: yup.string()
    .required("Số Điện Thoại không được để trống")
    .matches(/(84|0[3|5|7|8|9])([0-9]{8})\b/, "Số Điện Thoại không hợp lệ"),
    password: yup.string().required("Mật khẩu không được để trống"),
    address: yup.string().required("Địa chỉ không được để trống"),
    roleIds: yup.array().min(1, "Vui lòng chọn ít nhất một role"),

});

export const updateInformationSchema = yup.object({
    id: yup.number().required("Id nhân viên không được để trống"),
    fullName: yup.string().required("Họ và tên không được để trống"),
    email: yup
    .string()
    .required("Email không được để trống")
    .matches(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
        "Email không hợp lệ"
    ),
    phone: yup.string()
    .required("Số Điện Thoại không được để trống")
    .matches(/(84|0[3|5|7|8|9])([0-9]{8})\b/, "Số Điện Thoại không hợp lệ"),
    roleIds: yup.array().test("isCategoryIdsRequired", "Danh mục không được để trống", (value) => {
        return value && value.length > 0 || typeof value === 'undefined';
      }),
    address: yup.string().required("Địa chỉ không được để trống"),

});

export const updatePasswordSchema = yup.object({
    password: yup.string().required("Mật khẩu không được để trống"),
});