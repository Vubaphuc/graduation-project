import React from "react";
import Sidebar from "./Sidebar";
import Headers from "./Headers";
import { Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import Footer from "./Footer";
import SidebarChat from "./SidebarChat";
import ChatRoomPage from "../page/chat/ChatRoomPage";
import { Col, Row } from "antd";

function LayoutChat () {
    const { auth } = useSelector((state) => state.auth);

    const roles = auth.roles.map((role) => role.name);
    const isAdmin = roles.includes("ADMIN");


    return (
        <>
            <Row>
                <Col span={6}>
                    <SidebarChat/>
                </Col>
                <Col span={18}>
                    <ChatRoomPage />
                </Col>
            </Row>
        </>
    )
}

export default LayoutChat;