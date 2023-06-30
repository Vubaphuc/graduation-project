import { useNavigate } from "react-router-dom";
import { useUpdateMaterialByIdMutation } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { updateMaterialSchema } from "../../schemas/WarehouseEmployeeSchemas";
import { toast } from "react-toastify";


const hookUpdateMaterial = (materialId) => {
    const id = materialId;
    const navigate = useNavigate();

    const [updateMaterial] = useUpdateMaterialByIdMutation();

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateMaterialSchema),
        mode: "all",
    });

    const onUpdateMaterial = (data) => {
        const newData = {...data, id: id};
        updateMaterial({id,...newData})
        .unwrap()
        .then(() => {
            toast.success("Cập Nhật Thành Công");
            setTimeout(() => {
                navigate("/warehouse");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        register, handleSubmit, errors, onUpdateMaterial
    }

}

export default hookUpdateMaterial;