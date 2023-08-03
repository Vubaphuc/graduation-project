import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import hookFetchQuery from "./hookFetchQuery";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { updatePersonalInformationSchema } from "../../schemas/accountSchemas";
import { toast } from "react-toastify";
import { useUpdatePersonalInformationMutation } from "../../../../app/apis/employee/employeeApi";
import { updateUser } from "../../../../app/slice/authSlice";


const hookPersonalInformation = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const { token } = hookFetchQuery();

    const [personalInformation] = useUpdatePersonalInformationMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updatePersonalInformationSchema),
        mode: "all"
    });

    const onPersonalInformation = (data) => {
        personalInformation(data)
        .unwrap()
        .then((res) => {
            console.log(data)
            const newAuth = {
                auth: res,
                token: token,
                isAuthenticated: true,
            }
            dispatch(updateUser(newAuth));
            toast.success("Cập Nhật Thông Tin Thành Công");
        })
        .catch((err) => {
            toast.error(err);
        })
    }

    return {
        control, register, handleSubmit, errors, onPersonalInformation
    }

}

export default hookPersonalInformation;