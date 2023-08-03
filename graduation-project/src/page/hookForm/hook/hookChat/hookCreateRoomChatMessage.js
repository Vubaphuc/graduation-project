import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { createRoomChatSchema } from "../../schemas/ChatMessageSchemas";
import { useNavigate } from "react-router-dom";
import { useCreateRoomMutation } from "../../../../app/apis/employee/chatApi";
import { toast } from "react-toastify";


const hookCreateRoomChatMessage = () => {
    const navigate = useNavigate();

    const [createRoomChat] = useCreateRoomMutation();

    const { control, register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(createRoomChatSchema),
        mode: "all",
    });

    const onCreateRoomChat = (data) => {
        createRoomChat(data)
        .unwrap()
        .then(() => {
            toast.success("Tao Room thanh cong")
            navigate("/employee/room")
        })
        .catch((err) => {
            toast.error(err.data.message);
        })
    }

    return {
        control, register, handleSubmit, errors, onCreateRoomChat
    }

}

export default hookCreateRoomChatMessage;