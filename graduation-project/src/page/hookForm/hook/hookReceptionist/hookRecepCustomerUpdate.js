import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom"
import { updateCustomerSchema } from "../../schemas/ReceptionistSchemas";
import { useUpdateCustomerByIdMutation } from "../../../../app/apis/receptionist/customerApi";
import { toast } from "react-toastify";


const hookRecepCustomerUpdate = () => {
    const navigate = useNavigate();

    const [updateCustomer] = useUpdateCustomerByIdMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateCustomerSchema),
        mode: "all",
    });

    const onUpdateCustomer = (data) => {
        const id = data.id;
        updateCustomer({id,...data})
        .unwrap()
        .then(() => {
            toast.success("Cập nhật thành công")
            setTimeout(() => {
                navigate("/receptionist/customers");
            },1500)
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onUpdateCustomer
    }
}

export default hookRecepCustomerUpdate;