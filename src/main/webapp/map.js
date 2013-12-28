
$(document).ready (function() {
        map = $('#map_canvas');
        map.gmap().bind('init', function () {
          // Process the Microdata for each Event into an object.
          map.gmap('microdata', 'http://schema.org/Event', function(result, item, index) {
            var eventName = result.properties.name[0];
            var logo = result.properties.image[0];
            var url = result.properties.url[0];

            // Traverse from the Event to the Place and from the Place to the
            // Address to get the properties.
            var streetAddress = result.properties.location[0].properties.address[0].properties.streetAddress[0];
            streetAddress = streetAddress.replace(/\s*\(.*?\)\s*/g, '');
            var municipality = result.properties.location[0].properties.address[0].properties.addressMunicipality[0];
            var postalCode = result.properties.location[0].properties.address[0].properties.addressPostalCode[0];
            var region = "Tenerife";
          
            // Join the address parts into a comma-separated string.
            var address = [streetAddress, municipality, postalCode, region].join(', ');
            // Run the Geocoder request for the address.
            map.gmap('search', {'address': address } , function(result, status) {
              if (status == google.maps.GeocoderStatus.OK) {
                // Create a LatLng object.
                var lat = result[0].geometry.location.lat();
                var lng = result[0].geometry.location.lng();
                var latlng = new google.maps.LatLng(lat, lng);
    
                var eventDetails = '';
                eventDetails += '<div class="iw">';
                eventDetails += '<img src="'+logo+'"></img>';
                eventDetails += '<h2><a href="'+url+'">'+eventName+'</a></h2>';
                eventDetails += '</div>';
    
                // Place the marker.
                var markerOptions = {
                  'bounds':true,
                  'position': latlng,
                };
                map.gmap('addMarker', markerOptions).click( function() {
                  map.gmap('openInfoWindow', { 'content': eventDetails }, this ); 
                });
              } else {
                  if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
                    nextAddress--;
                    delay++;
                  } else {
                    var reason="Code "+status;
                    var msg = 'address="' + search + '" error=' +reason+ '(delay='+delay+'ms)<br>';
                    document.getElementById("messages").innerHTML += msg;
                  }   
                next();
              }
            });
          });
        });
      });