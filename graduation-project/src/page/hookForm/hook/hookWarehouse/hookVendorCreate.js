import { useNavigate } from "react-router-dom";
import { useCreateVendorMutation } from "../../../../app/apis/warehouseEmployee/warehouseEmployeeApi";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { vendorCreateSchema } from "../../schemas/WarehouseEmployeeSchemas";
import { toast } from "react-toastify";

const hookVendorCreate  = () => {

    const navigate = useNavigate();

    const [vendorCreate] = useCreateVendorMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(vendorCreateSchema),
        mode: "all"
    });

    const onVendorCreate = (data) => {
        vendorCreate(data)
        .unwrap()
        .then(() => {
            toast.success("Đăng ký thành công");
            setTimeout(() => {
                navigate("/warehouse")
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    } 

    return {
        control, register, handleSubmit, errors, onVendorCreate
    }

}

export default hookVendorCreate;