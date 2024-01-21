function edit() {
    const textArea = document.getElementById('content-change');
    const editBtn = document.getElementById('edit-btn');
    const discard= document.getElementById('discard-btn');
    const submitBtn = document.getElementById('submit-btn');
    const commentContent = document.getElementById('comment-content');
    const settingsDiv = document.getElementById('settings-div');
    const cardRight = document.getElementById('card-right');
    const deleteForm = document.getElementById('delete-form');

    textArea.style.display = 'block';
    cardRight.style.display = 'none';
    deleteForm.style.display = 'none';
    settingsDiv.style.width = '100%';
    editBtn.style.display = 'none';
    commentContent.style.display = 'none';
    submitBtn.style.display = 'block';
    discard.style.display = 'block';
}

function cancel() {
    const textArea = document.getElementById('content-change');
    const editBtn = document.getElementById('edit-btn');
    const discard= document.getElementById('discard-btn');
    const submitBtn = document.getElementById('submit-btn');
    const commentContent = document.getElementById('comment-content');
    const settingsDiv = document.getElementById('settings-div');
    const cardRight = document.getElementById('card-right');
    const deleteForm = document.getElementById('delete-form');

    textArea.value = '';
    settingsDiv.style.width = 'auto';
    deleteForm.style.display = 'block';
    textArea.style.display = 'none';
    cardRight.style.display = 'block';
    editBtn.style.display = 'block';
    commentContent.style.display = 'block';
    submitBtn.style.display = 'none';
    discard.style.display = 'none';
}