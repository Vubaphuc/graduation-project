import React from "react";
import { Avatar, Typography } from 'antd';
import styled from 'styled-components';
import useSelection from "antd/es/table/hooks/useSelection";
import { useSelector } from "react-redux";
import "./Message.css"


const WrapperStyled = styled.div`
  margin-bottom: 10px;

  .message_item {
    margin-left: 30px;
    display: flex;
    flex-direction: column;
  }

  .message_item_self {
    display: flex;
    flex-direction: column;
    margin-right: 50px;

  }

  .date {
    margin-left: 10px;
    font-size: 11px;
    color: #a7a7a7;
  }

  .content {
    margin-left: 30px;
  }
`;

function Message({ text, displayName }) {
  const { auth } = useSelector((state) => state.auth);

  return (

    <WrapperStyled>
      <div className={`message_item${auth.employeeName == displayName ? "_self" : ""}`}>     
        <div>
          <Typography.Text className='content'>{text}</Typography.Text>
        </div>
        <div>
          <Typography.Text className="user-name" >{displayName}</Typography.Text>
        </div>
      </div>
    </WrapperStyled>
  )
}

export default Message;