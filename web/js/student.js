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

let addUrl = "http://localhost:8080/AddStuServlet";
let deleteUrl = "http://localhost:8080/DeleteStuServlet";
let updateUrl = "http://localhost:8080/UpdateStuServlet";
let searchUrl = "http://localhost:8080/SearchStuServlet";
let loginOutUrl = "http://localhost:8080/loginOutServlet";
let findClassUrl = "http://localhost:8080/findClassServlet";

let stus = [];
document.getElementById("pageNum").innerText = 1;
// document.getElementById("searchBtn").addEventListener("click",query);
function queryAll(){
    let need = {
        selectValue: 'stuId',
        searchElement: ""
    }
    console.log(need.selectValue)
    ajaxGet(searchUrl, "get", need,
        function (resp) {
            console.log(resp);
            stus = [];
            stus = resp;
            console.log(resp[0].stuAge);
            document.getElementById("nums").innerHTML = stus.length;
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
    stus = [];
    let need = {
        selectValue : selectValue,
        searchElement : searchElement
    }

    ajaxGet(searchUrl, "get", need, function (resp){
        delTable();
        console.log(resp);
        stus = resp;
        if(stus.length == 0){
            alert("没有查询到相关信息！");
        }
        console.log(resp.length);
        document.getElementById("nums").innerText = stus.length;
        number = 1;
        display();
    })
}

let page = document.getElementById('pageNum').innerHTML;
/* 页面展示 */
function display() {
    console.log(stus.length);
    let num = stus.length;
    let count = 0;
    if (num % 10 == 0)
        count = num / 10;
    else
        count = parseInt(num / 10 + 1 + "");
    document.getElementById('totalPage').innerHTML = count + "";
    if (stus.length < page * 10) {
        for (let i = (page - 1) * 10; i < stus.length; i++) {
            showTable(stus[i]);
        }
    } else{
        for (let i = (page - 1) * 10; i < page * 10; i++) {
            showTable(stus[i]);
        }
    }

}

window.onload = function () {
    queryAll();
    getStudent1();
    getStudent2();
}

//获取课程的数据
function getStudent1() {
    let lesson = [];
    let sel = document.getElementById("stuClass1");
    ajaxGet(findClassUrl,"get",null,function (resp){
        console.log("find",resp)
        lesson = resp;
        for(let i = 0; i < lesson.length; i++){
            let opt = new Option(lesson[i].classId,lesson[i].classId);
            sel.options.add(opt);
            console.log(lesson[i].classId);
        }
    })
}

function getStudent2() {
    let student = [];
    let sel = document.getElementById("stuClass2");
    ajaxGet(findClassUrl,"get",null,function (resp){
        student = resp;
        console.log(student)
        for(let i = 0; i < student.length; i++){
            let opt = new Option(student[i].classId,student[i].classId);
            sel.options.add(opt);
            console.log(student[i].classId);
        }
    })
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
    let stuId = document.getElementById('stuId1').value;
    let stuName = document.getElementById('stuName1').value;
    let stuSex = document.getElementById("stuSex1").value;
    let stuAge = document.getElementById('stuAge1').value;
    let stuGrade = document.getElementById('stuGrade1').value;
    let stuClass = document.getElementById('stuClass1').value;
    console.log("stuId"+stuId)
    m = addInspect(stuId, stuName, stuSex, stuAge, stuGrade, stuClass);
    console.log(m)
    console.log("m"+stuId);
    for(let i = 0; i < stus.length; i++){
        if(stuId == stus[i].stuId){
            alert('该学号已存在');
            m = false;
            break;
        }
    }
    if(m && confirm('确定保存？')){
        let student = {
            stuId : stuId,
            stuName : stuName,
            stuSex : stuSex,
            stuAge : stuAge,
            stuGrade : stuGrade,
            stuClass :stuClass,
        }

        console.log("student" + student)
        ajaxGet( addUrl, "post", student, function (res){
            console.log(res)
            stus.push({
                stuId : stuId,
                stuName : stuName,
                stuSex : stuSex,
                stuAge : stuAge,
                stuGrade : stuGrade,
                stuClass : stuClass,
            })    // 将新增框架中的输入框值初始化
            document.getElementById('stuId1').value = null;
            document.getElementById('stuName1').value = null;
            document.getElementById('stuSex1').value = null;
            document.getElementById('stuAge1').value = null;
            document.getElementById('stuGrade1').value = null;
            document.getElementById('stuClass1').value = null;
            document.getElementById('nums').innerHTML = stus.length;
            document.getElementById('totalPage').innerHTML = Math.floor(stus.length/ 10 + 1);
            let pageNum = document.getElementById('pageNum').innerText;
            for (let i = pageNum; i <= Math.floor(nums / 10); i++) {
                yNext();
            }
            document.getElementById("addBlock").style.display = 'none';
        },function (err){
            console.log(err)
        })

        if(stus.length <= pageNums*10){
            showTable(stus[stus.length-1]);
        }
        myRefresh();
    }
}

//获取表
let iTable = document.getElementById('containerTable')
let number = 1;
document.getElementById("nums").innerText = stus.length;
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
    td3.appendChild(document.createTextNode(obj.stuId));
    let td4 = creatTd();
    td4.className = "col4";
    td4.appendChild(document.createTextNode(obj.stuName));
    let td5 = creatTd();
    td5.className = "col5";
    td5.appendChild(document.createTextNode(obj.stuSex));
    let td6 = creatTd();
    td6.className = "col6";
    td6.appendChild(document.createTextNode(obj.stuAge));
    let td7 = creatTd();
    td7.className = "col7";
    td7.appendChild(document.createTextNode(obj.stuGrade));
    let td8 = creatTd();
    td8.className = "col8";
    td8.appendChild(document.createTextNode(obj.stuClass));
    let td9 = creatTd();
    td9.className = "col9";
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
    td9.appendChild(examine);
    td9.appendChild(update);

    //将单元行添加到行中
    iTr.appendChild(td1);
    iTr.appendChild(td2);
    iTr.appendChild(td3);
    iTr.appendChild(td4);
    iTr.appendChild(td5);
    iTr.appendChild(td6);
    iTr.appendChild(td7);
    iTr.appendChild(td8);
    iTr.appendChild(td9);

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
                let id = stus[j+(pageNum-1)*10].stuId;
                let studentId = {
                    stuId : id
                }
                stus.splice(j+(pageNum-1)*10,1);
                ajaxGet(deleteUrl,"get",studentId,function (resp){
                    if(resp){
                        delTable();
                        document.getElementById("nums").innerText = stus.length;
                        display();
                        if (stus.length % 10 == 0) {
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
    document.getElementById('stuId3').value = iTds[2].innerText;
    document.getElementById('stuName3').value = iTds[3].innerText;
    document.getElementById('stuSex3').value = iTds[4].innerText;
    document.getElementById('stuAge3').value = iTds[5].innerText;
    document.getElementById('stuGrade3').value = iTds[6].innerText;
    document.getElementById('stuClass3').value = iTds[7].innerText;
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
    document.getElementById('stuId2').value = iTds[2].innerText;
    document.getElementById('stuName2').value = iTds[3].innerText;
    document.getElementById('stuSex2').value = iTds[4].innerText;
    document.getElementById('stuAge2').value = iTds[5].innerText;
    document.getElementById('stuGrade2').value = iTds[6].innerText;
    document.getElementById('stuClass2').value = iTds[7].innerText;
    n = iTds;
}

//提交按钮
function upSubmit() {
    //鼠标激活的单元格
    debugger
    let id2 = document.getElementById('stuId2').value;
    let name2 = document.getElementById('stuName2').value;
    let sex2 = document.getElementById('stuSex2').value;
    let age2 = document.getElementById('stuAge2').value;
    let grade2 = document.getElementById('stuGrade2').value;
    let class2 = document.getElementById('stuClass2').value;
    let prove = inspect(name2, sex2, age2, grade2, class2);
    if(prove == true && confirm('确定保存吗？')) {
        //获取当前行
        let iTds = n;
        let oldId = iTds[2].innerText;

        if (oldId == id2 || isExit(id2)) {
            let studentUpdate = {
                oldId: oldId,
                stuId: id2,
                stuName: name2,
                stuSex: sex2,
                stuAge: age2,
                stuGrade: grade2,
                stuClass: class2
            }
            ajaxGet(updateUrl, "get", studentUpdate, function (res) {
                if(res > 0){
                    iTds[2].innerText = id2;
                    iTds[3].innerText = name2;
                    iTds[4].innerText = sex2;
                    iTds[5].innerText = age2;
                    iTds[6].innerText = grade2;
                    iTds[7].innerText = class2;
                }else
                    alert("修改学生信息未成功！");
            }, function (err) {
                console.log(err)
            })
        } else {
            alert('该学号学生已存在！请修改！');
        }
    }
    document.getElementById('upBlock').style.display = 'none';
}

//判断该学号是否存在
function isExit(id){
    for(let i = 0; i < stus.length ; i++){
        if(id == stus[i].stuId)
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
    let pageNum = document.getElementById('pageNum').innerText;
    pageNum = parseInt(pageNum);
    if (pageNum == 1)
        window.alert('当前为第一页，无法往前翻页')
    if (pageNum > 1) {
        number = (pageNum-2)*10+1;
        delTable();
        document.getElementById('pageNum').innerHTML = pageNum - 1 + "";
        let count = 0;
        for (let i = (pageNum-2)*10;i < stus.length; i++){
            count++;
            if(count == 10)
                continue;
            showTable(stus[i]);
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
        for(let i = pageNum * 10; i < stus.length;i++){
            count++;
            if(count == 10)
                continue;
            showTable(stus[i]);
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

//返回上一页
document.getElementById("back").addEventListener("click",back);
function back(){
    window.history.go(-1);
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



//表单验证
function addInspect(id1, name1, sex, age, grade, class1) {
    return judgeId(id1) && judgeAge(age) && judgeClass(class1) && judgeGrade(grade) && judgeName(name1) && judgeSex(sex);
}

function inspect(name1, sex, age, grade, class1) {
    return judgeAge(age) && judgeClass(class1) && judgeGrade(grade) && judgeName(name1) && judgeSex(sex);
}

function judgeId(stuId){
    for (let i=0;i<stus.length;i++)
    {
        if(stus[i].stuId===stuId) {
            alert("输入学号已存在");
            return false;
        }
        else {
            let z = /^\d{11}$/;
            if (!stuId.match(z)) {//匹配函数
                alert("请输入正确的学生学号（11位数字）");
                return false;
            }

        }
    }
    return true;
}

function judgeAge(stuAge){
    let z = /^[1-9][0-9]$/;
    if(!z.test(stuAge)) {
        alert("年龄输入格式错误");
        return false;
    }
    else
        return true;
}

function judgeClass(stuClass){
    let z = /^[1-6]$/;
    if(!z.test(stuClass)) {
        alert("班级输入格式错误");
        return false;
    }
    else
        return true;
}

function judgeGrade(stuGrade){
    let z = /^\d{4}$/;
    if(!z.test(stuGrade)) {
        alert("年级输入格式错误");
        return false;
    }
    else
        return true;
}

function judgeName(stuName){
    let reg = new RegExp("[\\u4E00-\\u9FFF]+", "g");
    if (stuName.length < 1 || stuName.length > 6) {
        alert("姓名格式错误!");
        return false;
    }
    else {
        if (!reg.test(stuName)) {
            alert("姓名只能为汉字!");
            return false;
        }
        else
            return true;
    }
}

function judgeSex(stuSex){
    let z =/^(男|女)$/;
    if(!z.test(stuSex)) {
        alert("性别输入格式错误，只能为男或女");
        return false;
    }
    else
        return true;
}
