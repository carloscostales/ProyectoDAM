
$(document).ready(function() {
	$("#botonNuevo").addClass("disabled");
	$("#codigo").css("text-transform", "uppercase");
	
	$("#codigo").keyup(function(){	
		if ($("#codigo").val().length <3) {
			error="El codigo debe tener 3 letras.";
			$("#errorCodigo").show();
			$("#errorCodigo").text(error);
		}else {
			$("#errorCodigo").hide();
		}
	});
	
	$("#nombre").keyup(function(){
		if (!$("#nombre").val().length) {
			error="El nombre no puede estar vacío.";
			$("#errorNombre").show();
			$("#errorNombre").text(error);
		}else {
			$("#errorNombre").hide();
		}
	});
	
	$("input").blur(function(){
    	if($("#codigo").val().length <3 || !$("#nombre").val().length) {
    		$('#botonNuevo').addClass('disabled');
    	} else {
    	  	$('#botonNuevo').removeClass('disabled');
		}
	});
});
