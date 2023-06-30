import { useNavigate } from "react-router-dom";
import { useUpdateInformationProductNewByIdMutation } from "../../../../app/apis/engineer/engineerProductApi";
import { useForm } from "react-hook-form";
import { updateInformationProductRepair } from "../../schemas/EngineerSchemas";
import { toast } from "react-toastify";
import { yupResolver } from "@hookform/resolvers/yup";

const hookUpdateInformationProductRepair = (productId) => {
    const id = productId;
    const navigate = useNavigate();

    const [updateInformationProduct] = useUpdateInformationProductNewByIdMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateInformationProductRepair),
        mode: "all"
    });

    const onUpdateInformationProductRepair = (data) => {
        const newData = {...data, id: id}

        updateInformationProduct({id,...newData})
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
        control, register, handleSubmit, errors, onUpdateInformationProductRepair
    }

}

export default hookUpdateInformationProductRepair;