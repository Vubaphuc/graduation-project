import React, { useEffect, useRef, useState } from "react";
import io from 'socket.io-client';
import { useSelector } from "react-redux";
import axios from "axios";
import { useSocket } from "../socket/useSocket";
import { toast } from "react-toastify";
import styled from "styled-components"
import { Button, Tooltip, Avatar, Form, Input, Alert } from "antd";
import Message from "./Message";
import { useContext } from "react";
import { ModalContext } from "./Context/ModalProvider";
import { useDeleteRoomChatMutation, useLazyGetMemberByRoomIdQuery } from "../../app/apis/employee/chatApi";


const WrapperStyled = styled.div`
  height: 100vh;
`;

const HeaderStyled = styled.div`
  display: flex;
  justify-content: space-between;
  height: 56px;
  padding: 0 16px;
  align-items: center;
  border-bottom: 1px solid rgb(230, 230, 230);
  margin-top: 20px;

    .headerInfo {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .headerTitle {
        margin: 0;
        font-weight: bold;
    }
  }
`;

const ButtonGroupStyled = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  margin-right: 20px;

  .ant-btn {
    margin-right: 10px;
  }
`;


const ContentStyled = styled.div`
  height: calc(100% - 56px);
  display: flex;
  flex-direction: column;
  padding: 11px;
  justify-content: flex-end;
  overflow-y: auto;
`;

const FormStyled = styled(Form)`
  display: flex;
  justify-content: space-between;
  flex-direction: column
  align-items: center;
  padding: 2px 2px 2px 0;
  border: 1px solid rgb(230, 230, 230);
  border-radius: 2px;
  padding-top: 20px;
  .ant-form-item {
    flex: 1;
    margin-bottom: 18px;
    margin-left: 10px;
  }
`;

const MessageListStyled = styled.div`
  max-height: 100%;
  overflow-y: auto;
`;


const ChatRoomPage = () => {
    const { auth } = useSelector((state) => state.auth);
    const { setIsInviteMemberVisible, setSelectedRoomId, selectedRoomId, setSelectedMember, selectedMember } = useContext(ModalContext);
    const [messages, setMessages] = useState([]);
    const [socket, setSocket] = useState(null);
    const [messageInput, setMessageInput] = useState("");
    const [messageData, setMessageData] = useState([]);

    const { socketResponse, isConnected, sendData } = useSocket(selectedRoomId, auth.id);
    const messageListRef = useRef(null);

    const [getMember, { data: memberData, isLoading: memberLoading }] = useLazyGetMemberByRoomIdQuery();
    const [deleteRoom] = useDeleteRoomChatMutation();


    useEffect(() => {
        const newSocket = io('http://localhost:8085');

        setSocket(newSocket);

        getMessage();

        // Dọn dẹp kết nối socket khi component unmount
        return () => {
            if (socket) {
                socket.disconnect();
            }
        };

    }, [selectedRoomId]);

    useEffect(() => {
        setMessages([]);
        setMessageData([])
    }, [selectedRoomId])

    useEffect(() => {
        if (selectedRoomId !== null) {
            getMember(selectedRoomId);
            setSelectedMember(false);
        }
    }, [selectedRoomId, selectedMember, socketResponse])



    useEffect(() => {
        if (socketResponse != undefined) {
            setMessages((prevMessages) => [...prevMessages, ...socketResponse]);
        }
    }, [socketResponse]);

    useEffect(() => {
        if (messageData != undefined) {
            setMessages((prevMessages) => [...prevMessages, ...messageData]);
        }
    }, [messageData]);

    useEffect(() => {
        // scroll to bottom after message changed
        if (messageListRef?.current) {
            messageListRef.current.scrollTop =
                messageListRef.current.scrollHeight + 50;
        }
    }, [messages]);



    const getMessage = async () => {

        if (selectedRoomId === null) {
            return;
        }
        try {
            const rs = await axios.get(`http://localhost:8080/chat/api/v1/message/${selectedRoomId}`);

            console.log(rs.data)
            setMessageData(rs.data);
        } catch (err) {
            toast.error(err.data.message);
        }
    }


    if (memberLoading) {
        return <h2>Loading...</h2>
    }



    const handleSendMessage = (e) => {
        e.preventDefault();
        if (messageInput != "") {
            sendData({
                content: messageInput,
            })
            const newMessage = {
                content: messageInput,
                username: auth.employeeName,
                createdDateTime: new Date(),
            };
            setMessages((prevMessages) => [...prevMessages, newMessage]);
        }


        setMessageInput("");
    };

    const handleModal = () => {
        setIsInviteMemberVisible(true);
    }

    const handleDelete = () => {
        deleteRoom(selectedRoomId)
            .then(() => {
                toast.error("Xóa Thành Công");
            })
            .catch((err) => {
                toast.error(err.data.message);
            })
    }



    return (
        <>
            <WrapperStyled>
                {selectedRoomId ? (<>
                    <HeaderStyled>
                        <div className="headerInfo">
                            <p className="headerTitle">{memberData?.name}</p>
                        </div>
                        <ButtonGroupStyled>
                            <Button
                                onClick={handleModal}
                            >
                                Mời
                            </Button>
                            <Avatar.Group size="small" maxCount={2}>
                                {memberData && memberData.members.map((member) => (
                                    <Tooltip title={member.employeeName} key={member.id} >
                                        <Avatar src={`http://localhost:8080/employee/api/v1/avatar/${member.id}`}></Avatar>
                                    </Tooltip>
                                ))}
                            </Avatar.Group>
                            <Button
                                className="ms-2"
                                style={{ backgroundColor: "red" }}
                                onClick={handleDelete}
                            >
                                Delete
                            </Button>
                        </ButtonGroupStyled>
                    </HeaderStyled>
                    <ContentStyled>
                        <MessageListStyled ref={messageListRef}>
                            {messages &&
                                messages.map((message, index) => (
                                    <Message
                                        key={index}
                                        text={message.content}
                                        displayName={message.username}
                                    />
                                ))}
                        </MessageListStyled>
                        <FormStyled >
                            <Form.Item>
                                <Input
                                    onChange={(e) => setMessageInput(e.target.value)}
                                    onPressEnter={(e) => handleSendMessage(e)}
                                    value={messageInput}
                                    autoComplete='off'
                                />
                            </Form.Item>
                            <Button className="mb-3 ms-2 mr-3" onClick={(e) => handleSendMessage(e)}>
                                Gửi
                            </Button>
                        </FormStyled>
                    </ContentStyled>
                </>
                ) : (
                    <Alert
                        message='Hãy chọn phòng'
                        type='info'
                        showIcon
                        style={{ margin: 5 }}
                        closable
                    />
                )}
            </WrapperStyled>
        </>
    );
}

export default ChatRoomPage;
