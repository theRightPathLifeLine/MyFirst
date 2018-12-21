import axios from 'axios'
import qs from 'qs'
// 配置API接口地址
var root = 'http://127.0.0.1:7023'

// 返回在vue模板中的调用接口
export default {
	initWebsocket: function(onMessage) {
        let ws = new WebSocket('ws://127.0.0.1:7023/msgServer');
        ws.onopen = () => {
            // Web Socket 已连接上，使用 send() 方法发送数据
//          console.log('数据发送中...');
//          ws.send('Holle');
//          console.log('数据发送完成');
        }
        ws.onmessage = evt => {
        	evt = eval('(' + evt.data + ')');
            onMessage(evt);
        }
        ws.onclose = function () {
            // 关闭 websocket
//          console.log('连接已关闭...');
        }
     },
	get: function(url, params, success, failure) {
		return axios({
			method: 'get',
			url: url,
			params: params,
			baseURL: root
		}).then(function(res) {
			if(res.status === 200) {
				if(success) {
					success(res);
				}
			} else {
				if(failure) {
					failure(res);
				} else {
					window.alert('error: ' + JSON.stringify(res))
				}
			}
		}).catch(function(error) {
			console.log(error);
		});
	},
	post: function(url, params, success, failure) {
		return axios({
			method: 'post',
			url: url,
			data: qs.stringify(params),
			baseURL: root,
			headers:{
				'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
			}
		}).then(function(res) {
			if(res.status === 200) {
				if(success) {
					success(res);
				}
			} else {
				if(failure) {
					failure(res);
				} else {
					window.alert('error: ' + JSON.stringify(res))
				}
			}
		}).catch(function(error) {
			console.log(error);
		});
	},
	postJSONObj: function(url, params, success, failure) {
		return axios({
			method: 'post',
			url: url,
			data: params,
			baseURL: root,
			headers:{
				'Content-Type':'application/json'
			}
		}).then(function(res) {
			if(res.status === 200) {
				if(success) {
					success(res);
				}
			} else {
				if(failure) {
					failure(res);
				} else {
					window.alert('error: ' + JSON.stringify(res))
				}
			}
		}).catch(function(error) {
			console.log(error);
		});
	}
}