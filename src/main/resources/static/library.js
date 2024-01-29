const BASE_URL = 'http://localhost:8080';

document.getElementById("name").value = "Deputation";
document.getElementById("description").value = "Deputation";
document.getElementById("creationDate").value = "2024-01-24T15:00:00Z";
document.getElementById("creator").value = "1";
document.getElementById("eventTownHall").value = "1";
document.getElementById("startDate").value = "2024-01-24T15:00:00";
document.getElementById("endDate").value = "2024-01-24T15:00:00";
document.getElementById("locationLatitude").value = "99";
document.getElementById("locationLongitude").value = "99";

function approveEvent(eventId, userId) {
    return fetch(`${BASE_URL}/content/approve/event/${eventId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => response.json());
}

function approvePoi(poiId, userId) {
    return fetch(`${BASE_URL}/content/approve/poi/${poiId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => response.json());
}

function approveItinerary(itineraryId, userId) {
    return fetch(`${BASE_URL}/content/approve/itinerary/${itineraryId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => response.json());
}

function createContest(contestData) {
    return fetch(`${BASE_URL}/contest`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contestData),
    }).then(response => response.json());
}

function getContestById(id) {
    return fetch(`${BASE_URL}/contest/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllContests() {
    return fetch(`${BASE_URL}/contest/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function updateContest(contestData, id) {
    return fetch(`${BASE_URL}/contest/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contestData),
    }).then(response => response.json());
}

function subscribeContentToContest(contentId, contestId) {
    return fetch(`${BASE_URL}/contest/subscribe/${contestId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contentId),
    }).then(response => response.json());
}

function terminateContest(contestId, winningContentId) {
    return fetch(`${BASE_URL}/contest/terminate/${contestId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(winningContentId),
    }).then(response => response.json());
}

function createEvent(eventData) {
    return fetch(`${BASE_URL}/event`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventData),
    }).then(response => response.json());
}

function getEventById(id) {
    return fetch(`${BASE_URL}/event/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllEvents() {
    return fetch(`${BASE_URL}/event/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function updateEvent(eventData, id) {
    return fetch(`${BASE_URL}/event/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventData),
    }).then(response => response.json());
}

function createItinerary(itineraryData) {
    return fetch(`${BASE_URL}/itinerary`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(itineraryData),
    }).then(response => response.json());
}

function getItineraryById(id) {
    return fetch(`${BASE_URL}/itinerary/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllItineraries() {
    return fetch(`${BASE_URL}/itinerary/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function updateItinerary(itineraryData, id) {
    return fetch(`${BASE_URL}/itinerary/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(itineraryData),
    }).then(response => response.json());
}

function createPointOfInterest(poiData) {
    return fetch(`${BASE_URL}/poi`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(poiData),
    }).then(response => response.json());
}

function getPointOfInterestById(id) {
    return fetch(`${BASE_URL}/poi/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllPointsOfInterest() {
    return fetch(`${BASE_URL}/poi/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function updatePointOfInterest(poiData, id) {
    return fetch(`${BASE_URL}/poi/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(poiData),
    }).then(response => response.json());
}

function searchContests(name, startDate, endDate, type) {
    return fetch(`${BASE_URL}/search/contests?name=${name}&startDate=${startDate}&endDate=${endDate}&type=${type}`)
        .then(response => response.json());
}

function searchContent(name, description, creationDate, contentType) {
    const contentTypeQueryParam = contentType ? `&contentType=${contentType}` : '';
    return fetch(`${BASE_URL}/search/content?name=${name}&description=${description}&creationDate=${creationDate}${contentTypeQueryParam}`)
        .then(response => response.json());
}

function searchItineraries(name, description, creationDate, difficultyLevel, estimatedDuration) {
    return fetch(`${BASE_URL}/search/itineraries?name=${name}&description=${description}&creationDate=${creationDate}&difficultyLevel=${difficultyLevel}&estimatedDuration=${estimatedDuration}`)
        .then(response => response.json());
}

function searchPointsOfInterest(name, description, category, location) {
    return fetch(`${BASE_URL}/search/pois?name=${name}&description=${description}&category=${category}&location=${location}`)
        .then(response => response.json());
}

function searchEvents(name, description, startDate, endDate) {
    return fetch(`${BASE_URL}/search/events?name=${name}&description=${description}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json());
}

function searchUsersByRole(role) {
    return fetch(`${BASE_URL}/search/usersByRole?role=${role}`)
        .then(response => response.json());
}

function searchUsersByEmail(email) {
    return fetch(`${BASE_URL}/search/usersByMail?email=${email}`)
        .then(response => response.json());
}

function createTownHall(townHallData) {
    return fetch(`${BASE_URL}/townHall`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(townHallData),
    }).then(response => response.json());
}

function getTownHallById(id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllTownHalls() {
    return fetch(`${BASE_URL}/townHall/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function updateTownHall(townHallData, id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(townHallData),
    }).then(response => response.json());
}

function deleteTownHall(id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'DELETE'
    }).then(response => response.json());
}

function createUser(userData) {
    return fetch(`${BASE_URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    }).then(response => response.json());
}

function getUserById(id) {
    return fetch(`${BASE_URL}/users/${id}`, {
        method: 'GET'
    }).then(response => response.json());
}

function getAllUsers() {
    return fetch(`${BASE_URL}/users/getAll`, {
        method: 'GET'
    }).then(response => response.json());
}

function changeUserSuspensionStatus(suspensionData) {
    return fetch(`${BASE_URL}/users/suspension/`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(suspensionData),
    }).then(response => response.json());
}

function changeUserRole(roleChangeData) {
    return fetch(`${BASE_URL}/users/role/`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(roleChangeData),
    }).then(response => response.json());
}

function deleteUser(id) {
    return fetch(`${BASE_URL}/users/${id}`, {
        method: 'DELETE'
    }).then(response => response.json());
}

function createUserButton() {
    // Grab data from the DOM and fill out the object
    const sampleUserData = {
        username: document.getElementById("username").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        registrationDate: document.getElementById("registrationDate").value, // current date in ISO format
        townHall: parseInt(document.getElementById("userTownHall").value),
        role: parseInt(document.getElementById("role").value)
    };

    // Call the API
    createUser(sampleUserData)
        .then(user => console.log("Created User:", user))
        .catch(error => console.error("Error creating user:", error));

    // Clean up the UI
    document.getElementById("username").value = "";
    document.getElementById("fullName").value = "";
    document.getElementById("email").value = "";
    document.getElementById("registrationDate").value = "";
    document.getElementById("userTownHall").value = "";
    document.getElementById("role").value = "";
}

function createEventButton() {
    const eventData = {

        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        creationDate: document.getElementById("creationDate").value,
        creator: document.getElementById("creator").value,
        townHall: parseInt(document.getElementById("eventTownHall").value),
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value,
        location: {
            latitude: parseFloat(document.getElementById("locationLongitude").value),
            longitude: parseFloat(document.getElementById("locationLatitude").value)
        },
    };

    // Call the API
    createEvent(eventData)
        .then(user => console.log("Created Event:", user))
        .catch(error => console.error("| ERROR | Something went wrong on creating event:", error));

    // Clean up the UI
    document.getElementById("name").value = "";
    document.getElementById("description").value = "";
    document.getElementById("creationDate").value = "";
    document.getElementById("creator").value = "";
    document.getElementById("eventTownHall").value = "";
    document.getElementById("startDate").value = "";
    document.getElementById("endDate").value = "";
    document.getElementById("locationLongitude").value = "";
    document.getElementById("locationLatitude").value = "";
}
