document.addEventListener('DOMContentLoaded', function () {
    const tagsContainer = document.getElementById('tags-container');
    const tagInput = document.getElementById('tag-input');
    const textInput = document.getElementById('textInput');

    tagInput.addEventListener('keyup', function (event) {
        if (event.key === ' ' || event.keyCode === 32) {
            event.preventDefault();
            const tagValue = tagInput.value.trim().toLowerCase();

            if (tagValue !== '' && tagValue.length <= 32) {
                const existingTags = Array.from(tagsContainer.children).map(tag => tag.textContent.toLowerCase());
                if (existingTags.includes(tagValue)) {
                    alert('Tag is already added!');
                    return;
                }

                const tagElement = document.createElement('div');

                tagElement.classList.add('tag');
                tagElement.textContent = tagValue;
                tagElement.addEventListener('click', function () {
                    tagsContainer.removeChild(tagElement);
                    sourceDivs.forEach(function(div) {
                        textInput.value = '';
                        textInput.value += div.textContent;
                    });
                });

                tagsContainer.appendChild(tagElement);
                textInput.value += (textInput.value === '' ? '' : ',') + tagValue;

                tagInput.value = '';
            } else if (tagValue.length > 32) {
                alert('Your tag is too long (over 32 chars)!');
            }
        }
    });
});