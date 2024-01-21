document.getElementById('profile-form').addEventListener('submit', function(event) {
  const password = document.getElementById('password').value;
  const confirm = document.getElementById('confirm-password').value;

  if (password != '') {
    if (password.length < 7) {
        alert('Your password must be at least 8 characters long.');
        event.preventDefault();
    }

    if (password != confirm) {
        alert('Passwords don\'t match each other!');
        event.preventDefault();
    }
  }
});
