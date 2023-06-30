import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { registerInformationEngineerSchema } from "../../schemas/ReceptionistSchemas";
import { useRegisterEngineerInformationByProductMutation } from "../../../../app/apis/receptionist/productApi";
import { toast } from "react-toastify";


const hookRecepRegisterInformationEngineer = () => {

    const navigate = useNavigate();

    const [registerEngineer] = useRegisterEngineerInformationByProductMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(registerInformationEngineerSchema),
        mode: "all",
    });

    const onRegisterEnginner = (data) => {
        const id = data.id;
        registerEngineer({id,...data})
        .unwrap()
        .then(() => {
            toast.success("Cập Nhật Thành Công");
            setTimeout(() => {
                navigate("/receptionist/transfer-product");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onRegisterEnginner
    }
}

export default hookRecepRegisterInformationEngineer;