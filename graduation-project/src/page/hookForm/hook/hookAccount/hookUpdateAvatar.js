import { useState } from "react";
import { useNavigate } from "react-router-dom";
import hookFetchQuery from "./hookFetchQuery";
import { toast } from "react-toastify";
import axios from "axios";


const hookUpdateAvatar = () => {
    const [files, setFiles] = useState(null);

    const { token } = hookFetchQuery();

    const navigate = useNavigate();

    const handleChangeAvatar = async () => {

        const formData = new FormData();

        formData.append("avatar", files);

        try {

            const headers = {
                Authorization: `Bearer ${token}`,
            };

            const rs = await axios.post(
                "http://localhost:8080/employee/api/v1/upload-avatar",
                formData,
                {
                    headers
                },
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                }
            );

            navigate("/employee/personal-information")

        } catch (error) {
            toast.error(error.data.message);
        }
    };

    return {
        setFiles, handleChangeAvatar
    }

}

export default hookUpdateAvatar;