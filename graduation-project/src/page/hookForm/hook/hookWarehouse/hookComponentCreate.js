import { useNavigate } from "react-router-dom";
import { useCreateComponentsMutation } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { componentCreateSchema } from "../../schemas/WarehouseEmployeeSchemas";
import { toast } from "react-toastify";


const hookComponentCreate = () => {
    const navigate = useNavigate();

    const [componentCreate] = useCreateComponentsMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(componentCreateSchema),
        mode: "all",
    });

    const onComponentCreate = (data) => {
        componentCreate(data)
        .unwrap()
        .then(() => {
            toast.success("Đăng Ký Thành Công");
            setTimeout(() => {
                navigate("/warehouse/components")
            }, 1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, handleSubmit, errors, register, onComponentCreate
    }

}

export default hookComponentCreate;