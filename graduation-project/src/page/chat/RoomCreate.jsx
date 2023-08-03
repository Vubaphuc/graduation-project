import React from "react";
import { Link } from "react-router-dom";
import hookCreateRoomChatMessage from "../hookForm/hook/hookChat/hookCreateRoomChatMessage";
import { useGetAllUsersQuery } from "../../app/apis/employee/employeeApi";
import { getEmployees, getUsers } from "../formHTML/options";
import { Controller } from "react-hook-form";
import Select from "react-select";
import { yupResolver } from "@hookform/resolvers/yup";
import { createRoomChatSchema } from "../hookForm/schemas/ChatMessageSchemas";

function RoomCreate() {


    const { control, register, handleSubmit, errors, onCreateRoomChat } = hookCreateRoomChatMessage();
    const resolver = yupResolver(createRoomChatSchema);
    const { data: userData, isLoading: userLoading } = useGetAllUsersQuery();

    if (userLoading) {
        return <h2>Loading...</h2>
    }

    console.log(userData)

    const employeeOption = getUsers(userData);

    return (
        <>
            <div className="container mt-5 mb-5">
                <h2 className="text-center text-uppercase mb-3">Tạo user</h2>
                <div className="row justify-content-center">
                    <div className="col-md-6">
                        <form onSubmit={handleSubmit(onCreateRoomChat)}>
                            <div className="bg-light p-4">
                                <div className="mb-3">
                                    <label className="col-form-label">Name</label>
                                    <input 
                                        type="text" 
                                        id="name" 
                                        className="form-control" 
                                        {...register("roomName")}
                                    />
                                </div>                              
                                <div className="mb-3">
                                    <label className="col-form-label">Members</label>
                                    <Controller
                                        name="membersIds"
                                        control={control}
                                        defaultValue={[]}
                                        rules={{ resolver }}
                                        render={({ field: { onChange, value, ref } }) => (
                                            <div>
                                                <Select
                                                    placeholder="--Chọn danh mục--"
                                                    inputRef={ref}
                                                    options={employeeOption}
                                                    value={employeeOption.find(
                                                        (c) => c.value === value
                                                    )}
                                                    onChange={(val) =>
                                                        onChange(val.map((c) => c.value))
                                                    }
                                                    isMulti
                                                />
                                                {errors.membersIds?.message && (
                                                    <p className="text-danger fst-italic mt-2">
                                                        {errors.categoryIds.message}
                                                    </p>
                                                )}
                                            </div>
                                        )}
                                    />
                                </div>                            
                            </div>
                            <div className="text-center mt-3">
                                <Link to={'/employee/room'} className="btn btn-secondary btn-back">
                                    Quay lại
                                </Link>
                                <button className="btn btn-success" id="btn-save" type="submit">
                                    Tạo User
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}

export default RoomCreate;