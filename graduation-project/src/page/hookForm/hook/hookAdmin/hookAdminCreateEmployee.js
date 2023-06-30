import { useNavigate } from "react-router-dom";
import { useCreateEmployeeMutation } from "../../../../app/apis/admin/employeeManageApi";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { newCreateEmployeeSchema } from "../../schemas/adminSchemas";
import { toast } from "react-toastify";

const hookAdminCreateEmployee = () => {
    const navigate = useNavigate();

    const [createEmployee] = useCreateEmployeeMutation();

    const { control, register, handleSubmit, setValue ,formState: { errors } } = useForm({
        resolver: yupResolver(newCreateEmployeeSchema),
        mode: "all"
    });

    const onCreateEmployee = (data) => {
        createEmployee(data)
        .unwrap()
        .then(() => {
            toast.success("Đăng Ký Thành Công");
            setTimeout(() => {
                navigate("/admin/employees");
            },1000)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
      
    }

    return {
        control, register, handleSubmit, setValue, errors, onCreateEmployee
    }

}

export default hookAdminCreateEmployee;