import { f } from "html2pdf.js";
import React, { createContext } from "react";
import { useState } from "react";

export const ModalContext = createContext();

export default function ModalProvider({ children }) {
    const [isInviteMemberVisible, setIsInviteMemberVisible] = useState(false);
    const [selectedRoomId, setSelectedRoomId] = useState(null);
    const [selectedUserId, setSelectedUserId] = useState(null);
    const [selectedMember, setSelectedMember] = useState(false);
    const [isAddRoomVisible, setIsAddRoomVisible] = useState(false);

    console.log(isAddRoomVisible)

    return (
        <ModalContext.Provider
            value={{
                isInviteMemberVisible,
                setIsInviteMemberVisible,
                selectedRoomId,
                setSelectedRoomId,
                selectedUserId,
                setSelectedUserId,
                isAddRoomVisible,
                setIsAddRoomVisible,
                selectedMember,
                setSelectedMember

            }}
        >
            {children}
        </ModalContext.Provider>
    )
}

