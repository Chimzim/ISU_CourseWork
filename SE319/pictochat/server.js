const rooms = {};
const name = localStorage.getItem("name");
const screenList = ["null"]

screenList.push(name);

document.getElementById("name").innerHTML = "Welcome, " + name;
