import { useEffect } from "react";
import { useCallback } from "react";
import { useState } from "react";
import { io } from "socket.io-client";

export const useSocket = (room, userId) => {
    const [socket, setSocket] = useState(null);
    const [isConnected, setConnected] = useState(false);
    const [socketResponse, setSocketResponse] = useState([]);

    useEffect(() => {
        if (userId === null) {
            return;
        }

        const s = io("http://localhost:8085", {
            reconnection: false,
            query: `userId=${userId}&room=${room}`,
        });
        setSocket(s);
        s.on("connect", () => setConnected(true));
        s.on("read_message", (res) => {
            const message = {
                userId: res.userId,
                content: res.content,
                username: res.username,
                room: res.room,
                createdDateTime: res.createdDateTime,
            }
            setSocketResponse((prevMessages) => [...prevMessages, message]);
        });
        return () => {
            s.disconnect();
        };
    }, [room, userId])

    const sendData = useCallback(
        (payload) => {
            if (socket) {
                socket.emit("send_message", {
                    room: room,
                    content: payload.content,
                    userId: userId,
                });
            }
        },
        [socket, room]
    );

   


    return {
        socketResponse, isConnected, sendData, socket
    }
}