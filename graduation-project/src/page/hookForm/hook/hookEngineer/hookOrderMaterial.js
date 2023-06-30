import { useNavigate } from "react-router-dom";
import { useCreateOrderMaterialMutation } from "../../../../app/apis/engineer/engineerOrderMaterialApi";
import { useForm } from "react-hook-form";
import { orderMaterialCreateSchema } from "../../schemas/EngineerSchemas";
import { toast } from "react-toastify";
import { yupResolver } from "@hookform/resolvers/yup";

const hookOrderMaterial = () => {
    const navigate = useNavigate();

    const [orderCreate] = useCreateOrderMaterialMutation();

    const { register, handleSubmit,formState: { errors } } = useForm({
        resolver: yupResolver(orderMaterialCreateSchema),
        mode: "all"
    });

    const onOrderMaterial = (data) => {
        orderCreate(data)
        .unwrap()
        .then(() => {
            toast.success("Tạo Order Material thành công");
            setTimeout(() => {
                navigate("/engineer/orders");
            },1500)                      
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        register, handleSubmit, errors, onOrderMaterial
    }
}

export default hookOrderMaterial;