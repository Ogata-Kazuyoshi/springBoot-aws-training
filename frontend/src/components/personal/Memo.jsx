import React, { useEffect, useRef, useState } from 'react';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import dbApi from '../../api/dbHandling';
import { titleGet, changeToStringDate } from '../../controllers/controller';

const Memo = (props) => {
  const {
    memos,
    setMemos,
    currentId,
    setCurrentId,
    nowTyping,
    setNowTyping,
    newMessage,
    setNewMessage,
    isModify,
    setIsModify,
    saved,
    setSaved,
    resetSaved,
  } = props;
  const textAreaRef = useRef(null);
  const id = currentId;

  useEffect(() => {
    const autoPost = async () => {
      if (isModify) {
        setIsModify(false);
        try {
          const sendData = {
            update_date: changeToStringDate(new Date()),
            content: newMessage,
          };
          const res = await dbApi.updateCard(id, sendData);
          console.log('res : ', res);
          const getAll = await dbApi.getDB();
          setMemos(getAll.data);
          setSaved('saved');
          resetSaved();
        } catch (err) {
          console.log(`err : ${err}`);
        }
      } else return;
    };
    autoPost();
  }, [currentId]);

  const deleteHandle = async () => {
    if (currentId === null) return;
    const res = await dbApi.deletCard(currentId);
    const resAll = await dbApi.getDB();
    setCurrentId(null);
    setMemos(resAll.data);
  };

  let index = 0;
  if (currentId !== null) {
    index = memos.findIndex((elm) => elm.id === currentId);
    // setNewMessage(memos[index].content);
    // console.log(memos[index].content);
  }
  const autoPost = async () => {
    if (isModify) {
      setIsModify(false);
      try {
        const sendData = {
          update_date: changeToStringDate(new Date()),
          content: newMessage,
        };
        const res = await dbApi.updateCard(currentId, sendData);
        console.log('res : ', res);
        const getAll = await dbApi.getDB();
        setMemos(getAll.data);
        setSaved('saved');
        resetSaved();
        setIsModify(false);
      } catch (err) {
        console.log(`err : ${err}`);
      }
    } else return;
  };

  let timer;
  let flg = false;
  // const timeFunc = () => {
  //   if (flg) {
  //     flg = true;
  //     timer = setTimeout(() => {
  //       autoPost();
  //     }, 5000);
  //   } else {
  //     clearTimeout(timer);
  //     //   flg = false;
  //     timer = setTimeout(() => {
  //       autoPost();
  //     }, 5000);
  //   }
  // };

  const changeHandler = (e) => {
    setNewMessage(e.target.value);
    setNowTyping(titleGet(e.target.value));
    // timeFunc();
  };

  const updateHandler = async () => {
    const sendData = {
      update_date: Math.floor(new Date().getTime() / 1000),
      content: newMessage,
    };
    const res = await dbApi.updateCard(currentId, sendData);
    console.log('res : ', res);
    const getAll = await dbApi.getDB();
    setMemos(getAll.data);
  };

  const activeModify = () => {
    // timer();
    setIsModify(true);
  };

  //   const keydownHandler = () => {
  //     console.log('keyが押されました');
  //     clearTimeout(timer);
  //     timer();
  //   };

  //   if (textAreaRef.current) {
  //     // textAreaRef.current.addEventListener('keydown', keydownHandler);
  //     textAreaRef.current.addEventListener('keyup', keydownHandler);
  //   }

  return (
    <div className="personal__memo">
      <div className="personal__memo--top">
        <div>
          <DeleteForeverIcon onClick={deleteHandle} />
          <div>{saved}</div>
        </div>
        <button onClick={updateHandler}>編集完了する</button>
      </div>
      {currentId === null ? (
        <></>
      ) : (
        <div className="personal__memo--inputarea">
          <textarea
            type="text"
            value={newMessage || memos[index].content}
            onChange={changeHandler}
            onClick={activeModify}
            className="persnal__memo--input"
            ref={textAreaRef}
            style={{
              backgroundColor: isModify ? 'white' : 'rgb(191, 191, 191)',
              border: isModify ? '2px solid black' : 'none',
            }}
          />
        </div>
      )}
    </div>
  );
};

export default Memo;
