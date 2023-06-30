import { useNavigate } from "react-router-dom";
import { useApproveOrderMaterialMutation } from "../../../../app/apis/warehouseEmployee/approverOrderMaterialApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { approverOrderMaterialSchema } from "../../schemas/WarehouseEmployeeSchemas";
import { toast } from "react-toastify";


const hookApproverOrderMaterial = (orderId) => {
    const id = orderId;
    const navigate = useNavigate();

    const [approverOrder] = useApproveOrderMaterialMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(approverOrderMaterialSchema),
        mode: "all",
    });

    const onApproverOrder = (data) => {
        const newData = {...data, id: id};

        approverOrder({id,...newData})
        .unwrap()
        .then(() => {
            toast.success("Phê duyệt thành công");
            setTimeout(() => {
                navigate("/warehouse/orderMaterials");
            })
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onApproverOrder
    }

}

export default hookApproverOrderMaterial;