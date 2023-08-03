import React from "react";
import { Link } from "react-router-dom";
import { useGetRoomAllQuery } from "../../app/apis/employee/chatApi";

function RoomPage() {

    const { data: roomData, isLoading: roomLoading } = useGetRoomAllQuery();

    if(roomLoading) {
        return <h2>Loading...</h2>
    }

    console.log(roomData);

    return (
        <>
            <div className="container mt-5 mb-5">
                <h2 className="text-center text-uppercase">Danh sách user</h2>
                <div className="row justify-content-center">
                    <div className="col-md-10">
                        <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
                            <Link to={'/employee/room/create'} className="btn btn-warning">
                                Tạo Room
                            </Link>
                            <input
                                type="text"
                                id="search"
                                className="form-control w-50"
                                placeholder="Tìm kiếm user theo Name"
                            />
                        </div>
                        <div className="bg-light p-4">
                            <table className="table table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Room Name</th>                                      
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {roomData && roomData.map((room) => (                                   
                                    <tr key={room.id}>                                       
                                        <td>{room.id}</td>
                                        <td>{room.name}</td>
                                        <td>
                                            <Link to={`/employee/room/${room.id}`} className="btn btn-success">
                                                Xem chi tiết
                                            </Link>
                                            <button className="btn btn-danger">
                                                Xóa
                                            </button>
                                        </td>

                                    </tr>
                                    ))}
                                </tbody>
                            </table>
                            <p className="message d-none"></p>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default RoomPage;