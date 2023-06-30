// Lưu dữ liệu vào trong sessionStorage
export const setData = (key, value) => {
    sessionStorage.setItem(key, JSON.stringify(value));
}

// Lấy dữ liệu từ trong sessionStorage
export const getData = (key) => {
    const sessionStorageValue = sessionStorage.getItem(key);
    if(sessionStorageValue) {
        return JSON.parse(sessionStorageValue);
    }
    return null;
}