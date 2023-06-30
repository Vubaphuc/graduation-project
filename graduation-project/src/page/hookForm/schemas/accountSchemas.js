import * as yup from "yup";

export const updatePersonalInformationSchema = yup.object ({
    fullName: yup.string().required("Họ và Tên không được để trống"),
    phone: yup.string()
    .required("Số Điện Thoại không được để trống")
    .matches(/(84|0[3|5|7|8|9])([0-9]{8})\b/, "Số Điện Thoại không hợp lệ"),
    address: yup.string().required("Địa chỉ không được để trống")
});

export const changePasswordSchema = yup.object ({
    oldPassword: yup.string().required("Password cũ không được để trống"),
    newPassword: yup.string()
        .required("Password mới không được để trống")
        .test('match', "Mật khẩu mới không được trùng mật khẩu cũ", function (value) {
            return value === this.parent.oldPassword ? false : true;
        }),
        confirmNewPassword: yup.string()
        .required("Password mới không được để trống")
        .oneOf([yup.ref('newPassword'), null], 'Mật khẩu mới không trùng khớp'),
});

export const forgotPasswordSchema = yup.object ({
    email: yup
    .string()
    .required("Email không được để trống")
    .matches(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
        "Email không hợp lệ"
    ),
    
});

export const loginSchema = yup.object ({
    email: yup
    .string()
    .required("Email không được để trống")
    .matches(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
        "Email không hợp lệ"
    ),
    password: yup.string().required("Password cũ không được để trống"),
});