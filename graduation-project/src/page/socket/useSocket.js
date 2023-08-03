import { useEffect } from "react";
import { useCallback } from "react";
import { useState } from "react";
import { io } from "socket.io-client";

export const useSocket = (roomId, userId) => {
    const [socket, setSocket] = useState(null);
    const [isConnected, setConnected] = useState(false);
    const [socketResponse, setSocketResponse] = useState([]);

    useEffect(() => {
        if (roomId !== null && userId !== null) {
            const s = io("http://localhost:8085", {
                reconnection: false,
                query: `userId=${userId}&roomId=${roomId}`,
            });
            setSocket(s);
            s.on("connect", () => setConnected(true));
            s.on("read_message", (res) => {
                const message = {
                    content: res.content,
                    username: res.username,
                    createdDateTime: res.createdDateTime,
                }
                setSocketResponse((prevMessages) => [...prevMessages, message]);
            });           
            return () => {
                s.disconnect();
            };
        }
    }, [roomId, userId])

    const sendData = useCallback(
        (payload) => {
            if (socket) {
                socket.emit("send_message", {
                    roomId: roomId,
                    content: payload.content,
                    userId: userId,
                });
            }
        },
        [socket, roomId]
    );

    const addMember = useCallback(
        (payload) => {
            
            if (socket) {
                console.log(roomId)
                socket.emit("add_member", {
                    roomId: roomId,
                    membersIds: payload.membersIds,
                });
                
            }
        },
        [socket, roomId]
    );

   

    

    return {
        socketResponse, isConnected, sendData, socket, addMember
    }
}