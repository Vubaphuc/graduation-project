import * as yup from "yup";

export const updateInformationProductRepair = yup.object({
  location: yup.string().required("Nguyên Nhân không được để trống"),
  componentsId: yup.number().required("Vị trí sửa không được để trống"),
  status: yup
    .boolean()
    .test("is-true", "Trạng thái phải là OK", (value) => value === true)
    .required("Trạng thái không được trống"),
});

export const orderMaterialCreateSchema = yup.object({
  quantity: yup
    .number()
    .positive("Số lượng phải lớn hơn 0")
    .integer("số lượng phải là số nguyên")
    .required("số lượng không được để trống"),
});