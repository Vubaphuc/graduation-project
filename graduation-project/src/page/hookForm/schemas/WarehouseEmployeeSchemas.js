import * as yup from "yup";

export const materialCreateSchema = yup.object({
    materialCode: yup.string().required("Mã vật liệu không được để trống"),
    nameModel: yup.string().required("Tên Model không được để trống"),
    venderId: yup.number().required("Vender không được để trống"),
    componentsId: yup.number().required("Loại linh kiện không được để trống"),
    quantity: yup
        .number()
        .required("số lượng không được để trống")
        .integer("số lượng phải là số nguyên")
        .positive("số lượng phải lớn hơn 0"),
    price: yup
        .number()
        .required("số tiền không được để trống")
        .integer("số tiền phải là số nguyên")
        .positive("Số tiền phải lớn hơn 0"),
});

export const vendorCreateSchema = yup.object({
    name: yup.string().required("Tên Vender không được để trống"),
});

export const componentCreateSchema = yup.object({
    name: yup.string().required("Tên linh kiện không được để trống"),
    warrantyPeriod: yup
        .number()
        .required("thời gian không được để trống")
        .integer("thời gian tính theo tháng"),
});

export const updateComponentSchema = yup.object({
    id: yup
    .number()
    .required("Mã linh kiện không được để trống")
    .integer("Mã linh kiện phải không được là chuỗi ký tự"),
    name: yup.string().required("Tên linh kiện không được để trống"),
    warrantyPeriod: yup
        .number()
        .required("thời gian không được để trống")
        .integer("thời gian tính theo tháng"),
})

export const updateMaterialSchema = yup.object({
    quantity: yup
        .number()
        .required("số lượng không được để trống")
        .integer("số lượng phải là số nguyên")
        .positive("số lượng phải lớn hơn 0"),
});

export const approverOrderMaterialSchema = yup.object({
    materialCode: yup.string().required("Mã Vật Liệu không được để trống"),
    quantity: yup
        .number()
        .required("số lượng không được để trống")
        .integer("số lượng phải là số nguyên"),
    status: yup.boolean().test(
        "is-true",
        "Trạng thái phải là OK",
        value => value === true
    ).required("Trạng thái không được trống")
});