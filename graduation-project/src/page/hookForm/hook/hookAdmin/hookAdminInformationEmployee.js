import { useForm } from "react-hook-form";
import { useUpdateInformationEmployeeByIdMutation } from "../../../../app/apis/admin/employeeManageApi";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import { updateInformationSchema } from "../../schemas/adminSchemas";

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
                navigate(`/admin/employee/${res.data.id}`)
            },1000)
        })
    }

    return {
        control, register, handleSubmit, errors, onUpdateInformationEmployee
    }
}

export default hookAdminInformationEmployee;