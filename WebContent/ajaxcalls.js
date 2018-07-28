function populatetime(str){
	
	var url = 'populatejsp.jsp';
	 var xhttp;
	 
	 xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("time_selection").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "populatejsp.jsp?q="+str, true);
	  xhttp.send();
}