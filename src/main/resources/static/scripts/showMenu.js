const menu = document.getElementById('menuOptions');

function showMenu() {
    if (menu.style.display == "flex") {
        menu.style.display = "none";
    } else {
        menu.style.display = "flex";
    }
}