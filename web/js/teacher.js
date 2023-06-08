function ajaxGet(url, type, data, success, error){
    $.ajax({
        url:url,
        type:type,
        data: data,
        dataType:"json",
        success:success,
        error:error
    })
}

let addUrl = "http://localhost:8080/AddTeaServlet";
let deleteUrl = "http://localhost:8080/DeleteTeaServlet";
let updateUrl = "http://localhost:8080/UpdateTeaServlet";
let searchUrl = "http://localhost:8080/SearchTeaServlet";
let loginOutUrl = "http://localhost:8080/loginOutServlet";

let teas = [];
document.getElementById("pageNum").innerText = 1;
// document.getElementById("searchBtn").addEventListener("click",query);
function queryAll(){
    let need = {
        selectValue: 'teaId',
        searchElement: ""
    }
    console.log(need.selectValue)
    ajaxGet(searchUrl, "get", need,
        function (resp) {
            console.log(resp);
            teas = [];
            teas = resp;
            console.log(resp[0].teaAge);
            document.getElementById("nums").innerHTML = teas.length;
            display();
        },
        function (err) {
            console.log("错误", err);
        })
}

//模糊查询满足某条件的学生信息
document.getElementById("searchBtn").addEventListener("click", searchPart);
function searchPart(){
    let selectElement = document.getElementById('choice');
    let selectValue = selectElement.value;
    let searchElement = document.getElementById("search").value;
    teas = [];
    let need = {
        selectValue : selectValue,
        searchElement : searchElement
    }

    ajaxGet(searchUrl, "get", need, function (resp){
        delTable();
        console.log(resp);
        teas = resp;
        if(teas.length == 0){
            alert("没有查询到相关信息！");
        }
        console.log(resp.length);
        document.getElementById("nums").innerText = teas.length;
        number = 1;
        display();
    })
}

let page = document.getElementById('pageNum').innerHTML;
/* 页面展示 */
function display() {
    console.log(teas.length);
    let num = teas.length;
    let count = 0;
    if (num % 10 == 0)
        count = num / 10;
    else
        count = parseInt(num / 10 + 1 + "");
    document.getElementById('totalPage').innerHTML = count + "";
    if (teas.length < page * 10) {
        for (let i = (page - 1) * 10; i < teas.length; i++) {
            showTable(teas[i]);
        }
    } else{
        for (let i = (page - 1) * 10; i < page * 10; i++) {
            showTable(teas[i]);
        }
    }

}

window.onload = function () {
    queryAll();
}

//增添功能
//打开新增框架
/*新增学生*/
function add() {
    document.getElementById("addBlock").style.display = 'block';
}

document.getElementById("addSubmit").addEventListener("click",addSubmit);
//提交按钮
function addSubmit() {
    let m = true;
//获取表
    let iTable = document.getElementById('containerTable');
//获取输入值
    let teaId = document.getElementById('teaId1').value;
    let teaName = document.getElementById('teaName1').value;
    let teaSex = document.getElementById("teaSex1").value;
    let teaAge = document.getElementById('teaAge1').value;
    let teaClass = document.getElementById('teaClass1').value;
    console.log("teaId"+teaId)
    m = addInspect(teaId, teaName, teaSex, teaAge, teaClass);
    for(let i = 0; i < teas.length; i++){
        if(teaId == teas[i].teaId){
            alert('该工号已存在');
            m = false;
            break;
        }
    }
    if(m && confirm('确定保存？')){
        let teacher = {
            teaId : teaId,
            teaName : teaName,
            teaSex : teaSex,
            teaAge : teaAge,
            teaClass : teaClass,
        }
        ajaxGet( addUrl, "post", teacher, function (res){
            console.log(res)
            teas.push({
                teaId : teaId,
                teaName : teaName,
                teaSex : teaSex,
                teaAge : teaAge,
                teaClass : teaClass,
            })    // 将新增框架中的输入框值初始化
            document.getElementById('teaId1').value = null;
            document.getElementById('teaName1').value = null;
            document.getElementById('teaSex1').value = null;
            document.getElementById('teaAge1').value = null;
            document.getElementById('teaClass1').value = null;
            document.getElementById('nums').innerHTML = teas.length;
            document.getElementById('totalPage').innerHTML = Math.floor(teas.length/ 10 + 1);
            let pageNum = document.getElementById('pageNum').innerText;
            for (let i = pageNum; i <= Math.floor(nums / 10); i++) {
                yNext();
            }
            document.getElementById("addBlock").style.display = 'none';
        },function (err){
            console.log(err)
        })

        if(teas.length <= pageNums*10){
            showTable(teas[teas.length-1]);
        }
        myRefresh();
    }
}

//获取表
let iTable = document.getElementById('containerTable')
let number = 1;
document.getElementById("nums").innerText = teas.length;
//展示表格
function showTable(obj) {
    //创造新的行
    let iTr = document.createElement('tr');
    iTable.appendChild(iTr);

    //创建选择按钮
    let sel = document.createElement('input');
    sel.setAttribute('type', 'checkbox');
    sel.setAttribute('name', 'item');

    //创建表格
    let td1 = creatTd();
    td1.className = "col1";
    td1.appendChild(sel);
    let td2 = creatTd();
    td2.className = "col2";
    td2.appendChild(document.createTextNode(number));
    let td3 = creatTd();
    td3.className = "col3";
    console.log(obj)
    td3.appendChild(document.createTextNode(obj.teaId));
    let td4 = creatTd();
    td4.className = "col4";
    td4.appendChild(document.createTextNode(obj.teaName));
    let td5 = creatTd();
    td5.className = "col5";
    td5.appendChild(document.createTextNode(obj.teaSex));
    let td6 = creatTd();
    td6.className = "col6";
    td6.appendChild(document.createTextNode(obj.teaAge));
    let td7 = creatTd();
    td7.className = "col7";
    td7.appendChild(document.createTextNode(obj.teaClass));
    let td8 = creatTd();
    td8.className = "col8";
    //添加查看按钮
    let examine = document.createElement('input');
    examine.id = 'examine';
    examine.setAttribute('type', 'button');
    examine.setAttribute('value', '查看');
    examine.setAttribute('onclick', 'examine(this)');
    //添加修改按钮
    let update = document.createElement('input');
    update.id = 'update';
    update.setAttribute('type', 'button');
    update.setAttribute('value', '修改');
    update.setAttribute('onclick', 'update(this)');
    update.setAttribute('name', 'upItem');
    td8.appendChild(examine);
    td8.appendChild(update);

    //添加行
    iTr.appendChild(td1);
    iTr.appendChild(td2);
    iTr.appendChild(td3);
    iTr.appendChild(td4);
    iTr.appendChild(td5);
    iTr.appendChild(td6);
    iTr.appendChild(td7);
    iTr.appendChild(td8);

    number++;
}

//创建单元格
function creatTd() {
    let iTd = document.createElement('td');
    return iTd;
}
//取消按钮
function addCancel() {
    document.getElementById("addBlock").style.display = 'none';
}

//获取当前页数
function pageNums() {
    let pageNum = document.getElementById('pageNum').innerText;
    pageNum = parseInt(pageNum);
    return pageNum;
}

//删除功能
//全选功能
function checkAll(obj) {
    if (obj === undefined) {
        obj = document.getElementById("checkAll");
    }
    let status = obj.checked;
    //返回带有指定名称的对象的集合
    let items = document.getElementsByName('item');
    for (let i = 0; i < items.length; i++) {
        items[i].checked = status;
        items[i].addEventListener('click', function () {
            if (items[i].checked === false) {
                obj.checked = false;
            }
        })
    }
    if (!obj.checked) {
        for (let i = 0; i < items.length; i++) {
            items[i].addEventListener('click', function () {
                let check;
                for (let j = 0; j < items.length; j++) {
                    check = true;
                    if (items[j].checked === false) {
                        check = false;
                        break;
                    }
                }
                obj.checked = check;
            })
        }
    }
}

//删除按钮
function remove() {
    let items = document.getElementsByName('item');
    let pageNum = document.getElementById('pageNum').innerText;
    if(confirm("是否确定删除？")){
        for (let j = 0 ; j < items.length; j++){
            //判断是否勾选
            if(items[j].checked == true){
                let id = teas[j+(pageNum-1)*10].teaId;
                let teacherId = {
                    teaId : id
                }
                teas.splice(j+(pageNum-1)*10,1);
                ajaxGet(deleteUrl,"get",teacherId,function (resp){
                    if(resp){
                        delTable();
                        document.getElementById("nums").innerText = teas.length;
                        display();
                        if (teas.length % 10 == 0) {
                            delTable();
                            if (pageNum > 1)
                                pageNum--;
                            display();
                            document.getElementById('pageNum').innerText = pageNum;
                        }
                        number = (pageNum - 1) * 10 + 1;
                        myRefresh();
                    }else {
                        alert("未成功删除！")
                    }
                    console.log(resp);
                },function (err){
                    console.log("错误",err);
                })
            }
        }

    }
}

//自动刷新
function myRefresh() {
    window.location.reload();
}

//查看学生信息
function examine(obj) {
    document.getElementById('exBlock').style.display = 'block';
    //获取当前行
    let iTr = obj.parentNode.parentNode;
    //获取当前行所有单元格
    let iTds = iTr.getElementsByTagName('td');
    document.getElementById('teaId3').value = iTds[2].innerText;
    document.getElementById('teaName3').value = iTds[3].innerText;
    document.getElementById('teaSex3').value = iTds[4].innerText;
    document.getElementById('teaAge3').value = iTds[5].innerText;
    document.getElementById('teaClass3').value = iTds[6].innerText;
}

function exCancel() {
    document.getElementById('exBlock').style.display = 'none';
}

//修改学生信息
let n;
function update(obj) {
    document.getElementById('upBlock').style.display = 'block';
    //获取当前行
    let iTr = obj.parentNode.parentNode;
    //获取当前行所有单元格
    let iTds = iTr.getElementsByTagName('td');
    document.getElementById('teaId2').value = iTds[2].innerText;
    document.getElementById('teaName2').value = iTds[3].innerText;
    document.getElementById('teaSex2').value = iTds[4].innerText;
    document.getElementById('teaAge2').value = iTds[5].innerText;
    document.getElementById('teaClass2').value = iTds[6].innerText;
    n = iTds;
}

//提交按钮
function upSubmit() {
    //鼠标激活的单元格
    let id2 = document.getElementById('teaId2').value;
    let name2 = document.getElementById('teaName2').value;
    let sex2 = document.getElementById('teaSex2').value;
    let age2 = document.getElementById('teaAge2').value;
    let class2 = document.getElementById('teaClass2').value;
    let prove = inspect(name2, sex2, age2, class2);
    if(prove == true && confirm('确定保存吗？')) {
        //获取当前行
        let iTds = n;
        let oldId = iTds[2].innerText;

        if (oldId == id2 || isExit(id2)) {
            let teacherUpdate = {
                oldId: oldId,
                teaId: id2,
                teaName: name2,
                teaSex: sex2,
                teaAge: age2,
                teaClass: class2
            }
            ajaxGet(updateUrl, "get", teacherUpdate, function (res) {
                if(res > 0){
                    iTds[2].innerText = id2;
                    iTds[3].innerText = name2;
                    iTds[4].innerText = sex2;
                    iTds[5].innerText = age2;
                    iTds[7].innerText = class2;
                }else
                    alert("修改教师信息未成功！");
            }, function (err) {
                console.log(err)
            })
        } else {
            alert('该工号已存在！请修改！');
        }
    }
    document.getElementById('upBlock').style.display = 'none';
}

//判断该学号是否存在
function isExit(id){
    for(let i = 0; i < teas.length ; i++){
        if(id == teas[i].teaId)
            return false;
    }
    return true;
}

//取消按钮
function upCancel() {
    document.getElementById('upBlock').style.display = 'none';
}
//页码
function yPrevious() {
    debugger
    let pageNum = document.getElementById('pageNum').innerText;
    pageNum = parseInt(pageNum);
    if (pageNum == 1)
        window.alert('当前为第一页，无法往前翻页')
    if (pageNum > 1) {
        number = (pageNum-2)*10+1;
        delTable();
        document.getElementById('pageNum').innerHTML = pageNum - 1 + "";
        let count = 0;
        for (let i = (pageNum-2)*10;i < teas.length; i++){
            count++;
            if(count == 10)
                continue;
            showTable(teas[i]);
        }

    }
}
function yNext() {
    let pageSum = document.getElementById('totalPage').innerText;
    let pageNum = document.getElementById('pageNum').innerText;
    pageSum = parseInt(pageSum);
    pageNum = parseInt(pageNum);
    if (pageNum < pageSum) {
        document.getElementById('pageNum').innerHTML = pageNum + 1;
        delTable();
        let count = 0;
        for(let i = pageNum * 10; i < teas.length;i++){
            count++;
            if(count == 10)
                continue;
            showTable(teas[i]);
        }
    } else {
        window.alert('当前为最后一页，无法向后翻页')
    }
}

//清除表格
function delTable() {
    let iTable = document.getElementById('containerTable');
    let rows = iTable.getElementsByTagName("tr");
    let len = rows.length
    for (let i = 1; i < len; i++) {
        iTable.removeChild(rows[1]);
    }
}


//注销功能
document.getElementById("loginOut").addEventListener("click", logout);
function logout(){
    if(confirm('确定退出吗？')){
        ajaxGet(loginOutUrl,"get",null,function (res){
            console.log(res);
            window.location.href="../loginOut.jsp";
        },function (err){
            console.log(err);
        })
    }
}

//返回上一页
document.getElementById("back").addEventListener("click",back);
function back(){
    window.history.go(-1);
}

//表单验证
function addInspect(id1, name1, sex, age, class1) {
    return judgeId(id1) && judgeAge(age) && judgeClass(class1) && judgeName(name1) && judgeSex(sex);
}

function inspect(name1, sex, age, class1) {
    return judgeAge(age) && judgeClass(class1) && judgeName(name1) && judgeSex(sex);
}

function judgeId(teaId){
    for (let i=0;i<teas.length;i++)
    {
        if(teas[i].teaId===teaId) {
            alert("输入学号已存在");
            return false;
        }
        else {
            let z = /^\d{6}$/;
            if (!teaId.match(z)) {//匹配函数
                alert("请输入正确的教师编号（6位数字）");
                return false;
            }

        }
    }
    return true;
}

function judgeAge(teaAge){
    let z = /^[1-9][0-9]$/;
    if(!z.test(teaAge)) {
        alert("年龄输入格式错误");
        return false;
    }
    else
        return true;
}

function judgeClass(teaClass){
    let z = /^[1-6]$/;
    if(!z.test(teaClass)) {
        alert("班级输入格式错误");
        return false;
    }
    else
        return true;
}

function judgeName(teaName){
    let reg = new RegExp("[\\u4E00-\\u9FFF]+", "g");
    if (teaName.length < 1 || teaName.length > 6) {
        alert("姓名格式错误!");
        return false;
    }
    else {
        if (!reg.test(teaName)) {
            alert("姓名只能为汉字!");
            return false;
        }
        else
            return true;
    }
}

function judgeSex(teaSex){
    let z =/^(男|女)$/;
    if(!z.test(teaSex)) {
        alert("性别输入格式错误，只能为男或女");
        return false;
    }
    else
        return true;
}