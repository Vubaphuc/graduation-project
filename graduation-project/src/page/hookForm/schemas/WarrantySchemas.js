import * as yup from "yup";

export const registerProductGuaranteeSchema = yup.object({
    id: yup
    .number()
    .required("ID sản phẩm không được để trống")
    .integer("ID sản phẩm phải không được là chuỗi ký tự"),
    defectName: yup.string().required("Mô tả lỗi không được để trống"),
    cause: yup.string().required("Nguyên nhân không được để trống"),
    price: yup
    .number()
    .required("Số tiền không được để trống")
    .integer("Số tiền không được là chuỗi ký tự")
    .min(0, "Số tiền không được nhập số âm"),
});

export const changeEngineerSchema = yup.object({
    id: yup
    .number()
    .required("ID sản phẩm không được để trống")
    .integer("ID sản phẩm phải không được là chuỗi ký tự"),
    employeeCode: yup.string().required("Nhân Viên sữa chữa không được để trống"),
  });