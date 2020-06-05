// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['"I am running away from my responsibilities and it feels good."— Michael Scott',
       '"Mini cupcakes? As in the mini version of regular cupcakes? Which is already a mini version of cake? Honestly, where does it end with you people?" — Kevin Malone',
       '"I want people to be afraid of how much they love me." — Michael Scott',
       '"You only live once? False. You live every day. You only die once." — Dwight Schrute'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function getMessage() {
    fetch('/data').then(response => response.json()).then((message) => {

    const messageListElement = document.getElementById("message-container");
    messageListElement.innerHTML = '';
    message.forEach((element) => {
      messageListElement.appendChild(createListElement(element));
      })
    });

    system.out.print(messageListElement);


    /*
    console.log('Fetching a message');

    const responsePromise = fetch('/data');

    responsePromise.then(handleResponse);
    */
}

/*
function handleResponse (response) {
    console.log('Handling the Response');

    const textPromise = response.text();

    textPromise.then(addMessageToDom);
}

function addMessageToDom (message) {
    console.log('Adding this message to DOM: ' + message);

    const messageContainer = document.getElementById('message-container');
    messageContainer.innerText = message;
}
*/

function createListElement(text) {
    const liElement = document.createElement('li');
    liElement.innerText = text;
    return liElement;
}