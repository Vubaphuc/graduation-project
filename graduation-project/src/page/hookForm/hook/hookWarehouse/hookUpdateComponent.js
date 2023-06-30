import { useNavigate } from "react-router-dom";
import { useUpdateComponentByIdMutation } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import { updateComponentSchema } from "../../schemas/WarehouseEmployeeSchemas";

const hookUpdateComponent = () => {
    const navigate = useNavigate();

    const [updateComponent] = useUpdateComponentByIdMutation();

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateComponentSchema),
        mode: "all",
    });

    const onUpdateComponent = (data) => {
        const id = data.id;
        console.log(data)
        updateComponent({id,...data})
        .unwrap()
        .then(() => {
            toast.success("Cập Nhật Thành công");
            setTimeout(() => {
                navigate("/warehouse/components");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        register, handleSubmit, errors, onUpdateComponent
    }

}

export default hookUpdateComponent;