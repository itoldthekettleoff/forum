document.getElementById('regForm').addEventListener('submit', function(event) {
  const usernameInput = document.getElementById('username').value;
  const passwordInput = document.getElementById('password').value;

  const passwordConfirmInput = document.getElementById('confirmPassword').value;
  if (usernameInput.includes(' ')) {
    alert('Username cannot contain spaces. Please remove spaces.');
    event.preventDefault();
  }

  if (usernameInput.length < 4) {
    alert('Your username must be at least 4 characters long.');
    event.preventDefault();
  }

  if (passwordInput.length < 7) {
    alert('Your password must be at least 8 characters long.');
    event.preventDefault();
  }

  if (passwordInput != passwordConfirmInput) {
    alert('Passwords don\'t match each other!');
    event.preventDefault();
  }
});