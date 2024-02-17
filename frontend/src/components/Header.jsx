import React, { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import '../App';

const Header = () => {
  const navigate = useNavigate();
  useEffect(() => {
    navigate('/personal/memo');
  }, []);
  return (
    <div className="header__component">
      <div className="header__content">
          <img src="https://dig-expo-dev.s3.ap-northeast-1.amazonaws.com/1080x1104_%E3%83%81%E3%83%BC%E3%83%A0%E3%83%A1%E3%83%B3%E3%83%8F%E3%82%99.jpeg"/>
        <div>DIG Notes-test</div>
      </div>
      <Outlet />
    </div>
  );
};

export default Header;
