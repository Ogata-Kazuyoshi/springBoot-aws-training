import React, { memo, useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import '../../App';
import AddBoxIcon from '@mui/icons-material/AddBox';
import { changeToStringDate } from '../../controllers/controller';
import dbApi from '../../api/dbHandling';
import { titleGet } from '../../controllers/controller';

const Nav = (props) => {
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

  //   console.log('nw]owTyping : ', nowTyping);

  const selectCard = (id, content) => {
    console.log('selected : ', id);
    setNewMessage('');
    setNowTyping(content);
    setCurrentId(id);
    setIsModify(false);
  };
  const addHandler = async () => {
    const currentTime = new Date();
    // const changeDate = changeToStringDate(currentTime);
    const changeDate = Math.floor(currentTime.getTime() / 1000);
    const sendData = {
      create_date: changeDate,
      update_date: changeDate,
      content: '',
    };
    let maxId = 0;
    if (memos.length !== 0) {
      const memoIds = memos.map((elm) => elm.id);
      maxId = Math.max(...memoIds);
    }
    console.log(`maxId : ${maxId}`);
    const res = await dbApi.createCard(maxId + 1, sendData);
    const resAll = await dbApi.getDB();
    setMemos(resAll.data);
    setCurrentId(res.data.id);
    setNowTyping('');
    console.log('changeTime', changeDate);
    console.log('res : ', res);
  };

  const checkDiffTime = (date) => {
    const current = new Date().getTime();
    const updated = date * 1000;
    let diff = (current - updated) / 1000 / 60;
    if (import.meta.env.VITE_NODE_ENV === 'production') {
      diff = diff + 540;
    }
    if (Math.floor(diff) < 60) {
      return `Last updated ${Math.floor(diff)} min ago`;
    } else {
      diff = diff / 60;
      if (Math.floor(diff) < 60) {
        return `Last updated ${Math.floor(diff)} hours ago`;
      } else {
        diff = diff / 24;
        return `Last updated ${Math.floor(diff)} days ago`;
      }
    }
  };

  useEffect(() => {}, []);

  return (
    <div className="personal__component">
      <div className="personal__nav">
        <div className="personal__nav--top">
          <AddBoxIcon onClick={addHandler} />
        </div>

        {memos.map((elm) => {
          return (
            <div
              key={elm.id}
              className="card__container"
              onClick={() => {
                selectCard(elm.id, elm.content);
              }}
              style={{
                border: elm.id === currentId ? '2px solid red' : 'none',
              }}
            >
              <div className="card__title">
                <div>
                  {elm.id === currentId
                    ? titleGet(nowTyping)
                    : titleGet(elm.content)}
                </div>
              </div>
              <div className="card__date">
                {/* <div>{checkDiffTime(elm.update_date)}</div> */}
                <div>{checkDiffTime(elm.update_date)}</div>
              </div>
            </div>
          );
        })}
      </div>
      <Outlet />
    </div>
  );
};

export default Nav;
