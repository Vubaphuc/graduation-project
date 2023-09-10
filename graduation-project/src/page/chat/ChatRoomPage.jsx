import React, { useEffect, useRef, useState } from "react";
import io from 'socket.io-client';
import { useSelector } from "react-redux";
import axios from "axios";
import { useSocket } from "../socket/useSocket";
import { toast } from "react-toastify";
import styled from "styled-components"
import { Button, Form, Input } from "antd";
import Message from "./Message";


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
    const room = "chat"
    const { auth } = useSelector((state) => state.auth);
    const [messages, setMessages] = useState([]);
    const [socket, setSocket] = useState(null);
    const [messageInput, setMessageInput] = useState("");
    const [messageData, setMessageData] = useState([]);

    const { socketResponse, isConnected, sendData } = useSocket(room, auth.id);
    const messageListRef = useRef(null);




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

    }, [room]);

    useEffect(() => {
        setMessages([]);
        setMessageData([])
    }, [room])

   

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
  
        try {
            const rs = await axios.get(`http://localhost:8080/chat/api/v1/message/${room}`);

            console.log(rs.data)
            setMessageData(rs.data);
        } catch (err) {
            toast.error(err.data.message);
        }
    }




    const handleSendMessage = (e) => {
        e.preventDefault();
        if (messageInput != "") {
            sendData({
                content: messageInput,
            })
            const newMessage = {
                userId: auth.id,
                content: messageInput,
                username: auth.employeeName,
                room: room,
                createdDateTime: new Date(),
            };
            setMessages((prevMessages) => [...prevMessages, newMessage]);
        }


        setMessageInput("");
    };

    
    return (
        <>
            <WrapperStyled>
                    <HeaderStyled>
                        <div className="headerInfo">
                            <p className="headerTitle">{room}</p>
                        </div>                    
                    </HeaderStyled>
                    <ContentStyled>
                        <MessageListStyled ref={messageListRef}>
                            {messages &&
                                messages.map((message, index) => (
                                    <Message
                                        key={index}
                                        text={message.content}
                                        userId={message.userId}
                                        displayName={message.username}
                                        createdAt={message.createdDateTime}
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
            </WrapperStyled>
        </>
    );
}

export default ChatRoomPage;
