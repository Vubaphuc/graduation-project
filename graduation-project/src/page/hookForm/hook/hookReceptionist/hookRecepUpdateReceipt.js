import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom"
import { updateReceiptSchema } from "../../schemas/ReceptionistSchemas";
import moment from "moment";
import { useUpdateReceiptByIdMutation } from "../../../../app/apis/receptionist/productApi";
import { toast } from "react-toastify";

const hookRecepUpdateReceipt = (receiptId) => {
    const navigate = useNavigate();
    const id = receiptId;

    const [updateReceipt] = useUpdateReceiptByIdMutation();

    const { control, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateReceiptSchema),
        mode: "all"
    });

    const onUpdateReceipt = (data) => {
        const formattedPayDate = data ? moment(data.payDate).format("YYYY-MM-DDTHH:mm:ss") : "";
        const newData = {id: id, payDate: formattedPayDate}
        updateReceipt({id,...newData})
        .unwrap()
        .then(() => {
            toast.success("Cập nhật thành công")
            setTimeout(() => {
                navigate("/receptionist/receipts")
            }, 1500)
        })
        .catch((err) => {
            toast.error(err.data.message)
        })
    }

    return {
        control, handleSubmit, errors, onUpdateReceipt
    }
}

export default hookRecepUpdateReceipt;