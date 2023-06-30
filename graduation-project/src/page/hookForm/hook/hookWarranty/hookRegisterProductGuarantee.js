import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { registerProductGuaranteeSchema } from "../../schemas/WarrantySchemas";
import { useCreateProductGuaranteeMutation } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";
import { toast } from "react-toastify";


const hookRegisterProductGuarantee = () => {
    const navigate = useNavigate();

    const [createProductGuarantee] = useCreateProductGuaranteeMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(registerProductGuaranteeSchema),
        mode: "all",
    });

    const onRegisterProductGuarantee = (data) => {
        createProductGuarantee(data)
        .unwrap()
        .then(() => {
            toast.success("Đăng Ký Thành Công");
            setTimeout(() => {
                navigate("/warranty")
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors,onRegisterProductGuarantee
    }
}

export default hookRegisterProductGuarantee;