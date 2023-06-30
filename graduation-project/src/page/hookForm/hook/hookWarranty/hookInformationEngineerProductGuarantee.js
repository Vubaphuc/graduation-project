import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { changeEngineerSchema } from "../../schemas/WarrantySchemas";
import { useRegisterEngineerInformationByProductGuaranteeMutation } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";
import { toast } from "react-toastify";


const hookInformationEngineerProductGuarantee = () => {
    const navigate = useNavigate();

    const [updateEngineer] = useRegisterEngineerInformationByProductGuaranteeMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(changeEngineerSchema),
        mode: "all",
    });

    const onInformationEngineer = (data) => {
        const id = data.id;
        updateEngineer({id,...data})
        .unwrap()
        .then(() => {
            toast.success("Đăng ký Thành công");
            setTimeout(() => {
                navigate("/warranty/product-guarantee")
            }, 1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onInformationEngineer
    }
}

export default hookInformationEngineerProductGuarantee;