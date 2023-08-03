import { Col, Row } from "antd";
import React from "react";
import styled from 'styled-components';
import Headers from "./Headers";
import RoomList from "./RoomList";
import UserInfo from "./UserInfo";



const SidebarStyled = styled.div`
  background: #3f0e40;
  color: white;
  height: 100vh;
`;

function SidebarChat() {

    return (
        <SidebarStyled>
            <Row>
                <Col span={24}>
                    <UserInfo />
                </Col>
                <Col span={24}>
                    <RoomList />
                </Col>
            </Row>
        </SidebarStyled>
    )
}

export default SidebarChat;