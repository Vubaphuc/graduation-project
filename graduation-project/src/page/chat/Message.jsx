import React from "react";
import { Avatar, Typography } from 'antd';
import styled from 'styled-components';
import { useSelector } from "react-redux";
import "./Message.css"
import moment from "moment";


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

function Message({ text, displayName, userId, createdAt }) {
  const { auth } = useSelector((state) => state.auth);


  const avatarUrl = `http://localhost:8080/employee/api/v1/avatar/${userId}`;

  
  const date = moment(createdAt);
  const today = moment();

  const formattedDate = date.isSame(today, 'day')
    ? date.format('HH:mm') 
    : date.format('YYYY-MM-DD');

  console.log(date)

  return (

    <WrapperStyled>
      <div className={`message_item${auth.employeeName == displayName ? "_self" : ""}`}>

        <Typography.Text className='content'>{text}</Typography.Text>       
        <div className="flex mt-1">
          <Avatar size="small" src={avatarUrl}>
          </Avatar>
          <Typography.Text className="user-name ms-2" >{displayName}</Typography.Text>
        </div>
        <Typography.Text className='ms-2 mt-2'>
          {formattedDate}
        </Typography.Text>
      </div>
    </WrapperStyled>
  )
}

export default Message;