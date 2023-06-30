import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";


const addressQuery = () => {
    const [provinces, setProvinces] = useState([]);

    useEffect(() => {
        getAddress();
    }, []);

    const getAddress = async () => {
        try {
            const response = await axios.get("https://provinces.open-api.vn/api/p/");
            setProvinces(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    return {
        provinces
    }
};

export default addressQuery;