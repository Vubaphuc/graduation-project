import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { createProductSchema } from "../../schemas/ReceptionistSchemas";
import { useCreateProductMutation } from "../../../../app/apis/receptionist/productApi";
import { toast } from "react-toastify";
import { useState } from "react";



const hookRecepProductCreate = () => {
    const navigate = useNavigate();
    const [check, setCheck] = useState(false);

    const [createProduct] = useCreateProductMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(createProductSchema),
        mode: "all",
    });

    const onCreateProduct = (data) => {
        createProduct(data)
        .unwrap()
        .then((res) => {
            toast.success("Đăng ký Thành công");
            setCheck(true)
            setTimeout(() => {
                navigate(`/receptionist/receipt/${res.data.id}`)
            }, 1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onCreateProduct, check, setCheck
    }

}

export default hookRecepProductCreate;