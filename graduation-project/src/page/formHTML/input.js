export const getPart = (roles) => {
    if (!roles) {
        return [];
    }
    return roles.map((role) => {
        if (role.name === "NHANVIENLETAN") {
            return "Nhân Viên Lễ Tân";
        } else if (role.name === "NHANVIENKHO") {
            return "Nhân Viên Kho";
        } else if (role.name === "NHANVIENSUACHUA") {
            return "Nhân Viên Sửa Chữa";
        } else if (role.name === "NHANVIENBAOHANH") {
            return "Nhân Viên Bảo Hành";
        } else {
            return "";
        }
    });
}