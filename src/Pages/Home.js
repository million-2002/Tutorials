import React, { useState } from "react";
import "./home.css";

const Home = () => {
  const [username, setUsername] = useState("");
  const [pwd, setPwd] = useState("");
  const updateUsername = (e) => {
    setUsername(e.target.value);
  };
  const updatePwd = (e) => {
    setPwd(e.target.value);
  };
  const loginNow = () => {
    let data = {
      name: username,
      password: pwd,
    };
    console.log(data);
    fetch("",
    Headers:{
      
    }
    )
      .then((response) => response.json())
      .then((json) => console.log(json))
      .catch((err) => console.log("Request Failed", err));
  };
  return (
    <div className={"mainContainer"}>
      <div className={"mainContainerBack"}>
        <div className={"mainContainerContent"}>
          <input
            className={"inputLogin"}
            onChange={(value) => updateUsername(value)}
          />
          <input
            className={"inputLogin"}
            onChange={(value) => updatePwd(value)}
          />
          <button className={"inputLoginBtn"} onClick={() => loginNow()}>
            LOGIN
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
