import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";


const hookFetchQuery = () => {

    const [avatarUrl, setAvatarUrl] = useState("");
    const { auth, token } = useSelector((state) => state.auth);



    useEffect(() => {
        getAvatar();
    },[auth])


    const getAvatar = async () => {
        try {
            const headers = {
                Authorization: `Bearer ${token}`, 
            };
            const rs = await axios.get(
                `http://localhost:8080/employee/api/v1/avatar/${auth.id}`,
                {
                    headers,
                    responseType: "arraybuffer",
                }
            );
            const base64String = btoa(
                new Uint8Array(rs.data).reduce(
                    (data, byte) => data + String.fromCharCode(byte), ""
                )
            );
            const imgSrc = `data:image/png;base64,${base64String}`;
            setAvatarUrl(imgSrc);
        } catch (err) {
            toast.error(err.data.message);
        }
    }

    return {
        auth, avatarUrl, token
    }

}

export default hookFetchQuery;