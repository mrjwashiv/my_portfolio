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

function getComments() {
    const numComments = document.getElementById("num-of-comments").value;
    fetch('/data?numOfComments=' + numComments).then(
          response => response.json()).then((comment) => {

      commentListElement = document.
            getElementById("comment-container");
      commentListElement.innerHTML = '';
      comment.forEach((element) => {
        commentListElement.appendChild(createCommentElement(
            element));
      })
    });
}

function createCommentElement(element) {
    const commentElement = document.createElement('li');
    commentElement.className = 'comment';
    commentElement.innerText = element.userComment;

    const deleteButtonElement = document.createElement('button');
    deleteButtonElement.innerText = 'Delete';
    deleteButtonElement.addEventListener('click', () => {
        deleteComments(element);
        
        commentElement.remove();
    });

    commentElement.appendChild(deleteButtonElement);
    return commentElement;
}

function deleteComments(comment) {
    const params = new URLSearchParams();
    params.append('id', comment.id);
    fetch('/delete-data', {method: 'POST', body: params});
}