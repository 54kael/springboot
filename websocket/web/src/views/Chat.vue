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
          <button  @click="disconnect">断开</button>
        </td>
      </tr>
      <tr>
        <td>消息:</td>
        <td>
          <input v-model="message" />
        </td>
        <td>
          <button :disabled="connectFlag" @click="sendMessage">发送</button>
        </td>
      </tr>
    </table>
    <div v-for="(data,index) in dataList" :key="index">{{data.from}}："{{data.content}}"</div>
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
      message: "",
      connectFlag: true,
    };
  },
  beforeDestroy() {
    // 页面离开时断开连接,清除定时器
    this.disconnect();
    clearInterval(this.timer);
  },
  methods: {
    disconnect(){
      this.stompClient.disconnect(()=>{
        console.log("断开")
      })
    },
    connect() {
      let socket = new SockJS("http://192.168.0.105:8090/gs-guide-websocket");
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({token:this.sendName}, () => {
        this.connectFlag = false;
        this.stompClient.subscribe("/topic/greetings", (greeting) => {
          this.showMsg(JSON.parse(greeting.body));
        });
      });
    },
    sendMessage() {
      this.stompClient.send(
        "/app/hello",
        {token:"aa"},

        JSON.stringify({
          content: this.message,
        })
      );
    },
    showMsg(data) {
      this.dataList.push(data);
    },
  },
};
</script>