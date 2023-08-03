import * as yup from "yup";


export const createRoomChatSchema = yup.object({
    roomName: yup.string().required("Họ và tên không được để trống"),
    membersIds: yup.array().test("isCategoryIdsRequired", "Danh mục không được để trống", (value) => {
        return value && value.length > 0 || typeof value === 'undefined';
    })
});