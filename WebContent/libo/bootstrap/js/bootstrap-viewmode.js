function ViewMode(data){
	this.data = data;
	this.addOrjian = 0;
	this.year = null;
	this.month = null;
	this.week = null;
	this.endDate = "";
	this.setEndDate = function(str){
		this.endDate = str;
	}
	this.setAddOrjian = function(obj){
		if(obj.getAttribute("sheng") == "jian"){
			this.addOrjian = -1;
		}
		else if(obj.getAttribute("sheng") == "jia"){
			this.addOrjian = 1;
		}
		else{
			this.addOrjian = 0;
		}
	}
	this.setYear = function(str){
		this.year = parseInt(str.substr(0,4));
	}
	this.setMonth = function(str){
		var yue = str.substr(6, str.length);
		this.month = yue.substr(0, yue.length-1);
	}
	this.setWeek = function(obj){
		var tempZhou = obj.childNodes[0].innerHTML; 
		var index = tempZhou.indexOf('周');
			tempZhou = tempZhou.substr(0, index)
			this.week = tempZhou;
	}
}

ViewMode.prototype = {
	fillWeek : function(year, monthIndex, html){
			var _week = null;
			var _weekLength = 0; //当月有多少个周
			var _weekOther = 0; //12宫格剩余多少格子
			var first = 0;
			var last = 0;
			var index = 0; //记住选中年的索引号
			var obj = null; //当年
		
			for(var i = 0; i < this.data.length; i++){
				if(year == this.data[i]["year"]){
					obj = this.data[i]; 
					index = i;
					break;
				}
			}
			
			if(obj == null){
					return false;
			}
			else{  
				_week = obj.month[monthIndex].week; //指定当月的周数据
	
				//固定3行4列
				_weekLength = _week.length; //当月有多少个周
				_weekOther = 12 - _weekLength; //剩余多少周
				if(_weekOther % 2 === 0){
					last = first = _weekOther / 2;
				}
				else{
					first = Math.ceil(_weekOther / 2); //向上取整  前面填几格
					last = first - 1; //后面填几格
				}

				//取前后数据
		
				var yearObj1 = null; //前1年
				var yearObj2 = null; //后1年
		
				var _month1 = null; //当年的前一个月
				var _month2 = null; //当年的后一个月



				if(index > 0 && monthIndex == 0){
					yearObj1 = this.data[index - 1]; //前1年
					_month1 = yearObj1.month[11]; //前1年的最后一个月
					_month2 = obj.month[monthIndex + 1]; //当年的后一个月
				}
				else if(index < (this.data.length-1) && monthIndex == 11){
					yearObj2 = this.data[index + 1]; //后1年
					_month1 = obj.month[monthIndex - 1]; //当年的前一个月
					_month2 = yearObj2.month[0]; //后一年第一个月
					
				}
				else if(index >= 0 && index <= this.data.length){
					 _month1 = obj.month[monthIndex - 1]; //当年的前一个月
					 _month2 = obj.month[monthIndex + 1]; //当年的后一个月
				}
				else{
					 return false;;
				}
				
				if(_month1 == null || _month2 == null){
					return false;
				}
				
				var s_week1 = "";
				var s_week2 = "";
				var s_week3 = "";
				var s_week4 = "";

				var e_week1 = "";
				var e_week2 = "";
				var e_week3 = "";
				var e_week4 = "";

				s_week1 = _month1["week"][_month1["week"].length - 1];
				s_week2 = _month1["week"][_month1["week"].length - 2];
				s_week3 = _month1["week"][_month1["week"].length - 3];
				s_week4 = _month1["week"][_month1["week"].length - 4];

				e_week1 = _month2["week"][0];
				e_week2 = _month2["week"][1];
				e_week3 = _month2["week"][2];
				e_week4 = _month2["week"][3];
	
				var endYear = this.endDate.split("-")[0];
				var endMonth = parseInt(this.endDate.split("-")[1]) - 1;
				var className = ""
				if(endYear == obj.year && endMonth == monthIndex){
					className = "dayno"
				}
				else{
					className = "day"
				}
				if(_weekLength == 6){
					html.push('<tr>'); 
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week3+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week2+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week1+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[0]+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>'); 
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[1]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[2]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[3]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[4]+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>'); 
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[5]+"周") + '</div></td>');
					
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week1+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week2+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week3+"周") + '</div></td>');
					html.push('</tr>'); 
				}
				else if(_weekLength == 5){
					html.push('<tr>'); 
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week4+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week3+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week2+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week1+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>'); 
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[0]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[1]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[2]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[3]+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[4]+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week1+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week2+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week3+"周") + '</div></td>');
					html.push('</tr>'); 
				}
				else if(_weekLength == 4){  
					html.push('<tr>'); 
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week4+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week3+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week2+"周") + '</div></td>');
					html.push('<td sheng="jian" class="day"><div style="color:#969696;">'+(s_week1+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>'); 
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[0]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[1]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[2]+"周") + '</div></td>');
					html.push('<td sheng="" class="day"><div style="color:#262626;">'+(_week[3]+"周") + '</div></td>');
					html.push('</tr>'); 
					html.push('<tr>'); 
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week1+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week2+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week3+"周") + '</div></td>');
					html.push('<td sheng="jia" class='+className+'><div style="color:#969696;">'+(e_week4+"周") + '</div></td>');
					html.push('</tr>'); 
				}
			}
		return true;
	},

	processValue : function(){
			var formated = "";
			var year = "";
			var month = "";
			var week = "";
			if(this.addOrjian == -1){ 
				if(this.month == 1){
					year = parseInt(this.year) - 1;
					month = "12";
				}
				else{
					year = parseInt(this.year); 
					month = (parseInt(this.month) -1)+""; 
					if(month.length == 1){
						month = "0"+month;
					}
				}
				this.week = this.week.length == 1 ? "0"+this.week : this.week;
				formated = year + "-" + month + "-" +   this.week;
			}
			else if(this.addOrjian == 1){
				if(this.month == 12){
					year = parseInt(this.year) + 1;
					month = "01"; 
				}
				else{
					year = parseInt(this.year);
					month = (parseInt(this.month) + 1)+"";
					if(month.length == 1){
						month = "0"+month;
					}
				}
				this.week = this.week.length == 1 ? "0"+this.week : this.week;
				formated = year + "-" + month + "-" + this.week;
			}
			else{
				this.week = this.week.length == 1 ? "0"+this.week : this.week;
				this.month = this.month.length == 1 ? "0"+this.month : this.month;
						
				formated = this.year + "-" + this.month + "-" + this.week; 
			}
			return formated;
	},
	
	clickTD : function(obj){ 
		if(obj.getAttribute("class") == "dayno"){
			return false
		}
		this.setAddOrjian(obj);
		this.setWeek(obj);
		var ny = document.getElementById("ny").innerHTML; 
		this.setMonth(ny);
		this.setYear(ny);
		return true;
	},
	
	clickNEXT : function(obj){
		var browser = navigator.appName;
		var flag = false
		if(browser == "Microsoft Internet Explorer"){
			if(obj.parentElement.parentElement.parentElement.parentElement.getAttribute("class") == 'datepicker-days'){
				flag = true; 
			}
		}
		else{
			if(obj.parentNode.parentNode.parentNode.parentNode.getAttribute("class") == 'datepicker-days'){
				flag = true;
			}
		}
		return flag;
	}
}