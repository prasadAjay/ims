
$(document).ready(function() {
	$("#homepage").hide();
	$("#login").modal();
	
});

function close(){
	$("#login").modal('hide');
	$("#homepage").show();
}

$(document).ready(function() {
	$("#btnSubmit").click(function(event) {
		var x = document.getElementById("uploadsuccess");
		var y = document.getElementById("uploadfailure");

		//stop submit the form, we will post it manually.
		event.preventDefault();

		// Get form
		var form = $('#uploadform')[0];

		// Create an FormData object 
		var data = new FormData(form);

		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "/uploadcsv",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			success : function(data) {
				y.style.display = "none";
				x.style.display = "block";
				$('#choosefile').val("");

			},
			error : function(e) {
				x.style.display = "none";
				y.style.display = "block";

			}
		});

	});

});