
//method to delete data from table
clearTable = function () {
    var new_tbody = document.createElement('tbody');
    var old_tbody = document.getElementById("flight-table-body");
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    new_tbody.id = "flight-table-body";
};

// Get XHR ajax call
var reloadData = function () {
    clearTable();
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            var flightArray = JSON.parse(xhr.responseText);

            flightArray.forEach((flightElement) => {
                addFlightToTable(flightElement);
            });
        }
    };
    xhr.open("GET", "/air_flight/api/flight");

    xhr.send();
};
// added event listner to the dom
document.addEventListener("DOMContentLoaded", reloadData);

// function to add element to the table 
function addFlightToTable(flight) {
    var tr = document.createElement("tr");
    var flightNumber = document.createElement("td");
    var airport = document.createElement("td");
    var airlineName = document.createElement("td");
    var tailNumber = document.createElement("td");
    var distance = document.createElement("td");
    var departureCity = document.createElement("td");
    var arrivalCity = document.createElement("td");
    var date = document.createElement("td");
    var departureTime = document.createElement("td");
    var arrivalTime = document.createElement("td");

    // added delete icon to the table with modal and event listener 
    var deleteTd = document.createElement("td");
    var deleteBtn = document.createElement("button");
    deleteTd.appendChild(deleteBtn);
    deleteBtn.className = "fas fa-trash-alt";
    deleteBtn.setAttribute("data-toggle", "modal");
    deleteBtn.setAttribute("data-target", "#deleteConfirmModel");
    deleteBtn.addEventListener("click", function (event) {
        var tdChild = event.target.parentNode;
        var tdRow = tdChild.parentNode;
        var tdFirsCell = tdRow.childNodes[0];
        document.getElementById("flightToDeleteId").innerText =
            tdFirsCell.innerText;
    });

    // added update icon to the table with modal and event listener 
    var editTd = document.createElement("td");
    var editBtn = document.createElement("button");
    editTd.appendChild(editBtn);
    editBtn.className = "fas fa-edit";
    editBtn.setAttribute("data-toggle", "modal");
    editBtn.setAttribute("data-target", "#updateFlightModel");
    editTd.addEventListener("click", function (event) {
        var tdChild = event.target.parentNode;
        var tdRow = tdChild.parentNode;

        var tdFirsCell = tdRow.childNodes[0];
        document.getElementById("fnumberUpdate").value = tdFirsCell.innerText;
        document.getElementById("airportUpdate").value =
            tdRow.childNodes[1].innerText;
        document.getElementById("airlineNameUpdate").value =
            tdRow.childNodes[2].innerText;
        document.getElementById("tailNumberUpdate").value =
            tdRow.childNodes[3].innerText;
        document.getElementById("distanceUpdate").value =
            tdRow.childNodes[4].innerText;
        document.getElementById("departureCityUpdate").value =
            tdRow.childNodes[5].innerText;
        document.getElementById("arrivalCityUpdate").value =
            tdRow.childNodes[6].innerText;
        document.getElementById("dateUpdate").value = tdRow.childNodes[7].innerText;
        document.getElementById("departureTimeUpdate").value =
            tdRow.childNodes[8].innerText;
        document.getElementById("arrivalTimeUpdate").value =
            tdRow.childNodes[9].innerText;
    });

    flightNumber.innerText = flight.flight_number;
    airport.innerText = flight.airport;
    airlineName.innerText = flight.airline_name;
    tailNumber.innerText = flight.tail_number;
    distance.innerText = flight.distance;
    departureCity.innerText = flight.departure_city;
    arrivalCity.innerText = flight.arrival_city;
    date.innerText = flight.date;
    departureTime.innerText = flight.departure_time;
    arrivalTime.innerText = flight.arrival;

    tr.appendChild(flightNumber);
    tr.appendChild(airport);
    tr.appendChild(airlineName);
    tr.appendChild(tailNumber);
    tr.appendChild(distance);
    tr.appendChild(departureCity);
    tr.appendChild(arrivalCity);
    tr.appendChild(date);
    tr.appendChild(departureTime);
    tr.appendChild(arrivalTime);
    tr.appendChild(deleteTd);
    tr.appendChild(editTd);

    // call the table by its id to append rows
    document.getElementById("flight-table-body").appendChild(tr);
}

// Post opreation
document.getElementById("insert").addEventListener("click", function (event) {
    event.preventDefault();

    var flight_number = document.getElementById("fnumber").value;
    var airport = document.getElementById("airport").value;
    var airline_name = document.getElementById("airlineName").value;
    var tail_number = document.getElementById("tailNumber").value;
    var distance = document.getElementById("distance").value;
    var departure_city = document.getElementById("departureCity").value;
    var arrival_city = document.getElementById("arrivalCity").value;
    var date = document.getElementById("date").value;
    var departure_time = document.getElementById("departureTime").value + ":00";
    var arrival_time = document.getElementById("arrivalTime").value + ":00";

    var flight = {
        flight_number: flight_number,
        airline_name: airline_name,
        airport: airport,
        tail_number: tail_number,
        distance: distance,
        departure_city: departure_city,
        arrival_city: arrival_city,
        date: date,
        departure_time: departure_time,
        arrival: arrival_time,
    };

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            JSON.parse(xhr.responseText);

            reloadData();
        }
    };

    xhr.open("POST", "/air_flight/api/flight");

    xhr.send(JSON.stringify(flight));
});

// Update operation
document.getElementById("update").addEventListener("click", function (event) {
    event.preventDefault();

    var flight_number = document.getElementById("fnumberUpdate").value;
    var airport = document.getElementById("airportUpdate").value;
    var airline_name = document.getElementById("airlineNameUpdate").value;
    var tail_number = document.getElementById("tailNumberUpdate").value;
    var distance = document.getElementById("distanceUpdate").value;
    var departure_city = document.getElementById("departureCityUpdate").value;
    var arrival_city = document.getElementById("arrivalCityUpdate").value;
    var date = document.getElementById("dateUpdate").value;
    var departure_time = document.getElementById("departureTimeUpdate").value;
    var arrival_time = document.getElementById("arrivalTimeUpdate").value;

    var flight = {
        flight_number: flight_number,
        airline_name: airline_name,
        airport: airport,
        tail_number: tail_number,
        distance: distance,
        departure_city: departure_city,
        arrival_city: arrival_city,
        date: date,
        departure_time: departure_time,
        arrival: arrival_time,
    };
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            reloadData();
        }
    };
    xhr.open("PUT", "/air_flight/api/flight");
    xhr.send(JSON.stringify(flight));
});

// Delete operation
document.getElementById("delete").addEventListener("click", function (event) {
    event.preventDefault();

    var flight_number = document.getElementById("flightToDeleteId").innerText;

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            reloadData();

        }
    };
    xhr.open("DELETE", "/air_flight/api/flight?flight_number=" + flight_number);
    xhr.send();
});

// Find by flight number in the search bar
document.getElementById("basic-text1").addEventListener("click", function (event) {
    event.preventDefault();
    searchBar = document.getElementById("searchBar").value;
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            var serarchByFlight = JSON.parse(xhr.responseText);
            clearTable();
            if (serarchByFlight) {
                addFlightToTable(serarchByFlight);
            }

        }
    };
    xhr.open("GET", "/air_flight/api/flight?flight_number=" + searchBar);

    xhr.send();
});
