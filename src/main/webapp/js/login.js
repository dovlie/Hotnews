//$(function() {
//})

$(document).ready(
		function() {

			$(".content .con_right .left").click(
					function(e) {
						$(this).css({
							"color" : "#333333",
							"border-bottom" : "2px solid #2e558e"
						});
						$(".content .con_right .right").css({
							"color" : "#999999",
							"border-bottom" : "2px solid #dedede"
						});
						$(".content .con_right ul .con_r_left").css("display",
								"block");
						$(".content .con_right ul .con_r_right").css("display",
								"none");
						if (fluCodeInterval == null
								|| fluCheckCodeInterval == null) {
							show();
							flushQRCode();
							checkQRCodeStatus();
						}
					});
			$(".content .con_right .right").click(
					function(e) {
						$(this).css({
							"color" : "#333333",
							"border-bottom" : "2px solid #2e558e"
						});
						$(".content .con_right .left").css({
							"color" : "#999999",
							"border-bottom" : "2px solid #dedede"
						});
						$(".content .con_right ul .con_r_right").css("display",
								"block");
						$(".content .con_right ul .con_r_left").css("display",
								"none");
					});
			// $('#btn_Login').click(function() {}

			$("#btn_Login").on("click", function() {
				if ($.trim($('#userid').val()) == '') {
					alert('请输入您的用户名');
					return false;
				} else if ($.trim($('#pwd').val()) == '') {
					alert('请输入密码');
					return false;
				} else if ($.trim($('#yz').val()) == '') {
					alert('请输入验证码');
					return false;
				} else {
					$("form#loginForm").ajaxForm(function(data) {
						if (data.result == "Y") {
							alert("登录成功");
							window.location.href='index.html'
						} else if (data.result == "O") {
							alert("验证码错误");
							window.location.href='login.html'
						} else if (data.result == "N") {
							alert("用户名或密码错误");
							window.location.href='login.html'
						}
					});
					//return true;
				}

			});

			$("#flushLoginValiCode1,#flushLoginValiCode2").click(
					function() {
						$("#codeImage").attr(
								"src",
								"http://localhost:8080/GDMIS/auth/code.mvc?"
										+ Math.random());
					});
		})