import {createRouter,createWebHashHistory} from 'vue-router'

/* 导入组件 */
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import List from '../components/List.vue'

/* 创建路由 */
const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/",
            component:List,
            meta:{
                title:'系统首页',
            }
        },
        {
            path:"/list",
            component:List,
            meta:{
                title:'系统首页',
            }
        },
        {
            path:"/login",
            component:Login,
            meta:{
                title:'登录界面',
            }
        },
        {
            path:"/register",
            component:Register,
            meta:{
                title:'注册界面',
            }
        },
    ]
})

router.beforeEach((to, from, next) => {
    if(to.path == '/login' || to.path == '/register'){
        next()
    } else {
        let username = sessionStorage.getItem("username")
        if(null != username){
            next();
        } else {
            next('/login')
        }
    }
    
});

//不要忘记加这一行
export default router