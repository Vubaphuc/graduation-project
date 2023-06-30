import { useForm } from "react-hook-form";
import { useLoginMutation } from "../../../../app/apis/login/authApi"
import { yupResolver } from "@hookform/resolvers/yup";
import { loginSchema } from "../../schemas/accountSchemas";
import { toast } from "react-toastify";


const hookLogin = () => {

    const [login] = useLoginMutation();

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(loginSchema),
        mode: "all",
    });

    const onLogin = (data) => {
        login(data)
        .unwrap()
        .then(() => {
            toast.success("Đăng Nhập Thành Công");          
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        register, handleSubmit, errors, onLogin
    }
}

export default hookLogin;