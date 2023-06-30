export const getStatusLabel = (status) => {
    switch (status) {
        case "WAITING_FOR_REPAIR":
            return "Đang chờ sửa chữa";
        case "UNDER_REPAIR":
            return "Đang được sửa chữa";
        case "REPAIRED":
            return "Đã sửa chữa xong";
        case "WAITING_FOR_RETURN":
            return "Đang chờ trả khách";
        case "DELIVERED":
            return "Đã giao trả khách hàng";
        default:
            return "";
    }
};


export const getRepairLabel = (repair) => {
    return repair ? "Hàng Bảo Hành" : "Hàng Mới";
};


export const getRolesLabel = (role) => {
    switch (role) {
        case "NHANVIENLETAN":
            return "Nhân Viên Lễ Tân";
        case "NHANVIENKHO":
            return "Nhân Viên Kho";
        case "NHANVIENSUACHUA":
            return "Nhân Viên Sửa Chữa";
        case "NHANVIENBAOHANH":
            return "Nhân Viên Bảo Hành";
        default:
            return role;
    }
};