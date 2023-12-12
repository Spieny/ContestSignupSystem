<script setup>
  import { ref } from "vue"

  function logout(){
    alert("登出")
  }

  let username = ref("")

  username.value = "管理员"

</script>

<template>
  <div class="header">
    <div class="logo">广东海洋大学 比赛报名系统</div>
    <div class="logout">
      <el-button @click="logout()">登出</el-button>
    </div>
    <div class="userInfo">欢迎你: {{ username }}</div>
  </div>

</template>

<style scoped>
    .header{
      top:0;
      left: 0;
      position: fixed;
	    box-sizing: border-box;
    	width: 100%;
	    height: 65px;
	    font-size: 22px;
	    color: #fff;
      background-color: #242f42;
      display: block;
    }
    .logo{
      padding-left: 15px;
      padding-top: 5px;
      font-weight: bold;
      float:left;
      font-size: 38px;
      color:white;
    }

    .userInfo{
      padding-top: 15px;
      padding-right: 15px;
      margin: auto;
      float: right;
    }

    .logout{
      padding-top: 15px;
      padding-right: 15px;
      float: right;
    }
</style>