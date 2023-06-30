import * as XLSX from "xlsx";

export const exportToCSV = (data, filename) => {
    // Chuyển đổi dữ liệu thành sheet Excel
    const worksheet = XLSX.utils.json_to_sheet(data);

    // Tạo workbook từ sheet dữ liệu
    const workbook = { Sheets: { data: worksheet }, SheetNames: ["data"] };

    // Ghi workbook thành buffer Excel
    const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" });

    // Tạo đối tượng Blob từ buffer Excel
    const blobData = new Blob([excelBuffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

    // Tạo URL đến đối tượng Blob
    const url = URL.createObjectURL(blobData);

    // Tạo phần tử <a> để tải xuống tệp tin
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", filename);

    // Thêm phần tử <a> vào body của tài liệu
    document.body.appendChild(link);

    // Kích hoạt sự kiện click trên phần tử <a> để tải xuống tệp tin
    link.click();
};

/*

const worksheet = XLSX.utils.json_to_sheet(data);: 
Dòng này sử dụng hàm json_to_sheet từ thư viện xlsx để chuyển đổi dữ liệu từ mảng JSON thành một sheet Excel.

const workbook = { Sheets: { data: worksheet }, SheetNames: ["data"] };: 
Dòng này tạo một workbook chứa sheet dữ liệu được tạo ở bước trước. Sheets là một đối tượng có thuộc tính data, đại diện cho sheet dữ liệu. SheetNames là một mảng chứa tên của các sheet trong workbook.

const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" });: 
Dòng này sử dụng hàm write từ thư viện xlsx để ghi workbook thành một buffer Excel. bookType: "xlsx" chỉ định loại file đầu ra là Excel (.xlsx), và type: "array" chỉ định kiểu dữ liệu đầu ra là một mảng.

const blobData = new Blob([excelBuffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });: 
Dòng này tạo một đối tượng Blob từ buffer Excel đã tạo. Blob là một đối tượng JavaScript đại diện cho dữ liệu nhị phân không được cấu trúc.

const url = URL.createObjectURL(blobData);: 
Dòng này tạo một URL đến đối tượng Blob, cho phép truy cập vào nội dung của nó.

const link = document.createElement("a");: 
Dòng này tạo một phần tử <a> (anchor element) trong DOM.

link.href = url;: 
Dòng này đặt thuộc tính href của phần tử <a> để trỏ đến URL đã tạo ở bước trước.

link.setAttribute("download", filename);: 
Dòng này đặt thuộc tính download của phần tử <a> để chỉ định tên tệp tin khi tải xuống.

document.body.appendChild(link);: 
Dòng này thêm phần tử <a> vào phần tử <body> của tài liệu.

link.click();: 
Dòng này kích hoạt sự kiện click trên phần tử <a>, giúp tệp tin được tải xuống.


*/