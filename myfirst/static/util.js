
/**
 * 对象复制
 * @param {Object} obj 传入的对象参数
 */
var cloneObj = function (obj) {
    var newObj = {};  
    if (obj instanceof Array) {  
        newObj = [];  
    }  
    for (var key in obj) {  
        var val = obj[key];  
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。  
        newObj[key] = typeof val === 'object' ? cloneObj(val): val;  
    }  
    return newObj;  
}; 

/**
 * 日期格式化放阿飞
 * @param {Object} date
 * @param {Object} fmt
 */
function formatDate (date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    };
    for (let k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + '';
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
        }
    }
    return fmt;
};

function padLeftZero (str) {
    return ('00' + str).substr(str.length);
};


var imgfire=new Image();
var imghot=new Image();
var imgwater=new Image();
$(function(){
	imgfire.src = "../static/image/fireicon.png";
    imghot.src = "../static/image/hoticon.png";
	imgwater.src = "../static/image/watericon.png";	
});

/**
 * 图片名称
 * @param {Object} str
 */
function getImageByName(str){
	switch(str){
		case "imgfire":return imgfire;
		case "imghot":return imghot;
		case "imgwater":return imgwater;
	}
	
}
