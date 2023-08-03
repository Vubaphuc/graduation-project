import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom"
import moment from "moment";
import { toast } from "react-toastify";
import { useUpdateReceiptByIdMutation } from "../../../../app/apis/warrantyEmoloyee/warrantyProductApi";
import { updateReceiptGuaranteeSchema } from "../../schemas/WarrantySchemas";

const hookWarrantyUpdateReceipt = (receiptId) => {
    const navigate = useNavigate();
    const id = receiptId;

    const [updateReceipt] = useUpdateReceiptByIdMutation();

    const { control, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(updateReceiptGuaranteeSchema),
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
                navigate("/warranty/receipts")
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

export default hookWarrantyUpdateReceipt;