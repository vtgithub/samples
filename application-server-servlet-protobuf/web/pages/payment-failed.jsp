<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="fa">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="${cp}/pages/css/style.css">
    <style type="text/css">
    
    .big-text {        
        height: 20px;
        font-family: IRANSans;
        font-size: 18px;
        font-weight: 400;
        text-align: center;
        color: #444444;        

    }
            .small-text {
        width: 300px;
        height: 42px;
        font-family: IRANSans;
        font-size: 12px;
        text-align: center;
        color: #888888;        
    }
    .bank-transfer-failed {
	width: 136px;
	height: 120px;
	object-fit: contain;
}
    .code {	
	height: 19px;
	font-family: IRANSans;
	font-size: 12px;
	text-align: center;
	color: #019875;
}
.amnt {	
	
	font-family: IRANSans;
	font-size: 32px;
	font-weight: bold;
	text-align: center;
	color: #444444;	
}
.amnt-unit {
	
	font-family: IRANSans;
	font-size: 16px;
	text-align: center;
	color: #444444;

}
    .ret-btn {
	width: 150px;
	height: 32px;
	background-color: #88b04b;
    border-radius: 16px;
    display:table-cell;    /*Add this*/
  vertical-align:middle;
}
.cta {
	width: 150px;
	height: 19px;
	font-family: IRANSans;
	font-size: 14px;
	font-weight: 500;
	text-align: center;
	color: #ffffff;
    text-decoration: none !important;
        
}
.line {
	width: 208px;
	height: 2px;
	border-top: dotted 2px #dcdcdc;	
}
    .outer {
    display: table;
    position: absolute;
    height: 100%;
    width: 100%;
}

.middle {
    display: table-cell;
    vertical-align: middle;
}

.inner {
    margin-left: auto;
    margin-right: auto; 
    /*width: !*whatever width you want*!;*/
}
    </style>
<title>پرداخت ناموفق</title>
</head>

<body dir="rtl">
    <div class="outer">
  <div class="middle">
    <div class="inner">
        <center>
<img src="${cp}/pages/img/bank-transfer-failed.png"
		 srcset="${cp}/pages/img/bank-transfer-failed@2x.png 2x,
					 ${cp}/pages/img/bank-transfer-failed@3x.png 3x"
		 class="bank-transfer-failed">
<p class="big-text">
    ${message}
</p>

<p class="small-text">
    ${bodyMessage}
</p>
<div class="ret-btn">
    
    <a href="ord://charge?id=${orderId}" class="cta">
    بازگشت به اپلیکیشن
    </a>    
</div>

        </center>
    </div>
  </div>
</div>

</body>
</html>
