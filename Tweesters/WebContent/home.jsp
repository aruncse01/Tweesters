<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAgrj58PbXr2YriiRDqbnL1RSqrCjdkglBijPNIIYrqkVvD1R4QxRl47Yh2D_0C1l5KXQJGrbkSDvXFA" type="text/javascript"></script>
<script type="text/javascript">
 function load() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map"));
        map.addControl(new GSmallMapControl());
        map.addControl(new GMapTypeControl());
        var center = new GLatLng('40.732430','-73.974470');
        map.setCenter(center, 15);
        geocoder = new GClientGeocoder();
        var marker = new GMarker(center, {draggable: true});  
        map.addOverlay(marker);
        document.getElementById("lat").value = center.lat().toFixed(5);
        document.getElementById("lng").value = center.lng().toFixed(5);

	
	 GEvent.addListener(map, "moveend", function() {
		map.clearOverlays();
		var center = map.getCenter();
		var marker = new GMarker(center, {draggable: true});
		map.addOverlay(marker);
		document.getElementById("lat").value = center.lat().toFixed(5);
        document.getElementById("lng").value = center.lng().toFixed(5);

	 GEvent.addListener(marker, "dragend", function() {
		var point =marker.getPoint();
	    map.panTo(point);
		document.getElementById("lat").value = center.lat().toFixed(5);
        document.getElementById("lng").value = center.lng().toFixed(5);

        });
 
        });
		
      }
	  
    }

</script>



</head>
<body onload="load()" onunload="GUnload()" >
<form action="TwitterServlet" method="post">
Select State:
<select name="State"> 
<option value="" selected="selected">Select a State</option> 
<option value="Alabama">Alabama</option> 
<option value="Alaska">Alaska</option> 
<option value="Arizona">Arizona</option> 
<option value="Arkansas">Arkansas</option> 
<option value="Arkansas">Arkansas</option> 
<option value="California">California</option>
<option value="Colorado">Colorado</option> 
<option value="Connecticut">Connecticut</option> 
<option value="Delaware">Delaware</option> 
<option value="District of Columbia">Columbia</option> 
<option value="Florida">Florida</option> 
<option value="Georgia">Georgia</option> 
<option value="Hawaii">Hawaii</option> 
<option value="Idaho">Idaho</option> 
<option value="Illinois">Illinois</option> 
<option value="Indiana">Indiana</option> 
<option value="Iowa">Iowa</option> 
<option value="Kansas">Kansas</option> 
<option value="Kentucky">Kentucky</option> 
<option value="Louisiana">Louisiana</option> 
<option value="Maine">Maine</option> 
<option value="Maryland">Maryland</option> 
<option value="Massachusetts">Massachusetts</option> 
<option value="Michigan">Michigan</option> 
<option value="Minnesota">Minnesota</option> 
<option value="Mississippi">Mississippi</option> 
<option value="Missouri">Missouri</option> 
<option value="Montana">Montana</option> 
<option value="Nebraska">Nebraska</option> 
<option value="Nevada">Nevada</option> 
<option value="New Hampshire">New Hampshire</option> 
<option value="New Jersey">New Jersey</option> 
<option value="New Mexico">New Mexico</option> 
<option value="New York">New York</option> 
<option value="North Carolina">North Carolina</option> 
<option value="North Dakota">North Dakota</option> 
<option value="Ohio">Ohio</option> 
<option value="Oklahoma">Oklahoma</option> 
<option value="Oregon">Oregon</option> 
<option value="Pennsylvania">Pennsylvania</option> 
<option value="RhodeIsland">Rhode Island</option> 
<option value="South Carolina">South Carolina</option> 
<option value="South Dakota">South Dakota</option> 
<option value="Tennessee">Tennessee</option> 
<option value="Texas">Texas</option> 
<option value="Utah">Utah</option> 
<option value="Vermont">Vermont</option> 
<option value="Virginia">Virginia</option> 
<option value="Washington">Washington</option> 
<option value="West Virginia">West Virginia</option> 
<option value="Wisconsin">Wisconsin</option> 
<option value="Wyoming">Wyoming</option>
</select>
<br/>

Enter category:
  


<div align="center" id="map" style="width: 660px; height: 350px"><br/></div>  



 Lat:<input  type='text' name="lat" id='lat' >
 Lon:<input  type='text' name="lng" id='lng' >


<input type="text" name="cats"/>

<br/>
<input type="submit" value="submit"/>
</form>
</body>
</html>