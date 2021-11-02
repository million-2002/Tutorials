import React, { useState } from "react";
import { Upload, message, Button } from "antd";
// import { InboxOutlined } from "@ant-design/icons";
import {UploadOutlined} from "@ant-design/icons";

const UploadPage = () => {
  const [uploading, setUploading] = useState(false);
  const [name, setName] = useState("");
  const [msg, setMsg] = useState("");
  const [fileList, setFileList] = useState([]);
  const uploadData = () => {
    let formdata = new FormData();
    formdata.append("name", name);
    formdata.append("msg", msg);
    fileList.forEach((item, index) => formdata.append("file", item));
    setUploading(true);
    fetch("", {
      mode: "cors",
      method: "POST",
      headers: {
        "Content-type": "application:/x-www-form-urlencoded:charset=UTF-8",
      },
      body: formdata,
    })
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        if (json.code && json.code === 0) message.success("Upload success!");
        else message.error("Upload failed!");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const props = {
    onRemove: () => {
      setFileList([]);
    },
    beforeUpload: (file) => {
      setFileList(file);
      return false;
    },
    fileList,
  };
  return (
    <div
      style={{
        justifyContent: "center",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        minWidth:'60vw',
        height:'60vh'
      }}
    >
      <div>
        <input
          onChange={(value) => setName(value.target.value)}
          placeholder="TRUE NAME"
        />
      </div>
      <div>
        <input
          onChange={(value) => {
            setMsg(value.target.value);
          }}
          placeholder="Msgs YOU WANT TO SAY"
        />
      </div>
      <div>
        <Upload {...props}>
          <Button icon={<UploadOutlined />}>Select File</Button>
        </Upload>
        <Button
          type="primary"
          onClick={()=>uploadData()}
          disabled={fileList.length === 0}
          loading={uploading}
          style={{ marginTop: 16 }}
        >
          {uploading ? "Uploading" : "Start Upload"}
        </Button>
      </div>
    </div>
  );
};

export default UploadPage;
