import { Avatar, Form, Modal, Select, Spin } from "antd";
import React from "react";
import { useContext } from "react";
import { useState } from "react";
import { ModalContext } from "../Context/ModalProvider";
import { useGetAllUsersQuery } from "../../../app/apis/employee/employeeApi";
import { useAddMemberToRoomMutation, useLazyGetMemberByRoomIdQuery } from "../../../app/apis/employee/chatApi";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";
import { useSocket } from "../../socket/useSocket";
import { Option } from "antd/es/mentions";
import { useEffect } from "react";


export default function InviteMemberModal() {
    const { isInviteMemberVisible, setIsInviteMemberVisible, setSelectedMember, selectedRoomId, selectedUserId } = useContext(ModalContext);
    const [form] = Form.useForm();
    const [selectedOption, setSelectedOption] = useState(null);
    const [searchValue, setSearchValue] = useState('');
    const { auth } = useSelector((state) => state.auth);

    const { data: userData, isLoading } = useGetAllUsersQuery();
    const [getMember, { data: memberData }] = useLazyGetMemberByRoomIdQuery();
    const [addMember] = useAddMemberToRoomMutation();


    useEffect(() => {
        if(selectedRoomId !== null) {
            getMember(selectedRoomId)
        } 
    }, [selectedRoomId])

    if (isLoading) {
        return <h2>Loading...</h2>
    }

    const handleOk = () => {

        if (!selectedOption || selectedOption.length === 0) {
            // Hiển thị thông báo yêu cầu người dùng chọn ít nhất một thành viên
            toast.error("Vui lòng chọn ít nhất một thành viên để mời vào phòng chat.");
            return;
        }

        const newData = { roomId: selectedRoomId, membersIds: selectedOption }

        addMember(newData)
            .then(() => {
                toast.success("Thành Công")
            })
            .catch((err) => {
                toast.error(err.data.message);
            })


        setSelectedOption(null);
        setIsInviteMemberVisible(false);
        setSelectedMember(true);

    }

    const handleCancel = () => {
        setIsInviteMemberVisible(false);
    }

    const handleSelectChange = (value) => {
        console.log(value)
        setSelectedOption(value);
    };


    const data = userData.map((user) => ({
        id: user.id,
        name: user.employeeName,
        avatarUrl: `http://localhost:8080/employee/api/v1/avatar/${user.id}`,
    }))

    const handleSearch = (value) => {
        setSearchValue(value);
    };

    // const filteredOptions = data.filter(option =>
    //     option.name.toLowerCase().includes(searchValue.toLowerCase())
    // );

    const filteredOptions = data.filter((option) =>
        !memberData?.members.some((member) => member.employeeName === option.name) &&
        option.name.toLowerCase().includes(searchValue.toLowerCase())
    );

    console.log(memberData)


    return (
        <>
            <Modal
                title="Mời thêm thành viên"
                open={isInviteMemberVisible}
                onOk={handleOk}
                onCancel={handleCancel}
                destroyOnClose={true}
                closeIcon={false}
            >
                <Select
                    showSearch
                    style={{ width: '100%' }}
                    placeholder="Chọn một tùy chọn"
                    optionFilterProp="children"
                    mode="multiple"
                    onChange={handleSelectChange}
                    onSearch={handleSearch}
                    filterOption={false}
                >
                    {searchValue && filteredOptions.map((option) => (
                        <Option key={option.id} value={option.id}>
                            <Avatar size={"small"} src={option.avatarUrl} style={{ marginRight: '5px' }} />
                            {option.name}
                        </Option>
                    ))}
                </Select>
            </Modal>
        </>
    )
}