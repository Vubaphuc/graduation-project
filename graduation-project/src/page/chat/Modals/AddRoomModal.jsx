import { Avatar, Form, Input, Modal, Select } from "antd";
import React from "react";
import { useState } from "react";
import { useGetAllUsersQuery } from "../../../app/apis/employee/employeeApi";
import { useContext } from "react";
import { ModalContext } from "../Context/ModalProvider";
import { Option } from "antd/es/mentions";
import { useCreateRoomMutation } from "../../../app/apis/employee/chatApi";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";
import Item from "antd/es/list/Item";

function AddRoomModal() {
    const { auth } = useSelector((state) => state.auth);
    const [selectedOption, setSelectedOption] = useState(null);
    const [searchValue, setSearchValue] = useState('');
    const [form] = Form.useForm();
    const { isAddRoomVisible, setIsAddRoomVisible } = useContext(ModalContext);

    const { data: userData, isLoading } = useGetAllUsersQuery();
    const [createRoom] = useCreateRoomMutation();

    if (isLoading) {
        return <h2>Loading...</h2>
    }

    const handleOk = () => {

        if (!selectedOption || selectedOption.length === 0) {
            // Hiển thị thông báo yêu cầu người dùng chọn ít nhất một thành viên
            toast.error("Vui lòng chọn ít nhất một thành viên để mời vào phòng chat.");
            return;
        }

        const newData = {roomName:  form.getFieldsValue().name , membersIds: selectedOption}

        createRoom(newData)
        .then(() => {
            toast.success("Thành Công")
        })
        .catch((err) => {
            toast.error(err.data.message);
        })

        form.resetFields();
        setIsAddRoomVisible(false);
    }

    const handleCancel = () => {

        form.resetFields();
        setIsAddRoomVisible(false);
    }


    const handleSelectChange = (value) => {
        setSelectedOption(value);
    };


    const handleSearch = (value) => {
        setSearchValue(value);
    };

    const data = userData.map((user) => ({
        id: user.id,
        name: user.employeeName,
        avatarUrl: `http://localhost:8080/employee/api/v1/avatar/${user.id}`,
    }))

    const filteredOptions = data.filter(option =>
        option.name.toLowerCase().includes(searchValue.toLowerCase()) && option.name !== auth?.employeeName
    );

    return (
        <div>
            <Modal
                title='Tạo phòng'
                open={isAddRoomVisible}
                onOk={handleOk}
                onCancel={handleCancel}
                destroyOnClose={true}
                closeIcon={false}
            >
                <Form form={form} layout="vertical">
                    <Item label="Tên Phòng" name="name" className="mb-4 mt-4">
                        <Input placeholder="Nhập Tên Phòng" />
                    </Item>
                    <Select
                        showSearch
                        style={{ width: '100%' }}
                        placeholder="Chọn một tùy chọn"
                        optionFilterProp="children"
                        mode="multiple"
                        onChange={handleSelectChange}
                        onSearch={handleSearch}
                        filterOption={false}
                        className="mb-4"
                    >
                        {searchValue && filteredOptions.map((option) => (
                            <Option key={option.id} value={option.id}>
                                <Avatar size={"small"} src={option.avatarUrl} style={{ marginRight: '5px' }} />
                                {option.name}
                            </Option>
                        ))}
                    </Select>
                </Form>
            </Modal>
        </div>
    )
}

export default AddRoomModal;