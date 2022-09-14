<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex1</title>
<script>
   window.onload=start;
   function start(){
      var button = document.getElementById('button');
      button.onclick=send;
   }
   var req;
   function send(){ 
      req = new XMLHttpRequest();
      req.onreadystatechange = textChange;
      req.open('post', 'ex5');   
      req.send(null); 
   }
   function textChange(){
      if(req.readyState == 4 && req.status == 200){
         
         var jsonDatas = JSON.parse(req.responseText);
         console.log(jsonDatas);
         var data = "";
         
         for(i = 0; i < jsonDatas.cd.length; i++){
            data += "<tr>";
            data = data + "<td>" + jsonDatas.cd[i].title + "</td>";
            data = data + "<td>" + jsonDatas.cd[i].artist + "</td>";
            data = data + "<td>" + jsonDatas.cd[i].price + "</td>";
            data += "</tr>";
         }
         
         
         var tbody = document.getElementById('tbody');
         tbody.innerHTML = data;
      }
   }
</script></head>
<body>
    
   <button id="button" type="button">데이터 가져오기</button>
   <table border= 1>
      <thead>
         <tr>
            <th>title</th>
            <th>artist</th>
            <th>price</th>
         </tr>
      </thead>
      <tbody id="tbody">
         
      </tbody>
   </table>
</body>
</html>