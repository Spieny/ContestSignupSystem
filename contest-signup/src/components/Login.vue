<script setup>
    import { ref } from 'vue'
    const username = ref('')
    const userpwd = ref('')
</script>

<template>
    <div class="loginBg">
       <div class="loginWindow">
            <div class="text">登录
                <el-input v-model="username" placeholder="请输入账号" />
                <el-input type="password" v-model="userpwd" placeholder="请输入密码" />
                <el-button type="primary">登录</el-button>
                <el-button type="primary">注册</el-button>
            </div>
            
       </div>
       <div style="text-align: center; margin-top: 700px;">
            Programed by Ziahh
        </div>
    </div>

</template>

<style scoped>
    .loginBg{
        background-color: #1D2024;
        border: 5px solid #4e5763;
        width: 100%;
        height: 100%; 
        top: 0;
        left: 0;
        margin: auto; 
        font-size: 13px;
        color: white;
        line-height: 1.5;
    }

    .loginWindow{
        margin: auto;
        top: 300px;
        position: relative;
        width: 300px;
        height: 200px;
    }

    .loginBody{
        background: #FFF;
    }

    .text{
        text-align: center;
        font-size: 28px;
        color: white;
    }

</style>