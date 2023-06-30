import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { updateInformationProductRepair } from "../../schemas/EngineerSchemas";
import { useNavigate } from "react-router-dom";
import { useUpdateInformationProductGuaranteeByIdMutation } from "../../../../app/apis/engineer/engineerProductApi";
import { toast } from "react-toastify";

const hookUpdateInformationProductGuaranteeRepair = (productId) => {
    const id = productId;
    const navigate = useNavigate();

    const [updateInformationProductGuarantee] = useUpdateInformationProductGuaranteeByIdMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateInformationProductRepair),
        mode: "all"
    });

    const onUpdateInformationProductGuaranteeRepair = (data) => {
        const newData = {...data, id: id}

        updateInformationProductGuarantee({id,...newData})
        .unwrap()
        .then((res) => {
            toast.success("Đăng Ký thành công");
            setTimeout(() => {
                navigate("/engineer");
            },1500)           
            
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onUpdateInformationProductGuaranteeRepair
    }

}

export default hookUpdateInformationProductGuaranteeRepair;