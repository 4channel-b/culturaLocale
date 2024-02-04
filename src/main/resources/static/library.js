const BASE_URL = 'http://localhost:8080';
const SHOWCASE_MODE = true;

function setupShowcaseMode() {
    // Contest data
    document.getElementById("cstName").value = "Art Festival";
    document.getElementById("cstDescription").value = "An annual festival showcasing local artists and their work.";
    document.getElementById("cstInitialDate").value = "2024-05-01";
    document.getElementById("cstEndDate").value = "2024-05-07";
    document.getElementById("cstRules").value = "All artwork must be original and created within the last year.";
    document.getElementById("cstType").value = "Art";
    document.getElementById("cstContents").value = ""; // IDs unknown

    // Event data
    document.getElementById("evName").value = "Music Concert";
    document.getElementById("evDescription").value = "Outdoor music concert featuring various artists.";
    document.getElementById("evCreationDate").value = "2024-03-20";
    document.getElementById("evCreator").value = ""; // ID unknown
    document.getElementById("evTownHall").value = ""; // ID unknown
    document.getElementById("evStartDate").value = "2024-07-12";
    document.getElementById("evEndDate").value = "2024-07-14";
    document.getElementById("evLocationLongitude").value = "12.9716";
    document.getElementById("evLocationLatitude").value = "77.5946";

    // Itinerary data
    document.getElementById("itName").value = "Cultural Heritage Tour";
    document.getElementById("itDescription").value = "A guided tour of the city's historical landmarks.";
    document.getElementById("itCreationDate").value = "2024-04-10";
    document.getElementById("itCreator").value = ""; // ID unknown
    document.getElementById("itTownHall").value = ""; // ID unknown
    document.getElementById("itEstimateDuration").value = "3.5";
    document.getElementById("itDifficultyLevel").value = "2";
    document.getElementById("itContents").value = ""; // IDs unknown

    // Point of Interest data
    document.getElementById("poiName").value = "Liberty Statue";
    document.getElementById("poiDescription").value = "Famous statue with historical significance.";
    document.getElementById("poiCreationDate").value = "2024-01-15";
    document.getElementById("poiCreator").value = ""; // ID unknown
    document.getElementById("poiTownHall").value = ""; // ID unknown
    document.getElementById("poiCategory").value = "HISTORICAL_SITE";
    document.getElementById("poiLongitude").value = "40.6892";
    document.getElementById("poiLatitude").value = "74.0445";

    // TownHall data
    document.getElementById("thName").value = "Greenwood Municipality";
    document.getElementById("thDescription").value = "Administrative body for the Greenwood area.";
    document.getElementById("thLocationLongitude").value = "44.9680";
    document.getElementById("thLocationLatitude").value = "94.4203";
    document.getElementById("thArea").value = "120";
    document.getElementById("thEstablishment").value = "1856-03-11";

    // User data
    document.getElementById("usUsername").value = "johndoe123";
    document.getElementById("usFullName").value = "John Doe";
    document.getElementById("usEmail").value = "johndoe@example.com";
    document.getElementById("usRegistrationDate").value = "2024-02-20";
    document.getElementById("usUserTownHall").value = ""; // ID unknown
    document.getElementById("usRole").value = ""; // ID unknown
}

// Fill out input boxes with data if we are in showcase mode
// let's not waste time trying to fill them out ourselves, we need to show and tell!
if (SHOWCASE_MODE) {
    setupShowcaseMode();
}

function updateContentHeight() {
    const contentDiv = document.querySelector('.collapsible.active + .content');
    if (contentDiv) {
        contentDiv.style.maxHeight = 'none'; // Temporarily remove max height
        const scrollHeight = contentDiv.scrollHeight;
        contentDiv.style.maxHeight = scrollHeight + 'px'; // Set max height to new content height
    }
}

// Overriding the alert function here to show a notification instead of an alert
window.alert = function(message, duration = 3000) {
    const notification = document.getElementById('notification');
    notification.textContent = '✅ ' + message;
    notification.className = 'notification show';

    setTimeout(() => {
        notification.className = 'notification';
    }, duration);
};

// Let's handle the error alerts manually then
function error_alert(text, duration = 3000) {
    const notification = document.getElementById('error_notification');
    notification.textContent = '❌ ' + text;
    notification.className = 'error_notification show';

    setTimeout(() => {
        notification.className = 'error_notification';
    }, duration)
}

// Handle collapsible animations
document.addEventListener('DOMContentLoaded', (event) => {
    const coll = document.getElementsByClassName("collapsible");

    for (let i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            const content = this.nextElementSibling;
            if (content.style.maxHeight){
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + "px";
            }
        });
    }
});


function approveEvent(eventId, userId) {
    return fetch(`${BASE_URL}/content/approve/event/${eventId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function approvePoi(poiId, userId) {
    return fetch(`${BASE_URL}/content/approve/poi/${poiId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function approveItinerary(itineraryId, userId) {
    return fetch(`${BASE_URL}/content/approve/itinerary/${itineraryId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userId),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createContest(contestData) {
    return fetch(`${BASE_URL}/contest`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contestData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getContestById(id) {
    return fetch(`${BASE_URL}/contest/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllContests() {
    return fetch(`${BASE_URL}/contest/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function updateContest(contestData, id) {
    return fetch(`${BASE_URL}/contest/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contestData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function subscribeContentToContest(contentId, contestId) {
    return fetch(`${BASE_URL}/contest/subscribe/${contestId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contentId),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function terminateContest(contestId, winningContentId) {
    return fetch(`${BASE_URL}/contest/terminate/${contestId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(winningContentId),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createEvent(eventData) {
    return fetch(`${BASE_URL}/event`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getEventById(id) {
    return fetch(`${BASE_URL}/event/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllEvents() {
    return fetch(`${BASE_URL}/event/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function updateEvent(eventData, id) {
    return fetch(`${BASE_URL}/event/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createItinerary(itineraryData) {
    return fetch(`${BASE_URL}/itinerary`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(itineraryData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getItineraryById(id) {
    return fetch(`${BASE_URL}/itinerary/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllItineraries() {
    return fetch(`${BASE_URL}/itinerary/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function updateItinerary(itineraryData, id) {
    return fetch(`${BASE_URL}/itinerary/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(itineraryData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createPointOfInterest(poiData) {
    return fetch(`${BASE_URL}/poi`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(poiData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getPointOfInterestById(id) {
    return fetch(`${BASE_URL}/poi/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllPointsOfInterest() {
    return fetch(`${BASE_URL}/poi/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function updatePointOfInterest(poiData, id) {
    return fetch(`${BASE_URL}/poi/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(poiData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchContests(name, startDate, endDate, type) {
    return fetch(`${BASE_URL}/search/contests?name=${name}&startDate=${startDate}&endDate=${endDate}&type=${type}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchContent(name, description, creationDate, contentType) {
    const contentTypeQueryParam = contentType ? `&contentType=${contentType}` : '';
    return fetch(`${BASE_URL}/search/content?name=${name}&description=${description}&creationDate=${creationDate}${contentTypeQueryParam}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchItineraries(name, description, creationDate, difficultyLevel, estimatedDuration) {
    return fetch(`${BASE_URL}/search/itineraries?name=${name}&description=${description}&creationDate=${creationDate}&difficultyLevel=${difficultyLevel}&estimatedDuration=${estimatedDuration}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchPointsOfInterest(name, description, category, location) {
    return fetch(`${BASE_URL}/search/pois?name=${name}&description=${description}&category=${category}&location=${location}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchEvents(name, description, startDate, endDate) {
    return fetch(`${BASE_URL}/search/events?name=${name}&description=${description}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchUsersByRole(role) {
    return fetch(`${BASE_URL}/search/usersByRole?role=${role}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function searchUsersByEmail(email) {
    return fetch(`${BASE_URL}/search/usersByMail?email=${email}`)
        .then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createTownHall(townHallData) {
    return fetch(`${BASE_URL}/townHall`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(townHallData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getTownHallById(id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllTownHalls() {
    return fetch(`${BASE_URL}/townHall/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function updateTownHall(townHallData, id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(townHallData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function deleteTownHall(id) {
    return fetch(`${BASE_URL}/townHall/${id}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createUser(userData) {
    return fetch(`${BASE_URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getUserById(id) {
    return fetch(`${BASE_URL}/users/${id}`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function getAllUsers() {
    return fetch(`${BASE_URL}/users/getAll`, {
        method: 'GET'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function changeUserSuspensionStatus(suspensionData) {
    return fetch(`${BASE_URL}/users/suspension/`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(suspensionData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function changeUserRole(roleChangeData) {
    return fetch(`${BASE_URL}/users/role/`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(roleChangeData),
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function deleteUser(id) {
    return fetch(`${BASE_URL}/users/${id}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.status !== 200) {
            throw new Error(JSON.stringify(response.json()));
        }

        return response.json();
    });
}

function createUserButton() {
    const userData = {
        username: document.getElementById("usUsername").value,
        fullName: document.getElementById("usFullName").value,
        email: document.getElementById("usEmail").value,
        registrationDate: new Date(document.getElementById("usRegistrationDate").value),
        townHall: parseInt(document.getElementById("usUserTownHall").value),
        role: parseInt(document.getElementById("usRole").value)
    };

    createUser(userData)
        .then(user => {
            console.log("Created User:", user);
            alert("User created!");
        })
        .catch(error => {
            console.error("Error creating user:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("usUsername").value = "";
    document.getElementById("usFullName").value = "";
    document.getElementById("usEmail").value = "";
    document.getElementById("usRegistrationDate").value = "";
    document.getElementById("usUserTownHall").value = "";
    document.getElementById("usRole").value = "";
}

function createItineraryButton() {
    const contentsInput = document.getElementById("itContents").value;
    const contentsArray = contentsInput.split(',').map(id => parseInt(id.trim()));

    const itineraryData = {
        name: document.getElementById("itName").value,
        description: document.getElementById("itDescription").value,
        creationDate: new Date(document.getElementById("itCreationDate").value),
        creator: parseInt(document.getElementById("itCreator").value),
        townHall: parseInt(document.getElementById("itTownHall").value),
        estimatedDuration: parseFloat(document.getElementById("itEstimateDuration").value),
        difficultyLevel: parseInt(document.getElementById("itDifficultyLevel").value),
        contents: contentsArray
    };

    createItinerary(itineraryData)
        .then(user => {
            console.log("Created Itinerary:", user);
            alert('Itinerary created.');
        })
        .catch(error => {
            console.error("| ERROR | Something went wrong on creating Itinerary:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("itName").value = "";
    document.getElementById("itDescription").value = "";
    document.getElementById("itCreationDate").value = "";
    document.getElementById("itCreator").value = "";
    document.getElementById("itTownHall").value = "";
    document.getElementById("itEstimateDuration").value = "";
    document.getElementById("itDifficultyLevel").value = "";
    document.getElementById("itContents").value = "";
}

function createEventButton() {
    const eventData = {
        name: document.getElementById("evName").value,
        description: document.getElementById("evDescription").value,
        creationDate: new Date(document.getElementById("evCreationDate").value),
        creator: parseInt(document.getElementById("evCreator").value),
        townHall: parseInt(document.getElementById("evTownHall").value),
        startDate: new Date(document.getElementById("evStartDate").value),
        endDate: new Date(document.getElementById("evEndDate").value),
        location: {
            longitude: parseFloat(document.getElementById("evLocationLongitude").value),
            latitude: parseFloat(document.getElementById("evLocationLatitude").value)
        },
    };

    createEvent(eventData)
        .then(user => {
            console.log("Created Event:", user);
            alert('Event created.');
        })
        .catch(error => {
            console.error("| ERROR | Something went wrong on creating event:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("evName").value = "";
    document.getElementById("evDescription").value = "";
    document.getElementById("evCreationDate").value = "";
    document.getElementById("evCreator").value = "";
    document.getElementById("evTownHall").value = "";
    document.getElementById("evStartDate").value = "";
    document.getElementById("evEndDate").value = "";
    document.getElementById("evLocationLongitude").value = "";
    document.getElementById("evLocationLatitude").value = "";
}

function createTownHallButton() {
    const townHallData = {
        name: document.getElementById("thName").value,
        description: document.getElementById("thDescription").value,
        location: {
            longitude: parseFloat(document.getElementById("thLocationLongitude").value),
            latitude: parseFloat(document.getElementById("thLocationLatitude").value)
        },
        area: parseFloat(document.getElementById("thArea").value),
        establishment: new Date(document.getElementById("thEstablishment").value)
    };

    createTownHall(townHallData)
        .then(user => {
            console.log("Created TownHall:", user);
            alert('Created TownHall.');
        })
        .catch(error => {
            console.error("| ERROR | Something went wrong on creating TownHall:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("thName").value = "";
    document.getElementById("thDescription").value = "";
    document.getElementById("thLocationLongitude").value = "";
    document.getElementById("thLocationLatitude").value = "";
    document.getElementById("thArea").value = "";
    document.getElementById("thEstablishment").value = "";
}

function createContestButton() {
    const contentsArray = document.getElementById("cstContents").value.split(',').map(id => parseInt(id.trim()));

    const contestData = {
        name: document.getElementById("cstName").value,
        description: document.getElementById("cstDescription").value,
        initialDate: new Date(document.getElementById("cstInitialDate").value),
        endDate: new Date(document.getElementById("cstEndDate").value),
        rules: document.getElementById("cstRules").value,
        type: document.getElementById("cstType").value,
        contents: contentsArray
    };

    createContest(contestData)
        .then(contest => {
            console.log("Created Contest:", contest);
            alert('Contest created.');
        })
        .catch(error => {
            console.error("Error creating contest:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("cstName").value = "";
    document.getElementById("cstDescription").value = "";
    document.getElementById("cstInitialDate").value = "";
    document.getElementById("cstEndDate").value = "";
    document.getElementById("cstRules").value = "";
    document.getElementById("cstType").value = "";
    document.getElementById("cstContents").value = "";
}

function categoryStringToInteger(categoryString) {
    switch(categoryString) {
        case "HISTORICAL_SITE":
            return 0;
        case "MUSEUM":
            return 1;
        case "PARK":
            return 2;
        case "ART_GALLERY":
            return 3;
        case "RELIGIOUS_SITE":
            return 4;
        case "NATURAL_ATTRACTION":
            return 5;
        case "SHOPPING_AREA":
            return 6;
        case "ENTERTAINMENT_VENUE":
            return 7;
        case "SPORTS_FACILITY":
            return 8;
        case "EDUCATIONAL_INSTITUTION":
            return 9;
        default:
            throw new Error("Invalid category string");
    }
}

function createPointOfInterestButton() {
    const poiData = {
        name: document.getElementById("poiName").value,
        description: document.getElementById("poiDescription").value,
        creationDate: new Date(document.getElementById("poiCreationDate").value),
        creator: parseInt(document.getElementById("poiCreator").value),
        townHall: parseInt(document.getElementById("poiTownHall").value),
        category: categoryStringToInteger(document.getElementById("poiCategory").value),
        location: {
            longitude: parseFloat(document.getElementById("poiLongitude").value),
            latitude: parseFloat(document.getElementById("poiLatitude").value)
        }
    };

    createPointOfInterest(poiData)
        .then(poi => {
            console.log("Created Point of Interest:", poi);
            alert('Point of interest created successfully.');
        })
        .catch(error => {
            console.error("Error creating point of interest:", error);
            error_alert("Could not create requested object");
        });

    // Clean up the UI
    document.getElementById("poiName").value = "";
    document.getElementById("poiDescription").value = "";
    document.getElementById("poiCreationDate").value = "";
    document.getElementById("poiCreator").value = "";
    document.getElementById("poiTownHall").value = "";
    document.getElementById("poiCategory").value = "HISTORICAL_SITE";
    document.getElementById("poiLongitude").value = "";
    document.getElementById("poiLatitude").value = "";
}

function performSearch() {
    const searchType = document.getElementById("searchType").value;
    const searchTerm = document.getElementById("searchTerm").value;
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;
    const additionalParam = document.getElementById("additionalParam").value;

    switch (searchType) {
        case 'contests':
            searchContests(searchTerm, startDate, endDate, additionalParam).then(displayResults);
            break;
        case 'content':
            searchContent(searchTerm, searchTerm, startDate, additionalParam).then(displayResults);
            break;
        case 'itineraries':
            searchItineraries(searchTerm, searchTerm, startDate, additionalParam, additionalParam).then(displayResults);
            break;
        case 'pois':
            searchPointsOfInterest(searchTerm, searchTerm, additionalParam, additionalParam).then(displayResults);
            break;
        case 'events':
            searchEvents(searchTerm, searchTerm, startDate, endDate).then(displayResults);
            break;
        case 'usersByRole':
            searchUsersByRole(additionalParam).then(displayResults);
            break;
        case 'usersByEmail':
            searchUsersByEmail(searchTerm).then(displayResults);
            break;
        default:
            console.error('Invalid search type');
            error_alert('Invalid search type');
            break;
    }
}

function displayResults(results) {
    const resultsContainer = document.getElementById("searchResults");
    resultsContainer.innerHTML = ''; // Clear previous results

    const pre = document.createElement('pre');

    pre.textContent = JSON.stringify(results, null, 2);
    resultsContainer.appendChild(pre);

    alert('Search complete');
}

function processApproval() {
    const contentId = document.getElementById("contentId").value;
    const userId = document.getElementById("userId").value;
    const approvalType = document.getElementById("approvalType").value;

    if (!contentId || !userId) {
        error_alert("Both Content ID and User ID are required.");
        return;
    }

    switch (approvalType) {
        case 'event':
            approveEvent(contentId, userId).then(displayResults);
            break;
        case 'poi':
            approvePoi(contentId, userId).then(displayResults);
            break;
        case 'itinerary':
            approveItinerary(contentId, userId).then(displayResults);
            break;
        default:
            // Should probably make it a UI element, but it'll work for now.
            error_alert("Invalid approval type selected.");
            break;
    }
}

function viewContent() {
    const id = document.getElementById("entityId").value;
    const entityType = document.getElementById("entityType").value;

    if (!id) {
        error_alert("Please enter an ID to view.");
        return;
    }

    // Yeah, I know I'm not writing C, but it's a dynamically typed lang!
    let fetchFunction;

    switch (entityType) {
        case 'townHall':
            fetchFunction = getTownHallById;
            break;
        case 'poi':
            fetchFunction = getPointOfInterestById;
            break;
        case 'itinerary':
            fetchFunction = getItineraryById;
            break;
        case 'contest':
            fetchFunction = getContestById;
            break;
        case 'event':
            fetchFunction = getEventById;
            break;
        case 'user':
            fetchFunction = getUserById;
            break;
        default:
            error_alert("Please select a valid content type.");
            return;
    }

    fetchFunction(id)
        .then(data => {
            const display = document.getElementById("contentDisplay");
            display.textContent = JSON.stringify(data, null, 2);
            updateContentHeight();
            alert("Content successfully fetched.");
        })
        .catch(error => {
            console.error('Error fetching data: ', error);
            error_alert("Failed to fetch data. See console for more details.");
        });
}

function dumpContent() {
    const selectedType = document.getElementById("dumpType").value;
    let fetchFunction;

    switch (selectedType) {
        case 'contests':
            fetchFunction = getAllContests;
            break;
        case 'events':
            fetchFunction = getAllEvents;
            break;
        case 'itineraries':
            fetchFunction = getAllItineraries;
            break;
        case 'pois':
            fetchFunction = getAllPointsOfInterest;
            break;
        case 'townHalls':
            fetchFunction = getAllTownHalls;
            break;
        case 'users':
            fetchFunction = getAllUsers;
            break;
        default:
            error_alert("Invalid type selected for dumping.");
            return;
    }

    fetchFunction().then(data => {
        const dumpContainer = document.getElementById("dumpContainer");
        dumpContainer.innerHTML = ''; // Clear existing content

        const pre = document.createElement('pre');
        pre.textContent = `Dumping ${selectedType}:\n` + JSON.stringify(data, null, 2);

        dumpContainer.appendChild(pre);
        updateContentHeight();

        alert('Data loaded successfully');
    }).catch(error => {
        console.error('Error fetching data: ', error);
        error_alert("Failed to fetch data. See console for more details.");
    });
}

function deleteTownhallButton() {
    const id = parseInt(document.getElementById("toBeDeletedId").value);

    deleteTownHall(id)
        .then(data => {
            console.log('Townhall deleted successfully:', data);
            alert('Townhall deleted successfully');})
        .catch(error => {
            console.error('Error deleting townhall: ', error);
            error_alert('There was an error performing the requested operation.');
        });
}

function subscribeContestButton() {
    const contentId = parseInt(document.getElementById("contentToBeSubscribedId").value);
    const contestId = parseInt(document.getElementById("contestIdToSubTo").value);

    subscribeContentToContest(contentId, contestId).then(data => {
        alert('Content subscribed!');
    }).catch(error => {
        console.error('Error subscribing to contest: ', error);
        error_alert('There was an error performing the requested operation.');
    });
}

function closeContestButton() {
    const winningContentId = parseInt(document.getElementById("winningContentId").value);
    const contestId = parseInt(document.getElementById("winningContestId").value);

    terminateContest(contestId, winningContentId).then(data => {
        alert('Contest terminated with a winner!');
    }).catch(error => {
        console.error('Error terminating contest: ', error);
        error_alert('There was an error performing the requested operation.');
    });
}