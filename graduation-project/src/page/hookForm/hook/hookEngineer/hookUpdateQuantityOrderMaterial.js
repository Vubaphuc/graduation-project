import { useNavigate } from "react-router-dom";
import { useUpdateQuantityOrderMaterialByIdMutation } from "../../../../app/apis/engineer/engineerOrderMaterialApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { orderMaterialCreateSchema } from "../../schemas/EngineerSchemas";
import { toast } from "react-toastify";


const hookUpdateQuantityOrderMaterial = (orderId) => {
    const id = orderId;
    const navigate = useNavigate();

    const [updateOrderMaterial] = useUpdateQuantityOrderMaterialByIdMutation();

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(orderMaterialCreateSchema),
        mode: "all",
    });

    const onUpdateQuantity = (data) => {
        const newData = {...data, id: id};
        updateOrderMaterial({id,...newData})
        .unwrap()
        .then(() => {
            toast.success("Cập Nhật Thành Công");
            setTimeout(() => {
                navigate("/engineer/orders");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        register, handleSubmit, errors, onUpdateQuantity
    }

}

export default hookUpdateQuantityOrderMaterial;