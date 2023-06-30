import { useNavigate } from "react-router-dom"
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { forgotPasswordSchema } from "../../schemas/accountSchemas";
import { toast } from "react-toastify";
import { useForgotPasswordMutation } from "../../../../app/apis/employee/employeeApi";


const hookForgotPassword = () => {

    const navigate = useNavigate();

    const [sendEmail] = useForgotPasswordMutation();

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(forgotPasswordSchema),
        mode: "all"
    });

    const onSendMail = (data) => {
        sendEmail(data)
        .unwrap()
        .then(() => {
            toast.success("Mật khẩu Mới đã được gửi về email. Xin vui Lòng Kiểm tra lại");
            setTimeout (() => {
                navigate("/login")
            }, 1500)          
        })
    }

    return {
        register, handleSubmit, errors, onSendMail
    }
}

export default hookForgotPassword;