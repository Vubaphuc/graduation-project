import { useForm } from "react-hook-form";
import { useUpdateInformationEmployeeByIdMutation } from "../../../../app/apis/admin/employeeManageApi";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import { updateInformationSchema } from "../../schemas/adminSchemas";
import { useNavigate } from "react-router-dom";

const hookAdminInformationEmployee = () => {
    const navigate = useNavigate();

    const [updateInforMation] = useUpdateInformationEmployeeByIdMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateInformationSchema),
        mode: "all",
    });

    const onUpdateInformationEmployee = (data) => {
        const id = data.id;
        updateInforMation({id,...data})
        .unwrap()
        .then((res) => {
            toast.success("Cập Nhật Thông Tin Thành Công");
            setTimeout(()=> {
                navigate("/admin/employees")
            },1000)
        })
        .catch((err) => {
            toast.error(err.data.message)
        })
    }

    return {
        control, register, handleSubmit, errors, onUpdateInformationEmployee
    }
}

export default hookAdminInformationEmployee;