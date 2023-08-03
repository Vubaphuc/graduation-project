import React from "react";
import { Button, Avatar, Typography } from 'antd';
import styled from 'styled-components';
import { Link, Navigate, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../app/slice/authSlice";
import hookFetchQuery from "../page/hookForm/hook/hookAccount/hookFetchQuery";



const WrapperStyled = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 12px 16px;
  border-bottom: 1px solid rgba(82, 38, 83);

  .username {
    color: white;
    margin-left: 5px;
  }
`;

function UserInfo() {
    const { auth } = useSelector((state) => state.auth);
    const dispath = useDispatch();
    const navigate = useNavigate();

    const { avatarUrl } = hookFetchQuery();

    const handleJob = () => {
        const roles = auth.roles.map((role) => role.name);

        if (roles.includes("NHANVIENLETAN")) {
            navigate("/receptionist");
        }
        if (roles.includes("NHANVIENSUACHUA")) {
            navigate("/engineer");
        }
        if (roles.includes("NHANVIENKHO")) {
            navigate("/warehouse");
        }
        if (roles.includes("NHANVIENBAOHANH")) {
            navigate("/warranty");
        }
        if (roles.includes("ADMIN")) {
            navigate("/admin");
        }
    }

    const handleLogout = () => {
        dispath(logout());
        navigate("/login");
      };

    return (
        <WrapperStyled>
            <div>
                <Avatar src={avatarUrl}>
                </Avatar>
                <Typography.Text className='username'>{auth.employeeName}</Typography.Text>
            </div>
            <Button
                ghost
                onClick={handleJob}
            >
                Job
            </Button>
            <Button
                ghost
                onClick={handleLogout}
            >
                Đăng xuất
            </Button>
        </WrapperStyled>
    )
}

export default UserInfo;