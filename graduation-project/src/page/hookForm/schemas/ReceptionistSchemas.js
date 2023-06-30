import * as yup from "yup";

export const createCustomerSchema = yup.object({
  customerName: yup.string().required("Họ và Tên không được để trống"),
  phoneNumber: yup
    .string()
    .required("Số Điện Thoại không được để trống")
    .matches(/^(84|0)([3|5|7|8|9])([0-9]{8})$/, "Số Điện Thoại không hợp lệ"),
  customerEmail: yup
    .string()
    .required("Email không được để trống")
    .matches(
      /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
      "Email không hợp lệ"
    ),
  customerAddress: yup.string().required("Địa chỉ không được để trống"),
});

export const updateCustomerSchema = yup.object({
    id: yup
    .number()
    .positive("ID Khách Hàng phải là số nguyên dương")
    .required("ID Khách Hàng không được để trống")
    .integer("ID Khách Hàng phải không được là chuỗi ký tự"),
    customerName: yup.string().required("Họ và Tên không được để trống"),
    phoneNumber: yup
      .string()
      .required("Số Điện Thoại không được để trống")
      .matches(/^(84|0)([3|5|7|8|9])([0-9]{8})$/, "Số Điện Thoại không hợp lệ"),
    customerEmail: yup
      .string()
      .required("Email không được để trống")
      .matches(
        /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
        "Email không hợp lệ"
      ),
    customerAddress: yup.string().required("Địa chỉ không được để trống"),
  });

export const createBillSchema = yup.object({
  productId: yup
    .number()
    .required("Mã sản phẩm không được để trống")
    .integer("Mã sản phẩm phải không được là chuỗi ký tự"),
});

export const createProductSchema = yup.object().shape({
  customerId: yup
    .number()
    .required("số lượng không được để trống")
    .integer("số lượng phải là số nguyên"),
  phoneCompany: yup.string().required("Hãng sản xuất không được để trống"),
  model: yup.string().required("Dòng máy không được để trống"),
  ime: yup.string().required("Số IME không được để trống"),
  defectName: yup.string().required("Mô tả lỗi không được để trống"),
  price: yup
    .number()
    .required("Tiền không được để trống")
    .integer("Tiên phải không được là chuỗi ký tự")
    .positive("Số Tiền Phải lớn hơn 0"),
});

export const registerInformationEngineerSchema = yup.object({
  employeeCode: yup.string().required("Nhân Viên sữa chữa không được để trống"),
});

export const guaranteeCreateSchema = yup.object({
  productId: yup
    .number()
    .required("Mã sản phẩm không được để trống")
    .integer("Mã sản phẩm phải không được là chuỗi ký tự"),
});