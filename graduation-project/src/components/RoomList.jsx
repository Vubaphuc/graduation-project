import React from "react";
import { Collapse, Typography, Button } from 'antd';
import styled from 'styled-components';
import { PlusSquareOutlined } from '@ant-design/icons';
import { useContext } from "react";
import { ModalContext } from "../page/chat/Context/ModalProvider";
import { useEffect } from "react";
import { useState } from "react";
import { io } from "socket.io-client";
import { useSelector } from "react-redux";
import { useLazyGetRoomByUserIdQuery } from "../app/apis/employee/chatApi";


const { Panel } = Collapse;

const PanelStyled = styled(Panel)`
  &&& {
    .ant-collapse-header,
    p {
      color: white;
    }

    .ant-collapse-content-box {
      padding: 0 40px;
    }

    .add-room {
      color: white;
      padding: 0;
    }
  }
`;


const LinkStyled = styled(Typography.Link)`
  display: block;
  margin-bottom: 5px;
  color: white;
`;

function RoomList() {
  const { setSelectedRoomId, setIsAddRoomVisible } = useContext(ModalContext);
  const { auth } = useSelector((state) => state.auth);
  const [getRoom, { data: roomData, isLoading: roomLoading }] = useLazyGetRoomByUserIdQuery();


  useEffect(() => {
    getRoom(auth.id);
  }, [auth])




  if (roomLoading) {
    return <h2>Loading....</h2>
  }


  console.log(roomData)


  return (
    <Collapse ghost defaultActiveKey={['1']}>
      <PanelStyled header="Danh Sách Các Phòng" key='1' >
        {roomData && roomData.map((room) => (
          <LinkStyled key={room.id} onClick={() => setSelectedRoomId(room.id)}>
            {room.name ? room.name : "No Name"}
          </LinkStyled>
        ))}

        <Button
          type="text"
          icon={<PlusSquareOutlined />}
          className="add-room"
          onClick={() => setIsAddRoomVisible(true)}
        >
          Thêm phòng
        </Button>
      </PanelStyled>
    </Collapse>
  )
}

export default RoomList;