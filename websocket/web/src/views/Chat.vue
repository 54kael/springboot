<template>
  <div>
    <table>
      <tr>
        <td>姓名:</td>
        <td>
          <input v-model="sendName" />
        </td>
        <td>
          <button :disabled="sendName==''" @click="connect">连接</button>
        </td>
        <td>
          <button @click="disconnect">断开</button>
        </td>
      </tr>
      <tr>
        <td>发给:</td>
        <td>
          <select v-model="message.to">
            <option value="嬴政">嬴政</option>
            <option value="赵高">赵高</option>
            <option value="李斯">李斯</option>
            <option value="所有">所有</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>消息:</td>
        <td>
          <input v-model="message.content" />
        </td>
        <td>
          <button :disabled="connectFlag" @click="sendMessage">发送</button>
        </td>
      </tr>
    </table>
    <div v-for="(data,index) in dataList" :key="index">
      {{data.from}}："{{data.content}}"      <span style="margin-left:30px">{{data.date}}</span>
    </div>
  </div>
</template>
<script>
import SockJS from "sockjs-client";
import Stomp from "stompjs";
export default {
  data() {
    return {
      dataList: [],
      sendName: "",
      message: {
        to: "",
        content: "",
        date:""
      },
      connectFlag: true,
    };
  },
  beforeDestroy() {
    // 页面离开时断开连接,清除定时器
    this.disconnect();
    clearInterval(this.timer);
  },
  methods: {
    disconnect() {
      this.stompClient.disconnect(() => {
        console.log("断开");
      });
    },
    connect() {
      let socket = new SockJS(
        "http://192.168.0.105:8090/gs-guide-websocket?token=" + this.sendName
      );
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({ token: this.sendName }, () => {
        this.connectFlag = false;
        this.stompClient.subscribe("/topic/chatting", (greeting) => {
          this.showMsg(JSON.parse(greeting.body));
        });
        this.stompClient.subscribe("/user/queue/chatting", (greeting) => {
          this.showMsg(JSON.parse(greeting.body));
        });
        
      });
    },
    sendMessage() {
      let d = new Date();
      var year = d.getFullYear();
      var month = d.getMonth()+1;
      var date = d.getDate();
      var hours = d.getHours();
      var minutes = d.getMinutes();
      var seconds = d.getSeconds();
      let currentDate = year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
      this.message.date = currentDate;
      var destinatioin;
      if (this.message.to === "所有") {
        destinatioin = "/app/group";
      } else {
        destinatioin = "/app/single";
      }

      this.stompClient.send(
        destinatioin,
        {},
        JSON.stringify(this.message)
      );
    },
    showMsg(data) {
      this.dataList.push(data);
    },
  },
};
</script>